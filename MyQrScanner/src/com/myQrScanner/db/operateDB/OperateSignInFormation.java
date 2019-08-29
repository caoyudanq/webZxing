package com.myQrScanner.db.operateDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.myQrScanner.db.getDB.DBUtil;


import com.myQrScanner.db.model.SignInFormationUtil;

public class OperateSignInFormation {

	public void addInformation(SignInFormationUtil s) throws Exception {
	//������ݿ�����
		Connection conn = DBUtil.getConnection();
		//ƴдsql���
		String sqlString ="insert into signinformation"
							+"(userName,location,panelId,fireStatus,scanTime,secretMes,remarks)"
							+"values(?,?,?,?,?,?,?)";//ռλ�����������Sql���Ԥ�����ʱ��Ѳ�������ȥ
		//Ԥ����sql���
		PreparedStatement ptmy = conn.prepareStatement(sqlString);
		//���Σ�ΪԤ�������ֵ
		ptmy.setString(1, s.getUserName());
		ptmy.setString(2,s.getLocation());
		ptmy.setString(3,s.getPanelId());
		ptmy.setString(4, s.getFireStatus());
		ptmy.setString(5, s.getScanTime());
		ptmy.setString(6, s.getSecretMes());
		ptmy.setString(7, s.getRemarks());
		//ִ��
		ptmy.execute();
	}
	
	
	public void delInformation(Integer id) throws Exception {
//������ݿ�����
		Connection conn = DBUtil.getConnection();

		//ƴдsql���
		String sqlString =""+
							" delete from signinformation"+
							" where id = ?";
							//ռλ�����������Sql���Ԥ�����ʱ��Ѳ�������ȥ
		//Ԥ����sql���
		PreparedStatement ptmy = conn.prepareStatement(sqlString);
		//���Σ�ΪԤ�������ֵ
		ptmy.setInt(1, id);

		//ִ��
		ptmy.execute();
		
	}
	public void updateInformation(SignInFormationUtil s) throws Exception {
//������ݿ�����
		Connection conn = DBUtil.getConnection();
		//ƴдsql���
		String sqlString =""+
							" update signinformation"+
							" set userName=?,location=?,fireStatus=?,scanTime=?,secretMes=?,remarks=?"+
							" where id=?";
							//ռλ�����������Sql���Ԥ�����ʱ��Ѳ�������ȥ
		//Ԥ����sql���
		PreparedStatement ptmy = conn.prepareStatement(sqlString);
		//���Σ�ΪԤ�������ֵ
		ptmy.setString(1, s.getUserName());
		ptmy.setString(2,s.getLocation());
		ptmy.setString(3, s.getFireStatus());
		ptmy.setString(4, s.getScanTime());
		ptmy.setString(5, s.getSecretMes());
		ptmy.setString(6, s.getRemarks());
		ptmy.execute();		
	}

	
	public List<SignInFormationUtil> queryInformation(List<Map<String, Object>> params) throws Exception {
		List<SignInFormationUtil> gs = new ArrayList<SignInFormationUtil>();
		
		Connection conn = DBUtil.getConnection();

		StringBuilder sBuilder =new StringBuilder();
		//С����
		sBuilder.append("select * from signinformation where 1=1");
		if(params != null &&params.size()>0) {
			for (int i = 0; i < params.size(); i++) {
				Map<String, Object> map=params.get(i);
				sBuilder.append(" and "+map.get("name")+" "+map.get("rela")+" "+map.get("value"));
			}
		}
		PreparedStatement ptmtPreparedStatement  = conn.prepareStatement(sBuilder.toString());
		System.out.println(sBuilder.toString());
		//ִ��
		ResultSet rs=ptmtPreparedStatement.executeQuery();	
		SignInFormationUtil g = null;	
		while (rs.next()) {	
			g = new SignInFormationUtil();
			g.setUserName(rs.getString("userName"));
			g.setLocation(rs.getString("location"));
			g.setFireStatus(rs.getString("fireStatus"));
			g.setScanTime(rs.getString("scanTime"));
			g.setSecretMes(rs.getString("secretMes"));
			g.setRemarks(rs.getString("remarks"));
			g.setPanelId(rs.getString("panelId"));			
			g.setId(rs.getInt("id"));
			gs.add(g);		
//			System.out.println(rs.getString("user_name"));
		}
		return gs;	
	}

}
