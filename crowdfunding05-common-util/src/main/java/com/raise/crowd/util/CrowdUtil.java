package com.raise.crowd.util;


import com.raise.crowd.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 尚筹网项目通用工具方法类
 * @author zmj
 *
 */
public class CrowdUtil {

	public static String getDateTime(){
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String format = dateTimeFormatter.format(now);
		return format;
	}
	
	/**
	 * 对明文字符串进行MD5加密
	 * @param source 传入的明文字符串
	 * @return 加密结果
	 */
	public static String md5(String source) {
		
		// 1.判断source是否有效
		if(source == null || source.length() == 0) {
		
			// 2.如果不是有效的字符串抛出异常
			throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
		}
		
		try {
			// 3.获取MessageDigest对象
			String algorithm = "md5";
			
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			
			// 4.获取明文字符串对应的字节数组
			byte[] input = source.getBytes();
			
			// 5.执行加密
			byte[] output = messageDigest.digest(input);
			
			// 6.创建BigInteger对象
			int signum = 1;
			BigInteger bigInteger = new BigInteger(signum, output);
			
			// 7.按照16进制将bigInteger的值转换为字符串
			int radix = 16;
			String encoded = bigInteger.toString(radix).toUpperCase();
			
			return encoded;
		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 判断当前请求是否为Ajax请求
	 * @param request 请求对象
	 * @return
	 * 		true：当前请求是Ajax请求
	 * 		false：当前请求不是Ajax请求
	 */
	public static boolean judgeRequestType(HttpServletRequest request) {
		
		// 1.获取请求消息头
		String acceptHeader = request.getHeader("Accept");
		String xRequestHeader = request.getHeader("X-Requested-With");
		
		// 2.判断
		return (acceptHeader != null && acceptHeader.contains("application/json"))
				
				||
				
				(xRequestHeader != null && "XMLHttpRequest".equals(xRequestHeader));
	}

/*	*//**
	 * 账号校验
	 * @param loginAcct
	 * @return boolean
	 *//*
	public static boolean checkLoginAcct(String loginAcct){
		String loginAcctRegExp = "^[a-z0-9_-]{3,16}$";

		if (loginAcct.matches(loginAcctRegExp)) {
			return true;
		}
		return false;
	}

	*//**
	 * 密码校验
	 * @param userPswd
	 * @return boolean
	 *//*
	public static boolean checkUserPswd(String userPswd){
		String userPswdRegExp = "^[a-z0-9_-]{6,18}$";
		if (userPswd.matches(userPswdRegExp)) {
			return true;
		}
		return false;
	}*/
}
