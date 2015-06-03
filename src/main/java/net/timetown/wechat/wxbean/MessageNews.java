package net.timetown.wechat.wxbean;

import java.util.ArrayList;
import java.util.List;

public class MessageNews extends Message {
	
	private List<Item> items = new ArrayList<MessageNews.Item>();
	private int size = 1;
	
	public MessageNews() {
		super();
		setMsgType(MSGTYPE_NEWS);
	}
	
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

	public class Item {
		private String title;
		private String description;
		private String picUrl;
		private String url;
		
		public Item(String title, String description, String picUrl, String url) {
			super();
			this.title = title;
			this.description = description;
			this.picUrl = picUrl;
			this.url = url;
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
		public String getPicUrl() {
			return picUrl;
		}
		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
	}
}
