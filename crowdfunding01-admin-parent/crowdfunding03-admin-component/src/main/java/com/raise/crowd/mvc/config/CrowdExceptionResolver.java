package com.raise.crowd.mvc.config;


import com.google.gson.Gson;
import com.raise.crowd.constant.CrowdConstant;
import com.raise.crowd.exception.*;
import com.raise.crowd.util.CrowdUtil;
import com.raise.crowd.util.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// @ControllerAdvice表示当前类是一个基于注解的异常处理器类
@ControllerAdvice
public class CrowdExceptionResolver {
	
	@ExceptionHandler(value = LoginAcctAlreadyInUseForUpdateException.class)
	public ModelAndView resolveLoginAcctAlreadyInUseForUpdateException(
			LoginAcctAlreadyInUseForUpdateException exception,
			HttpServletRequest request,
			HttpServletResponse response
			) throws IOException {
		
		String viewName = "errorPage/system-error";
		
		return commonResolve(viewName, exception, request, response);
	}
	
	@ExceptionHandler(value = LoginAcctAlreadyInUseException.class)
	public ModelAndView resolveLoginAcctAlreadyInUseException(
			LoginAcctAlreadyInUseException exception,
			HttpServletRequest request,
			HttpServletResponse response
			) throws IOException {
		
		String viewName = "admin/admin-add";
		
		return commonResolve(viewName, exception, request, response);
	}
	
/*	@ExceptionHandler(value = LoginFailedException.class)
	public ModelAndView resolveLoginFailedException(
			LoginFailedException exception,
			HttpServletRequest request,
			HttpServletResponse response
			) throws IOException {
		
		String viewName = "admin-login";
		
		return commonResolve(viewName, exception, request, response);
	}
	
	@ExceptionHandler(value = AccessForbiddenException.class)
	public ModelAndView resolveAccessForbiddenException(
			AccessForbiddenException exception,
			HttpServletRequest request,
			HttpServletResponse response
			) throws IOException {
		
		String viewName = "admin-login";
		
		return commonResolve(viewName, exception, request, response);
	}

	@ExceptionHandler(value = AccOrPwdFormatException.class)
	public ModelAndView resolveAccOrPwdFormatException(
			AccOrPwdFormatException exception,
			HttpServletRequest request,
			HttpServletResponse response
			) throws IOException {

		String viewName = "admin-login";

		return commonResolve(viewName, exception, request, response);
	}*/

	/**
	 * 管理员登录异常处理(security接管)
	 * @param exception
	 * @param request
	 * @param response
	 * @return ModelAndView 返回管理员登入页面
	 * @throws IOException
	 */
	@ExceptionHandler(value = Exception.class)
	public ModelAndView resolveAdminLoginException(
			Exception exception,
			HttpServletRequest request,
			HttpServletResponse response
			) throws IOException {

		String viewName = "errorPage/system-error";

		return commonResolve(viewName, exception, request, response);
	}

	// @ExceptionHandler将一个具体的异常类型和一个方法关联起来
	private ModelAndView commonResolve(
			
			// 异常处理完成后要去的页面
			String viewName, 
			
			// 实际捕获到的异常类型
			Exception exception, 
			
			// 当前请求对象
			HttpServletRequest request, 
			
			// 当前响应对象
			HttpServletResponse response) throws IOException {
		
		// 1.判断当前请求类型
		boolean judgeResult = CrowdUtil.judgeRequestType(request);
		
		// 2.如果是Ajax请求
		if(judgeResult) {
			
			// 3.创建ResultEntity对象
			ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());
			
			// 4.创建Gson对象
			Gson gson = new Gson();
			
			// 5.将ResultEntity对象转换为JSON字符串
			String json = gson.toJson(resultEntity);
			
			// 6.将JSON字符串作为响应体返回给浏览器
			response.getWriter().write(json);
			
			// 7.由于上面已经通过原生的response对象返回了响应，所以不提供ModelAndView对象
			return null;
		}
		
		// 8.如果不是Ajax请求则创建ModelAndView对象
		ModelAndView modelAndView = new ModelAndView();
		
		// 9.将Exception对象存入模型
		modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION, exception);
		
		// 10.设置对应的视图名称
		modelAndView.setViewName(viewName);
		
		// 11.返回modelAndView对象
		return modelAndView;
	}

}
