package com.raise.crowd.mvc.controller;

import com.raise.crowd.entity.Role;
import com.raise.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zmj
 * @date 2020/5/30 14:41
 * @Description
 */
@Controller
public class AssignController {

    @Autowired
    RoleService roleService;

    /**
     * 分配角色
     * (需要分配角色权限)
     * @param adminId
     * @param pageNum
     * @param keyword
     * @param roleIdList
     * @return
     */
    @PreAuthorize("hasAuthority('user:role')")
    @PostMapping("admin/saveRoleAssign.html")
    public String saveRoleAssign(@RequestParam("adminId")Integer adminId,
                                 @RequestParam("pageNum")Integer pageNum,
                                 @RequestParam("keyword")String keyword,
                                 @RequestParam(value = "roleIdList",required = false)List<Integer> roleIdList){
        roleService.saveAssignedRole(adminId,roleIdList);
        return "redirect:/admin/getAdminList.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

    /**
     * 去角色分配页面
     *
     * @param adminId
     * @param request
     * @return
     */
    @GetMapping("admin/toAssignRolePage.html")
    public String toAssignRolePage(@RequestParam("adminId")Integer adminId,
                                 HttpServletRequest request){
        List<Role> assignedRoleListByAdminId = roleService.getAssignedRoleListByAdminId(adminId);
        List<Role> unassignedRoleListByAdminId = roleService.getUnassignedRoleListByAdminId(adminId);

        request.setAttribute("assignedRoleList",assignedRoleListByAdminId);
        request.setAttribute("unAssignedRoleList",unassignedRoleListByAdminId);

        return "admin/assign-role";
    }

}
