package com.sj.user.service.ipml;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sj.common.pojo.ObjectUtil;
import com.sj.common.pojo.User;
import com.sj.common.utils.MD5Util;
import com.sj.common.vo.SysResult;
import com.sj.user.mapper.UserMapper;

import redis.clients.jedis.JedisCluster;
@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	/**
	 * 用户注册前进行用户名是否已存在的验证
	 * @param userName
	 * @return 返回1 表示已存在 不能进行注册
	 * 		        返回0 表示不存在 可以进行注册
	 */
	public Integer checkUserName(String userName) {
		
		return userMapper.checkExists(userName);
	}
	public Integer userSave(String username,String userpass) {
		try{
			String password = MD5Util.md5(userpass);
			User user = new User();
			user.setName(username);
			user.setPass(password);
			return userMapper.doRegist(user);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		
	}
	
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	@Autowired
	private JedisCluster cluster;
	public String userLogin(String username,String userpass) {
		User user = new User();
		String password = MD5Util.md5(userpass);
		user.setName(username);
		user.setPass(password);
		User exiUser = userMapper.login(user);
		if(exiUser!=null){ 
			//查询该用户是否已经登录
			String exitTicket = cluster.get(exiUser.getName());
//			String exiTicket = cluster.get("SJ_TICKET");
			if(StringUtils.isNotEmpty(exitTicket)){ //该用户已经登录
				cluster.del(exiUser.getName());  //删除用户登录信息
			}
			
			String key = "SJ_TICKET"+user.getName()+System.currentTimeMillis(); 
			
			String jsondata;
			try {
				jsondata = ObjectUtil.mapper.writeValueAsString(exiUser);
				String ticket=MD5Util.md5(key);
				//打桩 打印cluster中的key
				System.out.println(exiUser.getName());
				//打桩 打印cluster中的key
				System.out.println(ticket);
				cluster.set(exiUser.getName(), ticket);//在redis中注册用户登录信息
				cluster.set(ticket, jsondata);//加入用户ticket 为跨域获取用户信息 和用户续约使用
				return ticket;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
			
		}else{  
			return "";
		}
		
	}
	public SysResult isExiTicket(String ticket) {
		
		Long lastTime = cluster.ttl(ticket);
		if(lastTime<5*60 && lastTime>0){
			cluster.expire(ticket, (int)(lastTime+10*60));
		}
		String jsondata = cluster.get(ticket);
		return new SysResult(200, "sucess", jsondata);
	}

}
