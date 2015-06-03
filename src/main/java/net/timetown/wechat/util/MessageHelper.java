package net.timetown.wechat.util;

import net.timetown.wechat.wxbean.Message;
import net.timetown.wechat.wxbean.MessageMusic;
import net.timetown.wechat.wxbean.MessageNews;
import net.timetown.wechat.wxbean.MessageNews.Item;
import net.timetown.wechat.wxbean.MessageText;

/**
 * 消息助手
 * 
 * @author stevenkang
 * @version 1.0.0
 * 
 */
public class MessageHelper {

	public static final String MSGTYPE_TEXT = "text";
	public static final String MSGTYPE_LINK = "link";
	public static final String MSGTYPE_NEWS = "news";
	public static final String MSGTYPE_EVENT = "event";

	public static String generateTextMessage(String toUserName,
			String fromUserName, Long createTime, String msgType, String content) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buffer.append("<xml>");
		buffer.append("<ToUserName><![CDATA[" + toUserName
				+ "]]></ToUserName>");
		buffer.append("<FromUserName><![CDATA[" + fromUserName
				+ "]]></FromUserName>");
		buffer.append("<CreateTime>" + createTime + "</CreateTime>");
		buffer.append("<MsgType><![CDATA[" + msgType + "]]></MsgType>");
		buffer.append("<Content><![CDATA[" + content + "]]></Content>");
		buffer.append("</xml>");

		return buffer.toString();
	}
	public static String generateTextMessage(MessageText message) {
		return generateTextMessage(message.getToUserName(), message.getFromUserName(), message.getCreateTime(), message.getMsgType(), message.getContent());
	}

	public static String generateLinkMessage(String toUserName,
			String fromUserName, Long createTime, String msgType, String title,
			String description, String url, Long msgId) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buffer.append("<xml>");
		buffer.append("<ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>");
		buffer.append("<FromUserName><![CDATA[" + toUserName
				+ "]]></FromUserName>");
		buffer.append("<CreateTime>" + createTime + "</CreateTime>");
		buffer.append("<MsgType><![CDATA[" + msgType + "]]></MsgType>");
		buffer.append("<Title><![CDATA[" + title + "]]></Title>");
		buffer.append("<Description><![CDATA[" + description
				+ "]]></Description>");
		buffer.append("<Url><![CDATA[" + url + "]]></Url>");
		buffer.append("<MsgId>" + msgId + "</MsgId>");
		buffer.append("</xml>");

		return buffer.toString();
	}

	public static String generateNews(MessageNews news) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buffer.append("<xml>");
		buffer.append("<ToUserName><![CDATA["+news.getToUserName()+"]]></ToUserName>");
		buffer.append("<FromUserName><![CDATA["+news.getFromUserName()+"]]></FromUserName>");
		buffer.append("<CreateTime>"+news.getCreateTime()+"</CreateTime>");
		buffer.append("<MsgType><![CDATA["+Message.MSGTYPE_NEWS+"]]></MsgType>");
		buffer.append("<ArticleCount>"+news.getSize()+"</ArticleCount>");
		buffer.append("<Articles>");
		for (Item item : news.getItems()) {
			buffer.append("<item>");
			buffer.append("<Title><![CDATA["+item.getTitle()+"]]></Title>");
			buffer.append("<Description><![CDATA["+item.getDescription()+"]]></Description>");
			buffer.append("<PicUrl><![CDATA["+item.getPicUrl()+"]]></PicUrl>");
			buffer.append("<Url><![CDATA["+item.getUrl()+"]]></Url>");
			buffer.append("</item>");
		}
		buffer.append("</Articles>");
		buffer.append("</xml>");
		
		return buffer.toString();
	}
	
	public static String generateMusic(MessageMusic music) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buffer.append("<xml>");
		buffer.append("<ToUserName><![CDATA["+music.getToUserName()+"]]></ToUserName>");
		buffer.append("<FromUserName><![CDATA["+music.getFromUserName()+"]]></FromUserName>");
		buffer.append("<CreateTime>"+music.getCreateTime()+"</CreateTime>");
		buffer.append("<MsgType><![CDATA["+music.getMsgType()+"]]></MsgType>");
		buffer.append("<Music>");
		buffer.append("<Title><![CDATA["+music.getTitle()+"]]></Title>");
		buffer.append("<Description><![CDATA["+music.getDescription()+"]]></Description>");
		buffer.append("<MusicUrl><![CDATA["+music.getMusicUrl()+"]]></MusicUrl>");
		buffer.append("<HQMusicUrl><![CDATA["+music.getHqMusicUrl()+"]]></HQMusicUrl>");
		buffer.append("</Music>");
		buffer.append("</xml>");
		
		return buffer.toString();
	}
	
	
}
