package com.sj.movie.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sj.common.pojo.Purchase;
import com.sj.movie.mapper.MovieMapper;
import com.sj.movie.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.common.pojo.Movie;
import com.sj.movie.mapper.GradeMovieMapper;
import tk.mybatis.mapper.entity.Example;

@Service
public class GradeMovieService {
	@Resource
	private GradeMovieMapper gradeMapper;

	@Autowired
	private MovieMapper movieMapper;

	@Autowired
	private PurchaseMapper purchaseMapper;
	
	public List<Movie> getGradeMovieData(int page) {
		try {
			//获取当前时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Calendar calendar = Calendar.getInstance();
		    Date dayOneUtil = calendar.getTime();
		    System.out.println(dayOneUtil.toString());
		    //输入datetime类型的时间
		    java.sql.Timestamp dayOneSql=new java.sql.Timestamp(dayOneUtil.getTime());
		    System.out.println(dayOneSql);
		    //获取第二天时间
		    Calendar cal = Calendar.getInstance();
	        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
	        Date beginOfDate = cal.getTime();
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String format = formatter.format(beginOfDate);
	        Date parse = formatter.parse(format);
	        Calendar c = Calendar.getInstance();  
	        c.setTime(parse);  
	        c.add(Calendar.DAY_OF_MONTH, 1);
	        Date dayTwoUtil = c.getTime();
	        //System.out.println(dayTwoUtil.toString());
		    
	        java.sql.Timestamp dayTwoSql=new java.sql.Timestamp(dayTwoUtil.getTime());
	        System.out.println(dayTwoSql);
		    
		    
		    
		    
			List<Movie> list=new ArrayList<>();
			int j=1;
			String dataString;
			Movie dataMapper;
			if(page==1) {
				for(int i=0;i<3;i++) {
					dataString=gradeMapper.getGradeName(i,j);
					System.out.println(dataString);
					dataMapper = gradeMapper.getGradeData(dataString,dayOneSql,dayTwoSql);
					list.add(dataMapper);
					}
			}else {
				for(int i=page*3-3;i<page*3;i++) {
					dataString=gradeMapper.getGradeName(i,j);
					System.out.println(dataString);
					dataMapper = gradeMapper.getGradeData(dataString,dayOneSql,dayTwoSql);
					list.add(dataMapper);
					}
			}
			
			/*dataString=gradeMapper.getGradeName(0,j);
			System.out.println(dataString);
			dataMapper = gradeMapper.getGradeData(dataString,dayOne,dayTwo);
			list.add(dataMapper);*/
			return list;
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("报错了");
			return null;
		}
	}

	public int getGradeMovieCount() {
		int i=gradeMapper.getGradeMovieCountMapper();
		return i;
	}


	public PageInfo<Movie> getAll(Integer page) {
		PageHelper.startPage(1, 3);
		List<Movie> ss = movieMapper.selectAll();
		for (Movie s : ss) {
			PageHelper.startPage(1, 4);
            Example example = new Example(Purchase.class);
			Example.Criteria criteria = example.createCriteria();
			criteria.andEqualTo("movieName", s.getName());
			List<Purchase> purchases = purchaseMapper.selectByExample(example);
			s.setPurchase(purchases);
		}
		return new PageInfo<>(ss);
	}
}
