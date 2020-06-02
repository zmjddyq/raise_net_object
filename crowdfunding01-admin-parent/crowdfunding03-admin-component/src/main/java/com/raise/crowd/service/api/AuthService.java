package com.raise.crowd.service.api;

import com.raise.crowd.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 * @author zmj
 * @date 2020/5/30 19:00
 * @Description
 */
public interface AuthService {

    /**
     * 获取所有Auth
     * @return
     */
    List<Auth> getAllAuth();

    /**
     * 通过roleId获取AuthId
     * @param roleId
     * @return
     */
    List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

    /**
     * 保存权限
     * @param roleAssignAuthMap
     */
    void doRoleAssignAuth(Map<String, List<Integer>> roleAssignAuthMap);

    /**
     * 通过adminId获取authName
     * @param adminId
     * @return
     */
    List<String> getAssignedAuthNameByAdminId(Integer adminId);
}
