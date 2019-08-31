package com.myQrScanner.db.action;

import java.util.List;
import java.util.Map;

import com.myQrScanner.db.model.FirePanelInfoUtil;
import com.myQrScanner.db.model.FireSensorInfoUtil;
import com.myQrScanner.db.operateDB.OperateFirePanelDB;
import com.myQrScanner.db.operateDB.OperateFireSensorDB;

public class SensorInfoAction {
	/**
	 * 
	 * @Title: add
	 * @Description: ������Ϣ����
	 * @param: @param s
	 * @param: @throws Exception   
	 * @return: void   
	 * @throws
	 */
	public void add(FireSensorInfoUtil f) throws Exception {
		OperateFireSensorDB operate = new OperateFireSensorDB();
		operate.addInformation(f);	
	}
	/**
	 * 
	 * @Title: update
	 * @Description: ������Ϣ����
	 * @param: @param s
	 * @param: @throws Exception   
	 * @return: void   
	 * @throws
	 */
	public void update(FireSensorInfoUtil f) throws Exception {
		OperateFireSensorDB operate = new OperateFireSensorDB();
		operate.updateInformation(f);
		
	}
	/**
	 * 
	 * @Title: del
	 * @Description: ɾ��һ����Ϣ����
	 * @param: @param id
	 * @param: @throws Exception   
	 * @return: void   
	 * @throws
	 */
	public void del(Integer id) throws Exception {
		OperateFireSensorDB operate = new OperateFireSensorDB();
		operate.delInformation(id);
	}
	
	/**
	 * 
	 * @Title: queryByFireDetails
	 * @Description: ��ѯһ������
	 * @param: @param fireDetails
	 * @param: @return
	 * @param: @throws Exception   
	 * @return: FirePanelInfoUtil   
	 * @throws
	 */
	public FireSensorInfoUtil queryBySensorId(String sensorId ) throws Exception {
		OperateFireSensorDB operate = new OperateFireSensorDB();
		return operate.queryBySensorId(sensorId);
	}
	
	/**
	 * 
	 * @Title: query
	 * @Description: ��ѯһ����Ϣ����
	 * @param: @param params
	 * @param: @return
	 * @param: @throws Exception   
	 * @return: List<FireSensorInfoUtil>   
	 * @throws
	 */
	public List<FireSensorInfoUtil> query(List<Map<String, Object>> params) throws Exception {
		OperateFireSensorDB operate = new OperateFireSensorDB();
		return operate.queryInformation(params);	
	}

}
