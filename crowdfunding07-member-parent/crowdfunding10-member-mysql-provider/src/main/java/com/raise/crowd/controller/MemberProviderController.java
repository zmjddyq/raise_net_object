package com.raise.crowd.controller;

import com.raise.crowd.entity.po.MemberPO;
import com.raise.crowd.service.api.MemberService;
import com.raise.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zmj
 * @date 2020/6/3 16:11
 * @Description
 */
@RestController
public class MemberProviderController {
    @Autowired
    MemberService memberService;
    @RequestMapping("get/memberpo/by/loginacct/remote")
    ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct){
        try {
            MemberPO memberPO = memberService.getMemberPOByLoginAcct(loginacct);
            return ResultEntity.successWithData(memberPO);
        } catch (Exception e) {
            return ResultEntity.failed(e.getMessage());
        }
    }
}
