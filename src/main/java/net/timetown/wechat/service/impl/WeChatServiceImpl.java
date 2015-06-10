package net.timetown.wechat.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.timetown.wechat.service.WeChatService;
import net.timetown.wechat.util.HttpUtil;
import net.timetown.wechat.util.MySQLHelper;
import net.timetown.wechat.wxbean.Message;
import net.timetown.wechat.wxbean.MessageNews;
import net.timetown.wechat.wxbean.MessageText;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

@Service
public class WeChatServiceImpl implements WeChatService {

	// access_token 缓存
	private static Map<String, String> accessTokenCache;
	// access_token 有效时间
	private static Map<String, Long> accessTokenExpires;
	
	// jsapi_ticket 缓存
	private static Map<String, String> jsapiTicketCache;
	// jsapi_ticket 有效时间
	private static Map<String, Long> jsapiTicketExpires;
	
	private MySQLHelper mysql = MySQLHelper.get();
	
	static {
		accessTokenCache = new HashMap<>();
		accessTokenExpires = new HashMap<>();
		jsapiTicketCache = new HashMap<>();
		jsapiTicketExpires = new HashMap<>();
	}
	
	@Override
	public Message process(Message message) {
		
		if (message instanceof MessageText) {
			MessageText text = (MessageText) message;
			
			String keywords = text.getContent();
			
			if("1".equals(keywords)) {
				return new MessageText("这是文本消息");
			}
				
			if("2".equals(keywords)) {
				MessageText Messagetext = new MessageText();
				StringBuffer contentMsg = new StringBuffer();  
				contentMsg.append("欢迎访问<a href=\"http://blog.sina.com.cn/s/blog_72827fb10101pl9i.html\">个人主页</a>").append("\n");  
				contentMsg.append("您好，我是机器人小Q，请回复数字选择服务：").append("\n\n");  
				contentMsg.append("1  天气预报").append("\n");  
				contentMsg.append("2  公交查询").append("\n");  
				contentMsg.append("3  周边搜索").append("\n");  
				contentMsg.append("4  歌曲点播").append("\n");
				contentMsg.append("5  经典游戏").append("\n");  
				contentMsg.append("6  美女电台").append("\n");  
				contentMsg.append("7  人脸识别").append("\n"); 
				contentMsg.append("8  聊天唠嗑").append("\n");
				contentMsg.append("9  电影排行榜").append("\n");
				contentMsg.append("10 Q友圈").append("\n\n");  
				contentMsg.append("点击查看 <a href=\"http://blog.sina.com.cn/s/blog_72827fb10101pl9i.html\">帮助手册</a>");  

				Messagetext.setContent(contentMsg.toString());
				
				return Messagetext;
			}
				
			if("3".equals(keywords)) {
				return new MessageText("暂未提供语音消息");
			}
			if("4".equals(keywords)) {
				return new MessageText("暂未提供视频消息");
			}
			if("5".equals(keywords)) {
				return new MessageText("暂未提供音乐消息");
			}
			if("6".equals(keywords)) {
				MessageNews news = new MessageNews();
				news.getItems().add(news.new Item("标题", "描述", "http://data.attachment.timetown.net/portal/201403/08/205957jotw0doi0twzt0dq.png", "http://stevenkang.tunnel.mobi/WeChat/"));
				return news;
			}
			
			// 根据数据库来查询
			List<Map<String, Object>> datas = mysql.executeQuery("select content from test where ? like CONCAT('%',keywords,'%') limit 1", keywords);
			if (datas.size() > 0) {
				return new MessageText((String) datas.get(0).get("content"));
			}
			String sql = "select content from test where keywords = '"+keywords+"' limit 1";
			System.out.println("sql: " + sql);
			datas = mysql.executeQuery(sql);
			if (datas.size() > 0) {
				return new MessageText((String) datas.get(0).get("content"));
			}
		}
		return null;
	}

	@Override
	public String getAccessToken(String appid, String secret) {
		
		// 如果缓存中有，并且没有失效，获取缓存中的accessToken
		if (accessTokenCache.containsKey(appid) && accessTokenExpires.get(appid) > System.currentTimeMillis()) {
			return accessTokenCache.get(appid);
		} else {
			
			System.out.println("获取新的accessToken, appid: " + appid);
			// 生成新的
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
			String response = HttpUtil.readContentFromGet(String.format(url, appid, secret));
			System.out.println(response);
			
			JSONObject json = JSONObject.parseObject(response);
			if (json.containsKey("errcode") && json.getInteger("errcode") != 0) {
				throw new RuntimeException(json.getString("errmsg"));
			} else {
				String accessToken = json.getString("access_token");
				Integer expires_in = json.getInteger("expires_in");
				// 缓存accessToken
				accessTokenCache.put(appid, accessToken);
				accessTokenExpires.put(appid, System.currentTimeMillis() + expires_in*1000);
				return accessToken;
			}
		}
	}

	@Override
	public String getJsapiTicket(String accessToken) {
		// 如果缓存中有，并且没有失效，获取缓存中的jsapiTicket
		if (jsapiTicketCache.containsKey(accessToken) && jsapiTicketExpires.get(accessToken) > System.currentTimeMillis()) {
			return jsapiTicketCache.get(accessToken);
		} else {
			
			System.out.println("获取新的jsapiTicket, accessToken: " + accessToken);
			// 生成新的
			String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
			String response = HttpUtil.readContentFromGet(String.format(url, accessToken));
			System.out.println(response);
			
			JSONObject json = JSONObject.parseObject(response);
			if (json.containsKey("errcode") && json.getInteger("errcode") != 0) {
				throw new RuntimeException(json.getString("errmsg"));
			} else {
				String ticket = json.getString("ticket");
				Integer expires_in = json.getInteger("expires_in");
				// 缓存jsapiTicket
				jsapiTicketCache.put(accessToken, ticket);
				jsapiTicketExpires.put(accessToken, System.currentTimeMillis() + expires_in*1000);
				return ticket;
			}
		}
	}
}
