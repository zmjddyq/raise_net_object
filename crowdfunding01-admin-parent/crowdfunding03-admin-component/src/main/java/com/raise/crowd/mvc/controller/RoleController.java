package com.raise.crowd.mvc.controller;

import com.github.pagehelper.PageInfo;
import com.raise.crowd.entity.Role;
import com.raise.crowd.service.api.RoleService;
import com.raise.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zmj
 * @date 2020/5/28 20:17
 * @Description
 */
@RestController
public class RoleController {

    @Autowired
    RoleService roleService;


    /**
     * 通过id数组批量删除role
     * (需要删除权限)
     * @param roleIdList
     * @return
     */
    @PreAuthorize("hasAuthority('role:delete')")
    @PostMapping("Admin/removeByRoleIdArray.json")
    public ResultEntity<String> removeByRoleIdArray(@RequestBody List<Integer> roleIdList) {
        roleService.deleteByRoleIdArray(roleIdList);
        return ResultEntity.successWithoutData();
    }


    /**
     * 修改role
     * (需要修改权限)
     * @param role
     * @return
     */
    @PreAuthorize("hasAuthority('role:update')")
    @PostMapping("/admin/updateRole.json")
    public ResultEntity<String> updateRole(Role role) {
        roleService.updateRole(role);
        return ResultEntity.successWithoutData();
    }

    /**
     * 添加role
     * (需要添加权限)
     * @param role
     * @return
     */
    @PreAuthorize("hasAuthority('role:add')")
    @PostMapping("/admin/saveRole.json")
    public ResultEntity<String> saveRole(Role role) {
        roleService.saveRole(role);
        return ResultEntity.successWithoutData();
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
    @PreAuthorize("hasAuthority('role:get')")
    @PostMapping("/admin/getRoleList.json")
    public ResultEntity<PageInfo<Role>> getAdminList(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                     HttpServletRequest httpServletRequest) {
        PageInfo<Role> pageInfo = roleService.getPageInfo(keyword, pageNum, pageSize);

        return ResultEntity.successWithData(pageInfo);
    }
}
