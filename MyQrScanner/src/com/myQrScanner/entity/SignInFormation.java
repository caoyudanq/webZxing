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
import com.myQrScanner.db.model.FirePanelInfoUtil;
import com.myQrScanner.db.model.HisMes;
import com.myQrScanner.db.model.SignInFormationUtil;
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
	
	public String receiveMessage() throws Exception {
		qqq++;
		System.out.println("1111111111111111111111111111111qqq="+qqq);
		
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
	 * @Title: responseMes
	 * @Description: TODO
	 * @param:    
	 * @return: void   
	 * @throws
	 */
	private void responseMes(String status) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		JSONObject json=new JSONObject();
		
		
		if(status.equals("SUCCESS")) {
				json.put("status", "\""+status+"\"");			
				json.put("url", "\""+getUrlString()+"\"");
				json.put("xPosition", "\""+xPosition+"\"");
				json.put("yPosition", "\""+yPosition+"\"");		
				System.out.println("ǩ���ɹ����޻� json= "+json.toString());
		}
		else if (status.equals("DANGEROUS")) {
			json.put("status", "\""+status+"\"");			
			json.put("url", "\""+getUrlString()+"\"");
			json.put("xPosition", "\""+xPosition+"\"");
			json.put("yPosition", "\""+yPosition+"\"");		
			
			Integer num = Integer.valueOf(fireStatus.substring(1, 4));
			json.put("fireNum", "\""+num+"\"");
			
			if(num<=3) {
				for(int i = 0;i<num;i++) {
					int fireId = Integer.valueOf(fireStatus.substring(4+i*3, 7+i*3));
					json.put("fire"+i, "\""+fireId+"\"");	
				}
			}
			else {
				for(int i = 0;i<3;i++) {
					int fireId = Integer.valueOf(fireStatus.substring(4+i*3, 7+i*3));
					json.put("fire"+i, "\""+fireId+"\"");					
				}				
			}
			
		}
		else {
				json.put("status", "\""+status+"\"");
		}
		
		try {
			PrintWriter writer = response.getWriter();			
			writer.write(json.toString());
			writer.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
