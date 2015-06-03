package net.timetown.wechat.wxbean;

public class MessageText extends Message {
	
	private String content;
	
	public MessageText() {
		this(null);
	}
	
	public MessageText(String content) {
		super();
		this.content = content;
		setMsgType(MSGTYPE_TEXT);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
