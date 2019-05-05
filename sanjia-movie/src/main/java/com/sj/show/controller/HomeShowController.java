package com.sj.show.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.common.pojo.Purchase;
import com.sj.show.serviceImpl.HomePageServiceImpl;

@Controller
public class HomeShowController {

	@Autowired
	private HomePageServiceImpl homePageService;
	
	//进入首页后默认最新上映
	@RequestMapping("/newplay")
	@ResponseBody
	public List<Purchase> newMoviePlay() throws Exception{
		List<Purchase> newplay = homePageService.newMoviePlay();
		
		return newplay;
		
	}
	
	//用户选择当前位置后的排序
	@RequestMapping("/userchoose")
	@ResponseBody
	public List<Purchase> userChoose(){
		List<Purchase> userChoose = homePageService.userChoose();
		
		return userChoose;
		
	}
	
	
}
