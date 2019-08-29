package com.myQrScanner.db.action;

import java.util.List;
import java.util.Map;

import com.myQrScanner.db.model.SignInFormationUtil;
import com.myQrScanner.db.operateDB.OperateSignInFormation;


public class InformationAction {
	/**
	 * 
	 * @Title: add
	 * @Description: 新增信息对象
	 * @param: @param s
	 * @param: @throws Exception   
	 * @return: void   
	 * @throws
	 */
	public void add(SignInFormationUtil s) throws Exception {
		OperateSignInFormation operate = new OperateSignInFormation();
		operate.addInformation(s);	
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
	public void update(SignInFormationUtil s) throws Exception {
		OperateSignInFormation operate = new OperateSignInFormation();
		operate.updateInformation(s);
		
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
		OperateSignInFormation operate = new OperateSignInFormation();
		operate.delInformation(id);
	}
	
	/**
	 * 
	 * @Title: query
	 * @Description: 查询一组信息对象
	 * @param: @param params
	 * @param: @return
	 * @param: @throws Exception   
	 * @return: List<SignInFormationUtil>   
	 * @throws
	 */
	public List<SignInFormationUtil> query(List<Map<String, Object>> params) throws Exception {
		OperateSignInFormation operate = new OperateSignInFormation();
		return operate.queryInformation(params);	
	}
	
//	public static void main(String[] args) throws Exception {
//	OperateSignInFormation g = new OperateSignInFormation();
//	List<Map<String, Object>> params= new ArrayList<Map<String,Object>>();
//	Map<String, Object> param = new HashMap<String, Object>();
//	param.put("name", "user_name");
//	param.put("rela", "=");
//	param.put("value", "'小四'");//注意此处要加一个单引号
//	params.add(param);
//	
//	//模糊查询
//	param.put("name", "user_name");
//	param.put("rela", "like");
//	param.put("value", "'%小%'");
//	params.add(param);
//	
//	
//	List<SignInFormationUtil> result = g.get(params); 
//	for (int i = 0; i < result.size(); i++) {
//		System.out.println(result.get(i).toString());	
//	}
//	
//	
////	OperateSignInFormation g = new OperateSignInFormation();
////	List<SignInFormationUtil> gs = g.get("小四");
////	for (int i = 0; i < gs.size(); i++) {
////		System.out.println(gs.get(i).toString());	
////	}
////	for (SignInFormationUtil SignInFormationUtil : gs) {
////			
////	}
//
////	OperateSignInFormation g = new OperateSignInFormation();
////	SignInFormationUtil gSignInFormationUtil=new SignInFormationUtil();		
////	gSignInFormationUtil.setuser_name("新小二");
////	gSignInFormationUtil.setSex(12);
////	gSignInFormationUtil.setCreate_date(new Date());
////	gSignInFormationUtil.setId(11);
//	
////	SignInFormationUtil mySignInFormationUtil =g.getSignInFormationUtil(6);
////	System.out.println(mySignInFormationUtil.toString());
//	
////	g.deSignInFormationUtil(2);
////	g.updateSignInFormationUtil(gSignInFormationUtil);
////	g.addSignInFormationUtil(gSignInFormationUtil);
//	
//}
}
