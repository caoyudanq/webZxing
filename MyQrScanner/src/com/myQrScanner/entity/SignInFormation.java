package com.myQrScanner.entity;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.mail.search.IntegerComparisonTerm;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.myQrScanner.common.Constant;
import com.myQrScanner.db.action.InformationAction;
import com.myQrScanner.db.action.PanelInfoAction;
import com.myQrScanner.db.action.SensorInfoAction;
import com.myQrScanner.db.model.FirePanelInfoUtil;
import com.myQrScanner.db.model.FireSensorInfoUtil;
import com.myQrScanner.db.model.HisMes;
import com.myQrScanner.db.model.Sensor;
import com.myQrScanner.db.model.SensorDangerousMes;
import com.myQrScanner.db.model.SignInFormationUtil;
import com.myQrScanner.db.operateDB.OperateFireSensorDB;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SignInFormation extends ActionSupport {
    private String userName;//用户名
    private String location;//区域
    private String scanTime;//扫码时间
    private String secretMes;//加密信息
    private String fireStatus;//火灾信息
    private String remarks;//备注
    private String panelId;//楼显编号
    private Double xPosition;
    private Double yPosition;
    private String hisTime;
    private static int qqq = 0;
    SensorDangerousMes sensorsMes;
	
	public String receiveMessage() throws Exception {
		
		SignInFormationUtil signInFormationUtil;
	    signInFormationUtil = new SignInFormationUtil();
		signInFormationUtil.setUserName(userName);
		signInFormationUtil.setLocation(location);
		signInFormationUtil.setFireStatus(fireStatus);
		signInFormationUtil.setScanTime(scanTime);
		signInFormationUtil.setSecretMes(secretMes);
		signInFormationUtil.setRemarks(remarks);
		signInFormationUtil.setPanelId(panelId);
		System.out.println(signInFormationUtil.toString());
		
		InformationAction informationAction = new InformationAction();
		
		//查询火灾显示盘编号 对应的 火灾显示盘 信息
		FirePanelInfoUtil firePanelInfoUtil = new FirePanelInfoUtil();
		PanelInfoAction panelInfoAction = new PanelInfoAction();
		firePanelInfoUtil = panelInfoAction.queryByFireDetails(panelId);
				
		if(secretMes.equals(firePanelInfoUtil.getSecret())) {//通过加密算法
			informationAction.add(signInFormationUtil);	
			xPosition = firePanelInfoUtil.getxPosition();
			yPosition = firePanelInfoUtil.getyPosition();			
			/////////////////////////////////////////////////////是否有火警
			String fire = fireStatus.substring(0, 1);
			if(fire.equals("0")) {//正常，无火警
				responseMes("SUCCESS");
				System.out.println("签到成功，无火警"+signInFormationUtil.toString());
			}
			else if(fire.equals("1")){//有火警
				responseMes("DANGEROUS");
				System.out.println("签到成功，有火警"+signInFormationUtil.toString());			
			}
			else {
				responseMes("WRONGQR");	
				System.out.println("签到失败,二维码有误"+signInFormationUtil.toString());	
			}			
		}
		else {
			responseMes(Constant.FAIL);	
			System.out.println("签到失败"+signInFormationUtil.toString());
		}		
		return null;		
	}
	
	@SuppressWarnings("unused")
	public String getHisMsgByUserNameAndDay() throws Exception {
		System.out.println("查询到的signInFormationUtils为111111111");
		System.out.println("hisTime="+hisTime);
		InformationAction informationAction = new InformationAction();
		List<SignInFormationUtil> signInFormationUtils ;
		List<HisMes> hisMessages = new ArrayList<HisMes>();
		
		List<Map<String, Object>> params= new ArrayList<Map<String,Object>>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", "scanTime");
		param.put("rela", "like");
		param.put("value", "\""+hisTime.substring(0,10)+" %\"");
		params.add(param);
		Map<String, Object> param1 = new HashMap<String, Object>();
		param1.put("name", "userName");
		param1.put("rela", "=");
		param1.put("value", "\""+userName+"\"");
		params.add(param1);
		
		signInFormationUtils = informationAction.query(params);
		
	
		for(int i=0;i<signInFormationUtils.size();i++) {
			System.out.println("查询到的signInFormationUtils为"+signInFormationUtils.get(i).toString());
			HisMes hisMes = new HisMes(signInFormationUtils.get(i).getLocation(),
					signInFormationUtils.get(i).getFireStatus(),
					signInFormationUtils.get(i).getScanTime(),
					signInFormationUtils.get(i).getRemarks(),
					signInFormationUtils.get(i).getPanelId());
			hisMessages.add(hisMes);			
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		if(signInFormationUtils != null) {
			JSONArray jsonArray = JSONArray.fromObject(hisMessages); 
			System.out.println("传送jsonarray"+jsonArray.toString());	
			
			try {
				PrintWriter writer = response.getWriter();			
				writer.write(jsonArray.toString());
				writer.flush();			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}else {
			System.out.println("没有查找到历史记录");	
			return null;			
		}	
		return null;		
	}
	
	private String getUrlString() {
		String danyuan = getLocation().substring(0, 1);
		String loudong = getLocation().substring(3, 4);
		String ceng = getLocation().substring(6, 7);
		String quhao = getLocation().substring(8, 9);		
		StringBuilder sb = new StringBuilder();
        sb.append(danyuan+loudong+ceng+quhao);
        String urlString = sb.toString();	
		return urlString;	
	}

	/**
	 * @throws Exception 
	 * @Title: responseMes
	 * @Description: TODO
	 * @param:    
	 * @return: void   
	 * @throws
	 */
	@SuppressWarnings("unused")
	private String responseMes(String status) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
	//	JSONObject json=new JSONObject();	
	
		
		
		if(status.equals("SUCCESS")) {			
			sensorsMes = new SensorDangerousMes();
			sensorsMes.setStatus(status);
			sensorsMes.setUrl(getUrlString());
			sensorsMes.setxPostion(xPosition);
			sensorsMes.setyPostion(yPosition);
			JSONArray jsonArray = JSONArray.fromObject(sensorsMes); 
			System.out.println("签到成功，无火警 json= "+jsonArray.toString());
			try {
				PrintWriter writer = response.getWriter();			
				writer.write(jsonArray.toString());
				writer.flush();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;	
		}	
		
		else if (status.equals("DANGEROUS")) {
			Integer num = Integer.valueOf(fireStatus.substring(1, 4));
			sensorsMes = new SensorDangerousMes();
			sensorsMes.setStatus(status);
			sensorsMes.setUrl(getUrlString());
			sensorsMes.setxPostion(xPosition);
			sensorsMes.setyPostion(yPosition);
			sensorsMes.setNum(num);			
			OperateFireSensorDB operateFireSensorDB = new OperateFireSensorDB();	
			SensorInfoAction sensorInfoAction = new SensorInfoAction();			
			ArrayList<Sensor> sensors = new ArrayList<Sensor>();
			
			for(int i = 0;i<num;i++) {
				int fireId = Integer.valueOf(fireStatus.substring(4+i*3, 7+i*3));
				String sensorId = String.valueOf(fireId);
				System.out.println("sensorId="+sensorId);	
				
				FireSensorInfoUtil fireSensorInfoUtil ;	
				fireSensorInfoUtil = sensorInfoAction.queryBySensorId(sensorId);
				if(fireSensorInfoUtil != null) {
					Double xSensor = fireSensorInfoUtil.getxPosition();
					Double ySensor = fireSensorInfoUtil.getyPosition();					
					Sensor sensor = new Sensor(sensorId,xSensor,ySensor);
					sensors.add(sensor);
				}
				else {
					System.out.println("没有查到发生火警的传感器");	
					return null;				
				}
					
			}
			if(sensors != null) {
				JSONArray jsonArray = JSONArray.fromObject(sensorsMes); 
				sensorsMes.setSensors(sensors);
			    jsonArray = JSONArray.fromObject(sensorsMes); 
				System.out.println("传送sensors jsonarray"+jsonArray.toString());					
				try {
					PrintWriter writer = response.getWriter();			
					writer.write(jsonArray.toString());
					writer.flush();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
				
			}else {
				System.out.println("没有查到发生火警的传感器1");	
				return null;			
			}
			
		}
		else {
			System.out.println("二维码信息有误 ");
		}
		return null;
	}
	
	public String getHisTime() {
		return hisTime;
	}

	public void setHisTime(String hisTime) {
		this.hisTime = hisTime;
	}

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
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

	public String getFireStatus() {
		return fireStatus;
	}

	public void setFireStatus(String fireStatus) {
		this.fireStatus = fireStatus;
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
	


}
