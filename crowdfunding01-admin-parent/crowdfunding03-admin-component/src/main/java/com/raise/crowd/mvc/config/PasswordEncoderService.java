package com.raise.crowd.mvc.config;

import com.raise.crowd.util.CrowdUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author zmj
 * @date 2020/5/31 19:20
 * @Description 对登录密码进行md5加密(用使用盐值的密码加密进行替代，更加安全可靠)
 */
@Deprecated
@Component
public class PasswordEncoderService implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return CrowdUtil.md5(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(CrowdUtil.md5(charSequence.toString()));
    }
}
