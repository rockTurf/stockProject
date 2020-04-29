package com.srj.web.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.srj.common.spring.utils.SpringContextHolder;
import com.srj.common.utils.TreeUtils;
import com.srj.web.sys.model.SysResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srj.common.utils.SysUserUtil;
import com.srj.common.constant.Constant;
import com.srj.web.sys.model.SysUser;
import com.srj.web.sys.service.SysResourceService;
import com.srj.web.sys.service.SysUserService;

/**
 * @登陆页
 * 
 */
@Controller
@Transactional
public class LoginController {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysResourceService sysResourceService;
	
	/**
	 * 管理主页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String toIndex(Model model, HttpServletRequest request) {
		SysUser u = (SysUser)request.getSession().getAttribute(Constant.SESSION_LOGIN_USER);
        //拥有的资源列表
        List<SysResource> userMenus = sysResourceService.findUserMenuByUserId(u);
        userMenus = TreeUtils.toTreeNodeList(userMenus);
		//model返回
		model.addAttribute("menuList", userMenus);
		model.addAttribute("user", u);
		return "index";
	}
	
	
	@RequestMapping(value = "/loginCheck")
	public @ResponseBody Map<String, Object> checkLogin(@RequestParam Map<String,Object> params,Model model,HttpServletRequest request,HttpServletResponse response){
		String remPwd = params.get("remPwd").toString();
		Map<String, Object> msg = new HashMap<String, Object>();
		SysUser user= sysUserService.CheckUser(params.get("loginName").toString());
		//数据库取到对应的用户信息不为空 判断
		if(user!=null){
			//校验密码 b=true为密码匹配
			params.put("userId", user.getId());
			boolean b = sysUserService.CheckPassword(user,params.get("loginPwd").toString());
			if(b){//校验通过
				msg.put("success", "登录成功！");
				//将用户信息 存储到session中;
				request.getSession().setAttribute(Constant.SESSION_LOGIN_USER, user);
				//判断是否是是否点击了记住密码   
				if("yes".equals(remPwd)){
				    //存cookie
					 response.addCookie(new Cookie(Constant.SET_COOKIE_USERNAME,(String)params.get("loginName")));
					 response.addCookie(new Cookie(Constant.SET_COOKIE_PASSWORD,(String)params.get("loginPwd")));
                    response.addCookie(new Cookie(Constant.SET_COOKIE_REM_PWD,remPwd));
				}else{
					response.addCookie(new Cookie(Constant.SET_COOKIE_USERNAME,""));
					response.addCookie(new Cookie(Constant.SET_COOKIE_PASSWORD,""));
                    response.addCookie(new Cookie(Constant.SET_COOKIE_REM_PWD,""));
				}
			}else{//校验不通过
				msg.put("error", "密码错误！");
			}
		}else{
			msg.put("error", "找不到用户！");
		}
		return msg;
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin(HttpServletRequest request,HttpServletResponse response,Model model ) {
		if (request.getSession().getAttribute(Constant.SESSION_LOGIN_USER)!= null) {
			return "redirect:/index";
		}
		String userName = "";
		String passWord = "";
		Cookie[] cookies = request.getCookies();
		if(cookies!=null ){
			for(int i = 0 ; i < cookies.length; i++){
				if(cookies[i].getName().equals(Constant.SET_COOKIE_USERNAME)){
					userName= cookies[i].getValue();
				}else if(cookies[i].getName().equals(Constant.SET_COOKIE_PASSWORD)){
					passWord = cookies[i].getValue();
				}
			}
		}
		model.addAttribute(Constant.SET_COOKIE_USERNAME,userName);
		model.addAttribute(Constant.SET_COOKIE_PASSWORD,passWord);
		return "loginPage";
	}
	
	/**
	 * 用户退出
	 * 
	 * @return 跳转到登录页面
	 */
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		try{
		//SysUserUtil.clearCacheUser(SysUserUtil.getSessionLoginUser().getId());
		}catch(Exception e){}
		request.getSession().invalidate();
		return "redirect:/login";
	}
	
	/**
	 * 注册
	 * 
	 * @return 跳转到注册页面
	 */
	@RequestMapping("register")
	public String register(HttpServletRequest request) {
		return "register";
	}
	
}
