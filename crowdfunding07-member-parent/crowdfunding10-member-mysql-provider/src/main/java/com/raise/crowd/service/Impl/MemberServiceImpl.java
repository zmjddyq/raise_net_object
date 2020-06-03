package com.raise.crowd.service.Impl;

import com.raise.crowd.entity.po.MemberPO;
import com.raise.crowd.entity.po.MemberPOExample;
import com.raise.crowd.mapper.MemberPOMapper;
import com.raise.crowd.service.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zmj
 * @date 2020/6/3 16:14
 * @Description
 */
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberPOMapper memberPOMapper;

    @Override
    public MemberPO getMemberPOByLoginAcct(String loginacct) {
        MemberPOExample memberPOExample = new MemberPOExample();
        MemberPOExample.Criteria criteria = memberPOExample.createCriteria();
        criteria.andLoginacctEqualTo(loginacct);
        List<MemberPO> memberPOS = memberPOMapper.selectByExample(memberPOExample);
        return memberPOS.get(0);
    }
}
