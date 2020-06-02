package com.raise.crowd.mvc.interceptor;

import com.raise.crowd.constant.CrowdConstant;
import com.raise.crowd.entity.Admin;
import com.raise.crowd.exception.AccessForbiddenException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author zmj
 * @date 2020/5/26 9:06
 * @Description
 */
@Deprecated
public class LoginInterceptor extends HandlerInterceptorAdapter {
    /***
     * preHandle
     *
     * 调用时间：Controller方法处理之前
     *
     * 执行顺序：链式Intercepter情况下，Intercepter按照声明的顺序一个接一个执行
     *
     * 若返回false，则中断执行，注意：不会进入afterCompletion
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 1.获取session对象
        HttpSession session = httpServletRequest.getSession();
        // 2.查看session对象里是否有包含登录信息
        Admin admin = (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);
        // 3.不包含抛出AccessForbiddenException异常
        if (admin == null) {
            throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDEN);
        }
        // 4.包含放行资源
        return true;


    }

}
