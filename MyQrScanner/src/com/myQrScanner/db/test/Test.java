package com.myQrScanner.db.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.myQrScanner.db.action.InformationAction;
import com.myQrScanner.db.action.PanelInfoAction;
import com.myQrScanner.db.model.FirePanelInfoUtil;
import com.myQrScanner.db.model.SignInFormationUtil;
import com.myQrScanner.db.operateDB.OperateFirePanelDB;
import com.myQrScanner.db.operateDB.OperateSignInFormation;


public class Test {
	
	public static void main(String[] args) throws Exception {
//		PanelInfoAction informationAction = new PanelInfoAction();
//		informationAction.del(2);
			
		OperateFirePanelDB operateFirePanelDB = new OperateFirePanelDB();
		
		FirePanelInfoUtil firePanelInfoUtil = new FirePanelInfoUtil();
		PanelInfoAction panelInfoAction = new PanelInfoAction();
		firePanelInfoUtil = panelInfoAction.queryByFireDetails("00000");
		System.out.println(firePanelInfoUtil.toString());
		
//		List<Map<String, Object>> params= new ArrayList<Map<String,Object>>();
//		Map<String, Object> param = new HashMap<String, Object>();
//		
//		param.put("name", "secret");
//		param.put("rela", "=");
//		param.put("value", "'666'");//注意此处要加一个单引号
//		params.add(param);
//		
//		List<FirePanelInfoUtil> result = operateFirePanelDB.queryInformation(params); 
//		for (int i = 0; i < result.size(); i++) {
//			System.out.println(result.get(i).getId());	
//		}
		
		
//		InformationAction informationAction = new InformationAction();
//		informationAction.del(2);
		
//		OperateSignInFormation operateSignInFormation = new OperateSignInFormation();
//		List<Map<String, Object>> params= new ArrayList<Map<String,Object>>();
//		Map<String, Object> param = new HashMap<String, Object>();
//		
//		param.put("name", "username");
//		param.put("rela", "=");
//		param.put("value", "'黄'");//注意此处要加一个单引号
//		params.add(param);	
//		
//		List<SignInFormationUtil> result = operateSignInFormation.queryInformation(params); 
//		for (int i = 0; i < result.size(); i++) {
//			System.out.println(result.get(i).toString());	
//		}
	}


}
