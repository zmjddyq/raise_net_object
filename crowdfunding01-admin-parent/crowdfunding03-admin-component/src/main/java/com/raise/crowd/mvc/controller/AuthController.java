package com.raise.crowd.mvc.controller;

import com.raise.crowd.entity.Auth;
import com.raise.crowd.service.api.AuthService;
import com.raise.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author zmj
 * @date 2020/5/30 19:01
 * @Description
 */
@RestController
public class AuthController {

    @Autowired
    AuthService authService;
    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PreAuthorize("hasAuthority('role:auth')")
    @PostMapping("admin/doRoleAssignAuth.json")
    public ResultEntity<String> doRoleAssignAuth(@RequestBody Map<String,List<Integer>> RoleAssignAuthMap){
        authService.doRoleAssignAuth(RoleAssignAuthMap);
        return ResultEntity.successWithoutData();
    }

    @PostMapping("admin/getAssignedAuthIdByRoleId.json")
    public ResultEntity<List<Integer>> getAssignedAuthIdByRoleId(@RequestParam("roleId")Integer roleId){
        List<Integer> authIdList = authService.getAssignedAuthIdByRoleId(roleId);
        return ResultEntity.successWithData(authIdList);
    }

    @PostMapping("admin/getAllAuth.json")
    public ResultEntity<List<Auth>> getAllAuth(){
        List<Auth> allAuthList = authService.getAllAuth();
        return ResultEntity.successWithData(allAuthList);
    }
}
