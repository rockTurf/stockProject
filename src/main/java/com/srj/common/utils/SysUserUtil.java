package com.srj.common.utils;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.srj.common.constant.Constant;
import com.srj.web.sys.model.SysResource;
import com.srj.web.sys.model.SysUser;
import com.srj.web.sys.service.SysResourceService;
import com.srj.common.spring.utils.SpringContextHolder;


/**
 * @ClassName:SysUserUtils
 * @date:2015年2月4日 下午8:12:41
 * @author  ?
 */
public class SysUserUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(SysUserUtil.class);

	static SysResourceService sysResourceService = SpringContextHolder.getBean("sysResourceServiceImpl");
	
	
	 
	public static void setSessionLoginUser(SysUser sysUser){
		SysUser loginUser = sysUser;
		getSession().setAttribute(Constant.SESSION_LOGIN_USER, loginUser);
	}
	
	
	
	
	/**
	 * 得到当前session
	 */
	public static HttpSession getSession() {
		HttpServletRequest request = getCurRequest();
		if(request==null){
			return null;
		}
		HttpSession session = request.getSession();
		return session;
	}
	
	/**
	 * session中的用户
	 */
	public static SysUser getSessionLoginUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session==null){
			return null;
		}
		SysUser u= (SysUser) session.getAttribute(Constant.SESSION_LOGIN_USER);
		return u;
	}
	
	
	public static HttpServletRequest getCurRequest(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		if(request != null && request instanceof ServletRequestAttributes){
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)request;
			return servletRequestAttributes.getRequest();
		}
		return null;
	}
	
	public static String getLoginUserSessionName(){
		return Constant.SESSION_LOGIN_USER;
	}
	
	
	public static HttpServletRequest getCurResponse(){
		return null;
	}


	/**
	 * 清除缓存中的用户
	 */
	/*public static void clearCacheUser(Long userId){
		CacheUtils.evict(Constant.CACHE_SYS_USER,userId.toString());
	}*/


	/**
	 * 登录用户按钮
	 */
	public static Map<String, SysResource> getUserButton() {
		// TODO Auto-generated method stub
		return null;
	}

}
