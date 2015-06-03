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
			
			String keywords = text.getContent();
			
			switch (keywords) {
			case "1":
				return new MessageText("这是文本消息");
			case "2":
				return new MessageText("暂未提供图片消息");
			case "3":
				return new MessageText("暂未提供语音消息");
			case "4":
				return new MessageText("暂未提供视频消息");
			case "5":
				return new MessageText("暂未提供音乐消息");
			case "6":
				MessageNews news = new MessageNews();
				news.getItems().add(news.new Item("标题", "描述", "http://data.attachment.timetown.net/portal/201403/08/205957jotw0doi0twzt0dq.png", "http://4kb.cn/"));
				return news;
			}
		}
		return null;
	}
}
