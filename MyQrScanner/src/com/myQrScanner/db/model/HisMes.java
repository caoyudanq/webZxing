package com.myQrScanner.db.model;

public class HisMes {
	private String location;
	private String fireStatus;//»ðÔÖÐÅÏ¢
	private String scanTime;//É¨ÂëÊ±¼ä
	private String remarks;//±¸×¢
	private String panelId;//Â¥ÏÔ±àºÅ
	
	
	public HisMes() {
		super();
	}
	public HisMes(String location, String fireStatus, String scanTime, String remarks, String panelId) {
		super();
		this.location = location;
		this.fireStatus = fireStatus;
		this.scanTime = scanTime;
		this.remarks = remarks;
		this.panelId = panelId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getFireStatus() {
		return fireStatus;
	}
	public void setFireStatus(String fireStatus) {
		this.fireStatus = fireStatus;
	}
	public String getScanTime() {
		return scanTime;
	}
	public void setScanTime(String scanTime) {
		this.scanTime = scanTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPanelId() {
		return panelId;
	}
	public void setPanelId(String panelId) {
		this.panelId = panelId;
	}
	

}
