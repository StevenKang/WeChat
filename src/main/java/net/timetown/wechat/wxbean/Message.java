package net.timetown.wechat.wxbean;

public class Message {

	public static final String MSGTYPE_TEXT = "text";
	public static final String MSGTYPE_LINK = "link";
	public static final String MSGTYPE_NEWS = "news";
	public static final String MSGTYPE_EVENT = "event";
	public static final String MSGTYPE_LOCATION = "location";
	public static final String MSGTYPE_IMAGE = "image";
	public static final String MSGTYPE_MUSIC = "music";

	private String toUserName;
	private String fromUserName;
	private Long createTime;
	private String msgType;
	private Long msgId;
	private Boolean response = true;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public Boolean getResponse() {
		return response;
	}

	public void setResponse(Boolean response) {
		this.response = response;
	}
	
}
