package net.timetown.wechat.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.timetown.wechat.service.WeChatService;
import net.timetown.wechat.util.MessageFactory;
import net.timetown.wechat.util.Util;
import net.timetown.wechat.wxbean.Message;
import net.timetown.wechat.wxbean.MessageText;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author stevenkang
 * 
 * 2015-6-3 上午10:18:27
 */
@Controller("apiController")
@RequestMapping("/api")
public class ApiController {

	@Resource
	private WeChatService service;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public String get(String signature, String echostr, String timestamp, String nonce, HttpServletRequest request) {
		System.out.println("GET");
		try {
			// 验证消息真实性
			if (Util.checkSignature(signature, timestamp, nonce)) {
				return echostr;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void post(String signature, String echostr, String timestamp, String nonce, 
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("POST");
		// 验证消息真实性
		if (Util.checkSignature(signature, timestamp, nonce)) {
			try {
				SAXReader saxReader = new SAXReader();
				Document document = saxReader.read(request.getInputStream());
				
				//封装消息对象
				Message message = MessageFactory.getMessageFactory().parseMessage(document);
				
				Message reply = service.process(message);
				
				if (reply == null) {
					reply = new MessageText("没有找到，输入数字查看回复：\n1 回复文本消息\n2 回复图片消息\n3 回复语音消息\n4 回复视频消息\n5 回复音乐消息\n6 回复图文消息");
				}
				
				// 判断是否设置哪里来的、哪里去
				if (reply.getFromUserName() == null)
					reply.setFromUserName(message.getToUserName());
				if (reply.getToUserName() == null)
					reply.setToUserName(message.getFromUserName());
					
				String xml = MessageFactory.getMessageFactory().parseXml(reply);
				System.out.println(xml);
				response.getWriter().print(xml);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
