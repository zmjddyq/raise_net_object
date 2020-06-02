package com.raise.crowd.mvc.config;

import com.raise.crowd.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * @author zmj
 * @date 2020/5/31 18:33
 * @Description
 */
public class SecurityAdmin extends User {
    private static final long serialVersionUID = 1L;

    // 原始的Admin对象，包含Admin对象的全部属性
    private Admin originalAdmin;

    public SecurityAdmin(
            // 传入原始的Admin对象
            Admin originalAdmin,

            // 创建角色、权限信息的集合
            List<GrantedAuthority> authorities) {

        // 调用父类构造器
        super(originalAdmin.getLoginAcct(), originalAdmin.getUserPswd(), authorities);

        // 给本类的this.originalAdmin赋值
        this.originalAdmin = originalAdmin;

        // 将原始Admin对象中的密码擦除
        this.originalAdmin.setUserPswd(null);

    }

    // 对外提供的获取原始Admin对象的getXxx()方法
    public Admin getOriginalAdmin() {
        return originalAdmin;
    }
}
