package com.raise.crowd.api;

import com.raise.crowd.entity.po.MemberPO;
import com.raise.crowd.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zmj
 * @date 2020/6/3 15:56
 * @Description
 */
@FeignClient("raise-crowd-mysql")
public interface MySQLRemoteService {

    @RequestMapping("get/memberpo/by/loginacct/remote")
    ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct);
}
