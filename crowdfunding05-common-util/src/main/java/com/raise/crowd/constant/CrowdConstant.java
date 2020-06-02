package com.raise.crowd.constant;

public class CrowdConstant {

	public static final String MESSAGE_LOGIN_FAILED = "抱歉！账号或密码错误！请重新输入！";
	public static final String MESSAGE_LOGIN_ACCT_ALREADY_IN_USE = "抱歉！这个账号已经被使用了！";
	public static final String MESSAGE_LOGIN_ACCT_CAN_USE = "账号可用！";
	public static final String MESSAGE_ACCESS_FORBIDEN = "请登录以后再访问！";
	public static final String MESSAGE_STRING_INVALIDATE = "字符串不合法！请不要传入空字符串！";
	public static final String MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE = "系统错误：登录账号不唯一！";
	public static final String MESSAGE_LOGINACCT_FORMAT_ERROR = "账号不合法!请输入3到16位由数字、字母、下划线组成的账号";
	public static final String MESSAGE_LOGINPWD_FORMAT_ERROR = "密码不合法!请输入6到18位数字、字母、下划线组成的密码";
	public static final String MESSAGE_USERNAME_FORMAT_ERROR = "用户名不合法!请输入3到16位字符组成的用户名";
	public static final String MESSAGE_LOGINACCT_REGEXP = "^[a-z0-9_-]{3,16}$";
	public static final String MESSAGE_PASSWORD_REGEXP = "^[a-z0-9_-]{6,18}$";
	public static final String MESSAGE_USERNAME_REGEXP = "^[0-9a-zA-Z\u4e00-\u9fa5_]{3,16}$";


	
	public static final String ATTR_NAME_EXCEPTION = "exception";
	public static final String ATTR_NAME_LOGIN_ADMIN = "Admin";
	public static final String ATTR_NAME_PAGE_INFO = "pageInfo";
	public static final String ATTR_NAME_LOGIN_ACCT = "loginAcct";

	public static final String MESSAGE_LOGINPWD_IS_EMPTY = "账号不能为空!";
	public static final String MESSAGE_LOGINACCT_IS_EMPTY = "密码不能为空!";

}
