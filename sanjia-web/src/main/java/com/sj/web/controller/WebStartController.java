package com.sj.web.controller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Controller
public class WebStartController {

    private static byte[] bytes = new byte[2048 * 1024];

    @RequestMapping("/")
    public String start() throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://movie/?page=1");
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String res = tostr(entity.getContent());
        System.out.println(res);
        return "index";
    }

    /*页面的跳转，跳转到后台商品管理，登录，
    注册，后台商品新增
    商品更新*/
    @RequestMapping(value = "page/{pageName}"
            , method = RequestMethod.GET)
    public String goPage(@PathVariable String pageName) {
        return pageName;
    }


//	@RequestMapping(value="login",method=RequestMethod.POST)
//	@ResponseBody
//	public String method(String userName,String userPassword) {
//		System.out.println(userName);
//		System.out.println(userPassword);
//		return "access";
//	}

    private String tostr(InputStream in) throws IOException {
        int i;
        StringBuilder str = new StringBuilder();
        while ((i = in.read(bytes)) != -1) {
            str.append(new String(bytes, 0, i, StandardCharsets.UTF_8));
        }
        in.close();
        return str.toString();
    }

}
