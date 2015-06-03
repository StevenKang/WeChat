package net.timetown.wechat.wxbean;

public class MessageMusic extends Message {
	
	
	private String title;
	private String description;
	private String musicUrl;
	private String hqMusicUrl;
	
	public MessageMusic() {
		this(null, null, null, null);
	}
	
	public MessageMusic(String title, String description, String musicUrl,
			String hqMusicUrl) {
		super();
		this.title = title;
		this.description = description;
		this.musicUrl = musicUrl;
		this.hqMusicUrl = hqMusicUrl;
		setMsgType(MSGTYPE_MUSIC);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMusicUrl() {
		return musicUrl;
	}
	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}
	public String getHqMusicUrl() {
		return hqMusicUrl;
	}
	public void setHqMusicUrl(String hqMusicUrl) {
		this.hqMusicUrl = hqMusicUrl;
	}
}
