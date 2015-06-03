package net.timetown.wechat.wxbean;

public class MessageImage extends Message {
	
	private String picUrl;
	private String mediaId;
	
	public MessageImage() {
		this(null);
	}
	
	public MessageImage(String picUrl) {
		super();
		this.picUrl = picUrl;
		setMsgType(MSGTYPE_IMAGE);
	}

	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	
}
