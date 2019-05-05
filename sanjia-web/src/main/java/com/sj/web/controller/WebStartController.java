package com.sj.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebStartController {
	@RequestMapping("/")
	public String start() {
		
	return "index";
	}
	
	/*页面的跳转，跳转到后台商品管理，登录，
	注册，后台商品新增
	商品更新*/
	@RequestMapping(value="page/{pageName}"
			,method=RequestMethod.GET)
	public String goPage(@PathVariable 
			String pageName){
		return pageName;
	}
	
	
//	@RequestMapping(value="login",method=RequestMethod.POST)
//	@ResponseBody
//	public String method(String userName,String userPassword) {
//		System.out.println(userName);
//		System.out.println(userPassword);
//		return "access";
//	}
	
}
