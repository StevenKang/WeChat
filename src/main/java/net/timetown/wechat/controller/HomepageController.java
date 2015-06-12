package net.timetown.wechat.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.timetown.wechat.service.WeChatService;
import net.timetown.wechat.util.Util;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomepageController {

	@Resource
	private WeChatService service;
	
	@RequestMapping
	public String get(Model model, HttpServletResponse response) {
		// 生成签名
		Map<String, String> params = new HashMap<>();
		params.put("noncestr", UUID.randomUUID().toString().replaceAll("-", ""));
		// 获取accessToken
		String accessToken = service.getAccessToken("wxac8428f6d6426ab7", "d1e1d969437d57f99a780370bb69a0e3");
		// 根据accessToken获取jsapiTicket
		params.put("jsapi_ticket", service.getJsapiTicket(accessToken));
		params.put("timestamp", String.valueOf(System.currentTimeMillis()));
		params.put("url", "http://stevenkang.tunnel.mobi/WeChat/");
		String signature = Util.jsSign(params);
		params.put("signature", signature);
		model.addAttribute("params", params);
		return "index";
	}
	
	@RequestMapping(value = "test")
	public void postTest(String key1, String key2, HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("request.method: " + request.getMethod());
		System.out.println("key1: " + key1);
		System.out.println("key2: " + key2);
		
		response.getWriter().println("中文1");
		response.getWriter().println("中\r\n文2");
	}
	
	@RequestMapping("test2")
	public void getTest2(HttpServletResponse response) throws IOException {
		response.getWriter().printf("这里是第一行内容\r\n换行了哦。最后没有换行");
	}
}
