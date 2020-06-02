package com.raise.crowd.mvc.controller;

import com.github.pagehelper.PageInfo;
import com.raise.crowd.constant.CrowdConstant;
import com.raise.crowd.entity.Admin;
import com.raise.crowd.exception.LoginFailedException;
import com.raise.crowd.service.api.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * @author zmj
 * @date 2020/5/25 10:29
 * @Description 管理员
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);


    /**
     * 修改员工
     * (需要修改权限)
     * @param admin
     * @return
     */
    @PreAuthorize("hasAuthority('user:update')")
    @PostMapping("admin/updateAdmin.html")
    public String updateAdmin(@Validated Admin admin, BindingResult result,
                              @RequestParam("pageNum") Integer pageNum,
                              @RequestParam("keyword") String keyword,
                              HttpServletRequest request) {
        boolean hasErrors = result.hasErrors();
        HashMap<String, Object> errorMap = new HashMap<>(ERROR_MAP_INITIAL_CAPACITY);
        if (hasErrors) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            request.setAttribute("errorInfo_edit", errorMap);
            request.setAttribute("editAdmin", admin);
            return "admin/admin-edit";
        } else {
            adminService.updateAdmin(admin);
            return "redirect:/admin/getAdminList.html?pageNum=" + pageNum + "&keyword=" + keyword;
        }

    }

    /**
     * 去管理员修改页
     * (需要修改权限)
     * @param adminId
     * @param request
     * @return
     */
    @PreAuthorize("hasAuthority('user:update')")
    @GetMapping("/admin/toEditPage.html")
    public String toEditPage(@RequestParam("adminId") Integer adminId,
                             HttpServletRequest request) {
        request.setAttribute("editAdmin", adminService.getAdminById(adminId));
        return "admin/admin-edit";
    }

    /**
     * ajax进行账号字段唯一性校验
     */
/*    @ResponseBody
    @PostMapping(value = "/admin/checkLoginAcct.html",produces = {"text/html;charset=UTF-8;","application/json;"})
    public String CheckLoginAcct(@RequestParam("loginAcct") String LoginAcct){
        if (LoginAcct.isEmpty()){
            return CrowdConstant.MESSAGE_LOGINPWD_IS_EMPTY;
        }else if (!CrowdUtil.checkLoginAcct(LoginAcct)){
            return CrowdConstant.MESSAGE_LOGINACCT_FORMAT_ERROR;
        }else if (!adminService.checkLoginAcct(LoginAcct).isEmpty()){
            return CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE;
        }
        return CrowdConstant.MESSAGE_LOGIN_ACCT_CAN_USE;
    }*/
    /**
     * errorMap初始值
     */
    static final int ERROR_MAP_INITIAL_CAPACITY = (int) ((10 / 0.75) + 1);

    /**
     * 新建员工
     * (需要新增权限)
     * @param admin
     * @return
     */
    @PreAuthorize("hasAuthority('user:add')")
    @PostMapping("/admin/saveAdmin.html")
    public String saveAdmin(@Validated Admin admin, BindingResult result, HttpServletRequest request) {
        boolean hasErrors = result.hasErrors();
        HashMap<String, Object> errorMap = new HashMap<>(ERROR_MAP_INITIAL_CAPACITY);
        if (hasErrors) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            request.setAttribute("errorInfo", errorMap);
            request.setAttribute("errorAdmin", admin);
            return "admin/admin-add";
        } else {

            adminService.saveAdmin(admin);

            return "redirect:/admin/getAdminList.html?pageNum=" + Integer.MAX_VALUE;
        }

    }

    /**
     * 删除单个管理员用户
     * (需要删除权限)
     * @param adminId
     * @param pageNum
     * @param keyword
     * @return
     */
    @PreAuthorize("hasAuthority('user:delete')")
    @GetMapping("/admin/removeAdmin.html")
    public String removeAdmin(@RequestParam("adminId") Integer adminId,
                              @RequestParam("pageNum") Integer pageNum,
                              @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        adminService.removeAdmin(adminId);
        // 重定向回管理员列表页面(避免重复删除)
        return "redirect:/admin/getAdminList.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

    /**
     * 分页显示
     * (需要查询权限)
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @param httpServletRequest
     * @return
     */
    @PreAuthorize("hasAuthority('user:get')")
    @GetMapping("/admin/getAdminList.html")
    public String getAdminList(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                               HttpServletRequest httpServletRequest) {
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        httpServletRequest.setAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);
        return "admin/admin-page";
    }

    /**
     * 退出登录
     * Security接管退出登录
     * @param session
     * @return
     */
    @Deprecated
    @GetMapping("/admin/adminLoginOut.html")
    public String adminLoginOut(HttpSession session) {
        // 清除session数据
        session.invalidate();
        return "admin-login";
    }

    /**
     * 管理员登录
     * Security接管登录验证
     *
     * @param loginAcct
     * @param userPswd
     * @param session
     * @return
     */
    @Deprecated
    @PostMapping("/adminLogin.html")
    public String adminLogin(@RequestParam("loginAcct") String loginAcct,
                             @RequestParam("userPswd") String userPswd,
                             HttpServletRequest request,
                             HttpSession session) {
        // 1.进行账号密码校验
        if (loginAcct.isEmpty()) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGINPWD_IS_EMPTY);
        } else if (userPswd.isEmpty()) {
            request.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ACCT, loginAcct);
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGINACCT_IS_EMPTY);
        }
        // 2.验证账号密码
        Admin admin = adminService.login(loginAcct, userPswd);
        // 3.保存用户信息
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);
        // 4.返回登录成功页
        return "redirect:/admin/toAdmin-main.html";
    }

/*    @ResponseBody
    @RequestMapping("/ajaxTest")
    public String ajaxTest(@RequestParam("array[]")List<Integer> array){
        array.forEach(System.out::println);
        return "success";
    }
    @RequestMapping("/test")
    public String getAllAdmin(Model model){
        List<Admin> allAdmin = adminService.getAllAdmin();
        model.addAttribute("allAdmin",allAdmin);
        return "success";
    }*/
}
