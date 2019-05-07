package com.sj.movie.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sj.common.pojo.Actor;
import com.sj.common.pojo.Cinema;
import com.sj.common.pojo.Purchase;
import com.sj.common.utils.DistanceUtiles;
import com.sj.movie.mapper.ActorMapper;
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

	@Autowired
	private ActorMapper actorMapper;
	
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

	/**
	 * Description: 分页查询所有电影
	 *
	 * @Date: 2019/5/6 22:29
	 * @param: [page]
	 * @author: ls
	 */
	public PageInfo<Movie> getAll(Integer page) {
		PageHelper.startPage(page, 3);
		List<Movie> movies = movieMapper.selectAll();
		for (Movie s : movies) {
			PageHelper.startPage(1, 4);
            Example example = new Example(Purchase.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("movieName", s.getName());
			List<Purchase> purchases = purchaseMapper.selectByExample(example);
			s.setPurchase(purchases);
			for(Purchase pur : purchases) {
				System.out.println("进入距离设置");
				String cinameName = pur.getCinemaName();
				try {
					Cinema cinema = gradeMapper.getCinema(cinameName);
					System.out.println(cinema);//打桩
					//todo:
					double distance = DistanceUtiles.getDistance(cinema, 104.0826545833, 30.6655390000);//给的当前位置（日报大厦）
					pur.setDistance(distance);
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println("未找到影院！设置一个默认距离");
					pur.setDistance(2.40);
				}								
			}
		}
		return new PageInfo<>(movies);
	}

	/**
	 * Description: 分页查询指定电影的所有购票信息
	 *
	 * @Date: 2019/5/6 22:29
	 * @param: [name, page]
	 * @author: ls
	 */
    public PageInfo<Purchase> findPurchaseInfo(String name, Integer page) {
    	PageHelper.startPage(page, 12);
	    Example example = new Example(Purchase.class);
	    Example.Criteria criteria = example.createCriteria();
	    criteria.andEqualTo("movieName", name);
	    List<Purchase> purchases = purchaseMapper.selectByExample(example);
	    return new PageInfo<>(purchases);
    }

    /**
     * Description: 查询电影详情
     *
     * @Date: 2019/5/7 8:46
     * @param: [name]：电影名称
     * @author: ls
     */
    public Movie getMovieInfo(String name) {
    	Movie movie = new Movie();
    	movie.setName(name);
	    movie = movieMapper.selectOne(movie);
	    Actor actor = new Actor();
	    actor.setActorMovie(name);
	    List<Actor> actors = actorMapper.select(actor);
	    movie.setActors(actors);
	    return movie;
    }
}
