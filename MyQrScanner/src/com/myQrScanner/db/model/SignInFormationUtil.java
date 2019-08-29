package com.myQrScanner.db.model;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

public class SignInFormationUtil extends ActionSupport {
	private Integer id;
	private String userName;
	private String location;
	private String fireStatus;//火灾信息
	private String scanTime;//扫码时间
	private String secretMes;//加密信息
	private String remarks;//备注
	private String panelId;//楼显编号

	//private int num;//火灾显示盘个数
	
	
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getSecretMes() {
		return secretMes;
	}

	public void setSecretMes(String secretMes) {
		this.secretMes = secretMes;
	}

	public String getRemarks() {
		return remarks;
	}
	

	public SignInFormationUtil() {
		super();
	}

	public SignInFormationUtil(Integer id, String userName, String location, String fireStatus, String scanTime,
			String secretMes, String remarks, String panelId) {
		super();
		this.id = id;
		this.userName = userName;
		this.location = location;
		this.fireStatus = fireStatus;
		this.scanTime = scanTime;
		this.secretMes = secretMes;
		this.remarks = remarks;
		this.panelId = panelId;
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
	
	@Override
	public String toString() {
		return "SignInFormationUtil [id=" + id + ", userName=" + userName + ", location=" + location + ", fireStatus="
				+ fireStatus + ", scanTime=" + scanTime + ", secretMes=" + secretMes + ", remarks=" + remarks
				+ ", panelId=" + panelId +"]";
	}
	
	

}