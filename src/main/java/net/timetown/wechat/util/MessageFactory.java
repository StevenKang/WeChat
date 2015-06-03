package net.timetown.wechat.util;

import net.timetown.wechat.wxbean.Message;
import net.timetown.wechat.wxbean.MessageEvent;
import net.timetown.wechat.wxbean.MessageImage;
import net.timetown.wechat.wxbean.MessageLink;
import net.timetown.wechat.wxbean.MessageLocation;
import net.timetown.wechat.wxbean.MessageMusic;
import net.timetown.wechat.wxbean.MessageNews;
import net.timetown.wechat.wxbean.MessageText;

import org.dom4j.Document;

/**
 * 
 * 消息解析工厂<br>
 * 需要封装成对象的：<br>
 * 1.文本消息MessageText<br>
 * 2.图片消息MessageImage<br>
 * 3.地理位置消息MessageLocation<br>
 * 4.链接消息MessageLink<br>
 * 5.事件消息MessageEvent<br>
 * 解析成XML格式的：<br>
 * 1.文本消息MessageText<br>
 * 2.音乐消息MessageMusic<br>
 * 3.图文消息MessageNews<br>
 * 
 * @author stevenkang
 * @version 1.0.0
 * 
 */
public class MessageFactory {

	private static MessageFactory messageFactory = new MessageFactory();

	public MessageFactory() {

	}

	public static MessageFactory getMessageFactory() {
		return messageFactory;
	}

	public Message parseMessage(Document document) {

		String fromUserName = document.getRootElement().element("FromUserName")
				.getStringValue();
		
		String toUserName = document.getRootElement().element("ToUserName")
				.getStringValue();

		String msgType = document.getRootElement().element("MsgType")
				.getStringValue();

		Long createTime = Long.valueOf(document.getRootElement()
				.element("CreateTime").getStringValue());
		
		Long msgId = null;
		if (!Message.MSGTYPE_EVENT.equals(msgType)) {
			msgId = Long.valueOf(document.getRootElement()
				.element("MsgId").getStringValue());
		}
		
		if (Message.MSGTYPE_TEXT.equals(msgType)) {
			//文本消息
			String content = document.getRootElement().element("Content")
					.getStringValue();
			MessageText message = new MessageText(content);
			message.setToUserName(toUserName);
			message.setFromUserName(fromUserName);
			message.setCreateTime(createTime);
			message.setMsgId(msgId);
			return message;
			
		} else if (Message.MSGTYPE_IMAGE.equals(msgType)) {
			//图片消息
			String picUrl = document.getRootElement().element("PicUrl")
					.getStringValue();
			String mediaId = document.getRootElement().element("MediaId")
					.getStringValue();
			
			MessageImage message = new MessageImage(picUrl);
			message.setToUserName(toUserName);
			message.setFromUserName(fromUserName);
			message.setCreateTime(createTime);
			message.setMsgId(msgId);
			message.setMediaId(mediaId);
			return message;
			
		} else if (Message.MSGTYPE_LOCATION.equals(msgType)) {
			
			Double locationX = Double.valueOf(document.getRootElement().element("Location_X")
					.getStringValue());
			
			Double locationY = Double.valueOf(document.getRootElement().element("Location_Y")
					.getStringValue());
			
			Integer scale = Integer.valueOf(document.getRootElement().element("Scale")
					.getStringValue());
			
			String label = document.getRootElement().element("Label")
					.getStringValue();
			
			MessageLocation message = new MessageLocation(locationX, locationY, scale, label);
			message.setToUserName(toUserName);
			message.setFromUserName(fromUserName);
			message.setCreateTime(createTime);
			message.setMsgId(msgId);
			return message;
		} else if (Message.MSGTYPE_LINK.equals(msgType)) {
			String title = document.getRootElement().element("Title")
					.getStringValue();
			String description = document.getRootElement().element("Description")
					.getStringValue();
			String url = document.getRootElement().element("Url")
					.getStringValue();
			MessageLink message = new MessageLink(title, description, url);
			message.setToUserName(toUserName);
			message.setFromUserName(fromUserName);
			message.setCreateTime(createTime);
			message.setMsgId(msgId);
			return message;
		} else if (Message.MSGTYPE_EVENT.equals(msgType)) {
			String event = document.getRootElement().element("Event")
					.getStringValue();
			String eventKey = document.getRootElement().element("EventKey")
					.getStringValue();
			
			
			MessageEvent message = new MessageEvent(event, eventKey);
//			message.setEvent(event);
//			message.setEventKey(eventKey);
			message.setToUserName(toUserName);
			message.setFromUserName(fromUserName);
			message.setCreateTime(createTime);
			message.setMsgId(msgId);
			
			return message;
		} else {
			throw new RuntimeException("不支持的消息类型封装：" + msgType);
		}
	}

	public String parseXml(Message message) {
		
		if (message instanceof MessageText) {
			return MessageHelper.generateTextMessage((MessageText)message);

		} else if (message instanceof MessageMusic) {
			return MessageHelper.generateMusic((MessageMusic)message);
			
		} else if (message instanceof MessageNews) {
			return MessageHelper.generateNews((MessageNews)message);
			
		} else {
			throw new RuntimeException("不能解析为XML格式的消息类型：" + message.getMsgType());
		}
	}
}
