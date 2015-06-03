package net.timetown.wechat.wxbean;

/**
 * 
 * 事件类型，subscribe(订阅)、unsubscribe(取消订阅)、CLICK(自定义菜单点击事件)
 * 
 * @author stevenkang
 * @version 1.0.0
 * 
 */
public class MessageEvent extends Message {
	/**subscribe(订阅)*/
	public static final String EVENT_SUBSCRIBE = "subscribe";
	/**unsubscribe(取消订阅)*/
	public static final String EVENT_UNSUBSCRIBE = "unsubscribe";
	/**CLICK(自定义菜单点击事件)*/
	public static final String EVENT_CLICK = "CLICK";
	/**default(默认回复)*/
	public static final String EVENT_DEFAULT = "default";
	
	public static final String EVENT_VIEW = "VIEW";
	
	private String event;
	private String eventKey;
	
	public MessageEvent() {
		this(null, null);
	}

	public MessageEvent(String event, String eventKey) {
		super();
		this.event = event;
		this.eventKey = eventKey;
		setMsgType(MSGTYPE_EVENT);
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
}
