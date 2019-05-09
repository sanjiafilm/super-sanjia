package com.sj.web.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Controller
public class WebStartController {

    private static byte[] bytes = new byte[2048 * 1024];

    /**
     * Description: 首页
     *
     * @Date: 2019/5/6 22:21
     * @param: [model, page]
     * @author: ls
     */
    @RequestMapping("/")
    public String start(Model model, Double lng, Double lat, @RequestParam(required = false, defaultValue = "1") int page) throws IOException {
        String url = "http://movie/all?page=" + page;
        if(lng == null || lat == null){
            // 默认定位到四川日报大厦
            url += "&lng=104.0826545833&lat=30.6655390000";
        }else{
            url += "&lng=" + lng + "&lat=" + lat;
        }
        HttpResponse response = HttpClients.createDefault().execute(new HttpGet(url));
        String res = tostr(response.getEntity().getContent());
        model.addAttribute("movie_page", JSONObject.parse(res));
        model.addAttribute("lng", lng);
        model.addAttribute("lat", lat);
        model.addAttribute("page_params", "lng=" + lng + "&lat=" + lat);
        return "index";
    }

    /**
     * Description: 购票信息详情页
     *
     * @Date: 2019/5/7 11:26
     * @param: [model, name, page]
     * @author: ls
     */
    @RequestMapping("/buy")
    public String purchase(Model model, String name, Double lng, Double lat,
                           @RequestParam(required = false, defaultValue = "1") int page) throws IOException {
        String url = "http://movie/buy?name=" + name + "&page=" + page;
        if(lng == null || lat == null){
            // 默认定位到四川日报大厦
            url += "&lng=104.0826545833&lat=30.6655390000";
        }else{
            url += "&lng=" + lng + "&lat=" + lat;
        }
        HttpClient httpClient = HttpClients.createDefault();

        HttpEntity entity = httpClient.execute(new HttpGet(url)).getEntity();
        String json = tostr(entity.getContent());
        model.addAttribute("purchase_page", JSONObject.parse(json));

        HttpEntity movieEntity = httpClient.execute(new HttpGet("http://movie/movie_info?name=" + name)).getEntity();
        model.addAttribute("movie_info", JSONObject.parse(tostr(movieEntity.getContent())));
        model.addAttribute("page_url", "buy");
        model.addAttribute("page_params", "name=" + name + "&lng=" + lng + "&lat=" + lat);
        model.addAttribute("lng", lng);
        model.addAttribute("lat", lat);
        return "buy_list";
    }

    /**
     * Description: 搜索功能
     *
     * @Date: 2019/5/7 11:30
     * @param: [model, name, page]
     * @author: ls
     */
    @RequestMapping("/search")
    public String search(Model model, String name, @RequestParam(required = false, defaultValue = "1") int page) throws IOException {
        String url = "http://movie/all?page=" + page;
        //String url = "http://search/?name=" + name + "&page=" + page;
        HttpEntity movieEntity = HttpClients.createDefault().execute(new HttpGet(url)).getEntity();
        model.addAttribute("movie_page", JSONObject.parse(tostr(movieEntity.getContent())));
        model.addAttribute("page_url", "search");
        model.addAttribute("page_params", "name=" + name);
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
