package com.myQrScanner.db.model;

public class FireSensorInfoUtil {
	private Integer id;
	private Double xPosition;
	private Double yPosition;
	private String sensorId;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getSensorId() {
		return sensorId;
	}
	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}
	@Override
	public String toString() {
		return "FireSensorInfoUtil [id=" + id + ", xPosition=" + xPosition + ", yPosition=" + yPosition + ", sensorId="
				+ sensorId + "]";
	}
	

	
	
	
	

}
