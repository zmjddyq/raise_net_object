package com.raise.crowd.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author zmj
 * @date 2020/5/31 12:45
 * @Description
 */
@Configuration
@EnableWebSecurity
/**启用全局方法权限控制功能，并且设置prePostEnabled = true。保证@PreAuthority、@PostAuthority、@PreFilter、@PostFilter生效*/
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {

/*        builder.inMemoryAuthentication()
                .withUser("admin").password("123456").roles("ADMIN");*/
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 对请求进行授权
                .authorizeRequests()
                // 允许访问主页
                .antMatchers("/")
                .permitAll()
                .antMatchers("/index.jsp")
                .permitAll()
                // 允许访问登录页
                .antMatchers("/admin/to/login/page.html")
                // 无条件访问
                .permitAll()
                // 可以访问静态页面
                .antMatchers("/static/**")
                .permitAll()
                // 对其他页面进行拦截
                .anyRequest()
                .authenticated()

                //开启登录验证
                .and()
                .csrf()                            // 防跨站请求伪造功能
                .disable()                        // 禁用
                .formLogin()                    // 开启表单登录的功能
                // 指定登录页面
                .loginPage("/admin/to/login/page.html")
                // 登录表单提交地址
                .loginProcessingUrl("/security/do/login.html")
                // 指定登录成功后前往的地址
                .defaultSuccessUrl("/admin/to/main/page.html")
                // 账号的请求参数名称
                .usernameParameter("loginAcct")
                // 密码的请求参数名称
                .passwordParameter("userPswd")

                // 开启退出登录
                .and()
                .logout()
                .logoutUrl("/security/do/login/out.html")
        ;
    }
}
