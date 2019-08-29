package com.myQrScanner.db.model;



public class FirePanelInfoUtil {
	private Integer id;
	private String secret;
	private Double xPosition;
	private Double yPosition;
	private Integer floor;
	private Integer num;
	private String panelId;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public Double getxPosition() {
		return xPosition;
	}
	public void setxPosition(Double xPosition) {
		this.xPosition = xPosition;
	}
	public Double getyPosition() {
		return yPosition;
	}
	public void setyPosition(Double yPosition) {
		this.yPosition = yPosition;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}

	
	public String getPanelId() {
		return panelId;
	}
	public void setPanelId(String panelId) {
		this.panelId = panelId;
	}
	@Override
	public String toString() {
		return "FirePanelInfoUtil [id=" + id + ", secret=" + secret + ", xPosition=" + xPosition + ", yPosition="
				+ yPosition + ", floor=" + floor + ", fireDetails=" + panelId + "]";
	}
	
	
	
	
	

}
