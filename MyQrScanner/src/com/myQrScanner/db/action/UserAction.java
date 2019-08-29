package com.myQrScanner.db.action;

import java.util.List;
import java.util.Map;

import com.myQrScanner.db.model.FirePanelInfoUtil;
import com.myQrScanner.db.model.UserUtil;
import com.myQrScanner.db.operateDB.OperateFirePanelDB;
import com.myQrScanner.db.operateDB.OperateUserDB;
import com.myQrScanner.entity.User;

public class UserAction {
	/**
	 * 
	 * @Title: add
	 * @Description: 新增信息对象
	 * @param: @param s
	 * @param: @throws Exception   
	 * @return: void   
	 * @throws
	 */
	public void add(UserUtil f) throws Exception {
		OperateUserDB operate = new OperateUserDB();
		operate.addInformation(f);	
	}
	/**
	 * 
	 * @Title: update
	 * @Description: 更新信息对象
	 * @param: @param s
	 * @param: @throws Exception   
	 * @return: void   
	 * @throws
	 */
	public void update(UserUtil f) throws Exception {
		OperateUserDB operate = new OperateUserDB();
		operate.updateInformation(f);
		
	}
	/**
	 * 
	 * @Title: del
	 * @Description: 删除一条信息对象
	 * @param: @param id
	 * @param: @throws Exception   
	 * @return: void   
	 * @throws
	 */
	public void del(Integer id) throws Exception {
		OperateUserDB operate = new OperateUserDB();
		operate.delInformation(id);
	}
	
//	/**
//	 * 
//	 * @Title: queryByFireDetails
//	 * @Description: 查询一个对象
//	 * @param: @param fireDetails
//	 * @param: @return
//	 * @param: @throws Exception   
//	 * @return: FirePanelInfoUtil   
//	 * @throws
//	 */
//	public UserUtil queryByFireDetails(String fireDetails ) throws Exception {
//		OperateUserDB operate = new OperateUserDB();
//		return operate.queryByFireDetails(fireDetails);	
//	}
	
	/**
	 * 
	 * @Title: query
	 * @Description: 查询一组信息对象
	 * @param: @param params
	 * @param: @return
	 * @param: @throws Exception   
	 * @return: List<UserUtil>   
	 * @throws
	 */
	public List<UserUtil> query(List<Map<String, Object>> params) throws Exception {
		OperateUserDB operate = new OperateUserDB();
		return operate.queryInformation(params);	
	}
	
	public UserUtil getUserByUserNameAndPassword (String userName,String password) throws Exception {
		OperateUserDB operate = new OperateUserDB();
		return operate.getUserByName(userName,password);
		
	}

}
