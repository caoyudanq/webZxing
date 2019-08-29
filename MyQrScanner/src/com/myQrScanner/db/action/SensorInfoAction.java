package com.myQrScanner.db.action;

import java.util.List;
import java.util.Map;

import com.myQrScanner.db.model.FirePanelInfoUtil;
import com.myQrScanner.db.operateDB.OperateFirePanelDB;

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
	public void add(FirePanelInfoUtil f) throws Exception {
		OperateFirePanelDB operate = new OperateFirePanelDB();
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
	public void update(FirePanelInfoUtil f) throws Exception {
		OperateFirePanelDB operate = new OperateFirePanelDB();
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
		OperateFirePanelDB operate = new OperateFirePanelDB();
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
	public FirePanelInfoUtil queryByFireDetails(String fireDetails ) throws Exception {
		OperateFirePanelDB operate = new OperateFirePanelDB();
		return operate.queryByFireDetails(fireDetails);	
	}
	
	/**
	 * 
	 * @Title: query
	 * @Description: ��ѯһ����Ϣ����
	 * @param: @param params
	 * @param: @return
	 * @param: @throws Exception   
	 * @return: List<FirePanelInfoUtil>   
	 * @throws
	 */
	public List<FirePanelInfoUtil> query(List<Map<String, Object>> params) throws Exception {
		OperateFirePanelDB operate = new OperateFirePanelDB();
		return operate.queryInformation(params);	
	}

}
