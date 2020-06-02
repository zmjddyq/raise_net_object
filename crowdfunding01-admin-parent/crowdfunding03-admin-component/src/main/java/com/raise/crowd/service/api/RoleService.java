package com.raise.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.raise.crowd.entity.Role;

import java.util.List;

/**
 * @author zmj
 * @date 2020/5/28 20:15
 * @Description
 */
public interface RoleService {

    PageInfo<Role> getPageInfo(String keyword,Integer pageNum,Integer pageSize);

    /**
     * 保存role
     * @param role
     */
    void saveRole(Role role);

    /**
     * 修改role
     * @param role
     */
    void updateRole(Role role);

    /**
     * 通过roleid数组批量删除
     * @param roleIdList
     */
    void deleteByRoleIdArray(List<Integer> roleIdList);

    /**
     * 通过adminId获取已被分配的role
     * @param adminId
     * @return List<Role>
     */
    List<Role> getAssignedRoleListByAdminId(Integer adminId);

    /**
     * 通过adminId获取未被分配的role
     * @param adminId
     * @return List<Role>
     */
    List<Role> getUnassignedRoleListByAdminId(Integer adminId);

    /**
     * 通过adminId保存此用户所拥有的权限
     * @param adminId
     * @param roleIdList
     */
    void saveAssignedRole(Integer adminId, List<Integer> roleIdList);
}
