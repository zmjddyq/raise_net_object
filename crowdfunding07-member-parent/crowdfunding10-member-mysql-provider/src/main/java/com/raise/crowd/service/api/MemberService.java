package com.raise.crowd.service.api;

import com.raise.crowd.entity.po.MemberPO;

/**
 * @author zmj
 * @date 2020/6/3 16:14
 * @Description
 */
public interface MemberService {
    /**
     * 通过loginacct获取MemberPO
     * @param loginacct
     * @return MemberPO
     */
    MemberPO getMemberPOByLoginAcct(String loginacct);
}
