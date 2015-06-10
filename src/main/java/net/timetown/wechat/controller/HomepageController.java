package net.timetown.wechat.controller;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import net.timetown.wechat.service.WeChatService;
import net.timetown.wechat.util.Util;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
