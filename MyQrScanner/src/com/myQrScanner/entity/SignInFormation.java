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
    private String userName;//�û���
    private String location;//����
    private String scanTime;//ɨ��ʱ��
    private String secretMes;//������Ϣ
    private String fireStatus;//������Ϣ
    private String remarks;//��ע
    private String panelId;//¥�Ա��
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
		
		//��ѯ������ʾ�̱�� ��Ӧ�� ������ʾ�� ��Ϣ
		FirePanelInfoUtil firePanelInfoUtil = new FirePanelInfoUtil();
		PanelInfoAction panelInfoAction = new PanelInfoAction();
		firePanelInfoUtil = panelInfoAction.queryByFireDetails(panelId);
				
		if(secretMes.equals(firePanelInfoUtil.getSecret())) {//ͨ�������㷨
			informationAction.add(signInFormationUtil);	
			xPosition = firePanelInfoUtil.getxPosition();
			yPosition = firePanelInfoUtil.getyPosition();			
			/////////////////////////////////////////////////////�Ƿ��л�
			String fire = fireStatus.substring(0, 1);
			if(fire.equals("0")) {//�������޻�
				responseMes("SUCCESS");
				System.out.println("ǩ���ɹ����޻�"+signInFormationUtil.toString());
			}
			else if(fire.equals("1")){//�л�
				responseMes("DANGEROUS");
				System.out.println("ǩ���ɹ����л�"+signInFormationUtil.toString());			
			}
			else {
				responseMes("WRONGQR");	
				System.out.println("ǩ��ʧ��,��ά������"+signInFormationUtil.toString());	
			}			
		}
		else {
			responseMes(Constant.FAIL);	
			System.out.println("ǩ��ʧ��"+signInFormationUtil.toString());
		}		
		return null;		
	}
	
	@SuppressWarnings("unused")
	public String getHisMsgByUserNameAndDay() throws Exception {
		System.out.println("��ѯ����signInFormationUtilsΪ111111111");
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
			System.out.println("��ѯ����signInFormationUtilsΪ"+signInFormationUtils.get(i).toString());
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
			System.out.println("����jsonarray"+jsonArray.toString());	
			
			try {
				PrintWriter writer = response.getWriter();			
				writer.write(jsonArray.toString());
				writer.flush();			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}else {
			System.out.println("û�в��ҵ���ʷ��¼");	
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
			System.out.println("ǩ���ɹ����޻� json= "+jsonArray.toString());
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
					System.out.println("û�в鵽�����𾯵Ĵ�����");	
					return null;				
				}
					
			}
			if(sensors != null) {
				JSONArray jsonArray = JSONArray.fromObject(sensorsMes); 
				sensorsMes.setSensors(sensors);
			    jsonArray = JSONArray.fromObject(sensorsMes); 
				System.out.println("����sensors jsonarray"+jsonArray.toString());					
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
				System.out.println("û�в鵽�����𾯵Ĵ�����1");	
				return null;			
			}
			
		}
		else {
			System.out.println("��ά����Ϣ���� ");
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
