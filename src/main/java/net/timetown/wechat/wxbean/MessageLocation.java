package net.timetown.wechat.wxbean;

public class MessageLocation extends Message {
	
	private Double locationX;
	private Double locationY;
	private Integer scale;
	private String label;
	
	public MessageLocation() {
		this(null, null, null, null);
	}

	public MessageLocation(Double locationX, Double locationY, Integer scale,
			String label) {
		super();
		this.locationX = locationX;
		this.locationY = locationY;
		this.scale = scale;
		this.label = label;
		setMsgType(MSGTYPE_LOCATION);
	}

	public Double getLocationX() {
		return locationX;
	}

	public void setLocationX(Double locationX) {
		this.locationX = locationX;
	}

	public Double getLocationY() {
		return locationY;
	}

	public void setLocationY(Double locationY) {
		this.locationY = locationY;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[locationX: "+locationX+", locationY: "+locationY+", scale: "+scale+", label: "+label+"]";
	}
}
