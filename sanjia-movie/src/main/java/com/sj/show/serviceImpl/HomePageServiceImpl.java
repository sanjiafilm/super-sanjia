package com.sj.show.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sj.common.pojo.Purchase;
import com.sj.show.mapper.HomePageMapper;

@Service
public class HomePageServiceImpl {
	@Autowired
	private HomePageMapper homePageMapper;
	
	
	//默认首页最新上映展示
	public List<Purchase> newMoviePlay() throws Exception {

		List<Purchase> newplay = homePageMapper.newMoviePlay();
		return newplay;
	}


	public List<Purchase> userChoose() {
		
		List<Purchase> userChoose = homePageMapper. userChoose();
		return userChoose;
	   
	}

}
