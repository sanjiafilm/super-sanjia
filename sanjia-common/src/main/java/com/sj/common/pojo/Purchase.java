package com.sj.common.pojo;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_purchase")
public class Purchase  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String movieName;  //电影名称
	private String showTime;   //上映时间
	private String category;   //分类
	private String  score;   //评分
	private String cinemaName;   //影院名字
	private String address;   //影院地址
	private Date playTime;  //场次
	private String platform;   //购票平台
	private Double price;   //价格	
	private String href;   //购票链接
	private String favoriteId;//唯一标识，只做收藏的关联字段
	private String distance;//距离（根据购票信息的电影院的经纬度和当前位置的经纬度计算得到距离）
	
	
	@Override
	public String toString() {
		return "Purchase [movieName=" + movieName + ", showTime=" + showTime + ", category=" + category + ", score="
				+ score + ", cinemaName=" + cinemaName + ", address=" + address + ", playTime=" + playTime
				+ ", platform=" + platform + ", price=" + price + ", href=" + href + ", favoriteId=" + favoriteId
				+ ", distance=" + distance + "]";
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}


	
	public Purchase(String movieName, String showTime, String category, String score, String cinemaName, String address,
			Date playTime, String platform, Double price, String href, String favoriteId, String distance) {
		super();
		this.movieName = movieName;
		this.showTime = showTime;
		this.category = category;
		this.score = score;
		this.cinemaName = cinemaName;
		this.address = address;
		this.playTime = playTime;
		this.platform = platform;
		this.price = price;
		this.href = href;
		this.favoriteId = favoriteId;
		this.distance = distance;
	}
	

	public Purchase() {
		
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getPlayTime() {
		return playTime;
	}

	public void setPlayTime(Date playTime) {
		this.playTime = playTime;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(String favoriteId) {
		this.favoriteId = favoriteId;
	}
	
	
	

}
