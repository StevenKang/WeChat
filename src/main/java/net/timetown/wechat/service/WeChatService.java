package net.timetown.wechat.service;

import net.timetown.wechat.wxbean.Message;

public interface WeChatService {

	/***
	 * 处理微信推送过来的消息
	 */
	public Message process(Message message);
}
