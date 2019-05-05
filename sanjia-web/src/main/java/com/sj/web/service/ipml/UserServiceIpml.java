package com.sj.web.service.ipml;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.common.config.HttpClientService;
import com.sj.common.config.UrlAddr;
import com.sj.common.pojo.User;
import com.sj.common.vo.SysResult;

@Service
public class UserServiceIpml {

	@Autowired
	private HttpClientService client;
	/**
	 * 用户注册前进行用户名是否已存在的验证
	 * @param userName
	 * @return
	 */
	public SysResult checkUserNameExists(String userName) {
		String url = UrlAddr.userRegistUrl+UrlAddr.userRegisterKey01+userName;
		try{
			//发起get提交请求,接收返回的1,0,封装SysResult
			Integer exists=
			Integer.parseInt(client.doGet(url));
			return SysResult.build(exists, "查询成功", null);
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(1, "出现异常了", null);
		}
	}
	/**
	 * 用户注册
	 * @param user
	 */
	public SysResult doRegist(User user) {
		String url = UrlAddr.userRegistUrl+UrlAddr.userRegisterKey02;
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("username", user.getName());
		param.put("userpass", user.getPass());
		
		try{
			String res = client.doGet(url, param);
			if(res!=null) {
				return SysResult.build(1, "查询成功", null);				
			}
			else {
				return SysResult.build(0, "查询失败", null);
			}
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(0, "出现异常了", null);
		}
		
	}
	public String doLogin(User user) {
		String url = UrlAddr.userRegistUrl+UrlAddr.userLoginKey03;
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("username", user.getName());
		param.put("userpass", user.getPass());
		try{
			String ticket = client.doGet(url, param);
			return ticket;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		
	}

}
