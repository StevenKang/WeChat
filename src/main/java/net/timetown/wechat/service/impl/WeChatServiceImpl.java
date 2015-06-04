package net.timetown.wechat.service.impl;

import net.timetown.wechat.service.WeChatService;
import net.timetown.wechat.wxbean.Message;
import net.timetown.wechat.wxbean.MessageNews;
import net.timetown.wechat.wxbean.MessageText;

import org.springframework.stereotype.Service;

@Service
public class WeChatServiceImpl implements WeChatService {

	@Override
	public Message process(Message message) {
		
		if (message instanceof MessageText) {
			MessageText text = (MessageText) message;
			
			int keywords = Integer.parseInt(text.getContent());
			
			switch (keywords) {
			case 1:
				return new MessageText("这是文本消息");
			case 2:
				
				MessageText Messagetext = new MessageText();
				StringBuffer contentMsg = new StringBuffer();  
				contentMsg.append("欢迎访问<a href=\"http://chatcourse.duapp.com\">个人主页</a>").append("\n");  
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
				contentMsg.append("点击查看 <a href=\"http://chatcourse.duapp.com\">帮助手册</a>");  

				Messagetext.setContent(contentMsg.toString());
				
				
				return Messagetext;
			case 3:
				return new MessageText("暂未提供语音消息");
			case 4:
				return new MessageText("暂未提供视频消息");
			case 5:
				return new MessageText("暂未提供音乐消息");
			case 6:
				MessageNews news = new MessageNews();
				news.getItems().add(news.new Item("标题", "描述", "http://data.attachment.timetown.net/portal/201403/08/205957jotw0doi0twzt0dq.png", "http://4kb.cn/"));
				return news;
			}
		}
		return null;
	}
}
