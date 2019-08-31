package com.myQrScanner.db.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class SensorDangerousMes {
	public ArrayList<Sensor> sensors;

	public Double xPostion;
	public Double yPostion;
	public String status;
	public String url;
	public int num;
	

	public SensorDangerousMes() {
		super();
	}

	public SensorDangerousMes(ArrayList<Sensor> sensors, Double xPostion, Double yPostion, String status, String url,
			int num) {
		super();
		this.sensors = sensors;
		this.xPostion = xPostion;
		this.yPostion = yPostion;
		this.status = status;
		this.url = url;
		this.num = num;
	}

	

	public Double getxPostion() {
		return xPostion;
	}

	public Double getyPostion() {
		return yPostion;
	}

	public String getStatus() {
		return status;
	}

	public String getUrl() {
		return url;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}




	public void setStatus(String status) {
		this.status = status;
	}


	public void setUrl(String url) {
		this.url = url;
	}



	public ArrayList<Sensor> getSensors() {
		return sensors;
	}

	public void setSensors(ArrayList<Sensor> sensors) {
		this.sensors = sensors;
	}

	public void setxPostion(Double xPostion) {
		this.xPostion = xPostion;
	}

	public void setyPostion(Double yPostion) {
		this.yPostion = yPostion;
	}



	@Override
	public String toString() {
		return "SensorDangerousMes [sensors=" + sensors + ", xPostion=" + xPostion + ", yPostion=" + yPostion
				+ ", status=" + status + ", url=" + url + "]";
	}	

}
