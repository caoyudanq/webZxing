package com.myQrScanner.db.model;

public class Sensor {
	private String sensorId;
	private Double xSensorPostion;
	private Double ySensorPostion;
	
	
	
	public Sensor() {
		super();
	}
	public Sensor(String sensorId, Double xSensorPostion, Double ySensorPostion) {
		super();
		this.sensorId = sensorId;
		this.xSensorPostion = xSensorPostion;
		this.ySensorPostion = ySensorPostion;
	}
	public String getSensorId() {
		return sensorId;
	}
	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}
	public Double getxSensorPostion() {
		return xSensorPostion;
	}
	public void setxSensorPostion(Double xSensorPostion) {
		this.xSensorPostion = xSensorPostion;
	}
	public Double getySensorPostion() {
		return ySensorPostion;
	}
	public void setySensorPostion(Double ySensorPostion) {
		this.ySensorPostion = ySensorPostion;
	}
	@Override
	public String toString() {
		return "Sensor [sensorId=" + sensorId + ", xSensorPostion=" + xSensorPostion + ", ySensorPostion="
				+ ySensorPostion + "]";
	}
	
	
	
	

}
