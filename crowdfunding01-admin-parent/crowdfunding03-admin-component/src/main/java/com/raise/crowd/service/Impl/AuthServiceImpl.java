package com.raise.crowd.service.Impl;

import com.raise.crowd.entity.Auth;
import com.raise.crowd.entity.AuthExample;
import com.raise.crowd.mapper.AuthMapper;
import com.raise.crowd.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zmj
 * @date 2020/5/30 19:01
 * @Description
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthMapper authMapper;

    @Override
    public List<Auth> getAllAuth() {
        return authMapper.selectByExample(new AuthExample());
    }

    @Override
    public List<Integer> getAssignedAuthIdByRoleId(Integer roleId) {
        return authMapper.getAssignedAuthIdByRoleId(roleId);
    }

    @Override
    public void doRoleAssignAuth(Map<String, List<Integer>> roleAssignAuthMap) {
        // 获取map里的信息
        List<Integer> roleIdList = roleAssignAuthMap.get("roleId");
        Integer roleId = roleIdList.get(0);
        List<Integer> authIdList = roleAssignAuthMap.get("authIdArray");

        // 删除原有权限
        authMapper.deleteRoleAssignAuthOldByRoleId(roleId);

        // 判断是否需要保存权限
        if (authIdList != null && authIdList.size() > 0){
            // 保存权限信息
            authMapper.insertRoleAssignAuthByRoleId(roleId,authIdList);
        }
    }

    @Override
    public List<String> getAssignedAuthNameByAdminId(Integer adminId) {
        return authMapper.selectAssignedAuthNameByAdminId(adminId);
    }
}
