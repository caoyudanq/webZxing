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
	//获得数据库连接
		Connection conn = DBUtil.getConnection();
		//拼写sql语句
		String sqlString ="insert into signinformation"
							+"(userName,location,panelId,fireStatus,scanTime,secretMes,remarks)"
							+"values(?,?,?,?,?,?,?)";//占位符，在下面的Sql语句预编译的时候把参数传进去
		//预编译sql语句
		PreparedStatement ptmy = conn.prepareStatement(sqlString);
		//传参，为预编译符赋值
		ptmy.setString(1, s.getUserName());
		ptmy.setString(2,s.getLocation());
		ptmy.setString(3,s.getPanelId());
		ptmy.setString(4, s.getFireStatus());
		ptmy.setString(5, s.getScanTime());
		ptmy.setString(6, s.getSecretMes());
		ptmy.setString(7, s.getRemarks());
		//执行
		ptmy.execute();
	}
	
	
	public void delInformation(Integer id) throws Exception {
//获得数据库连接
		Connection conn = DBUtil.getConnection();

		//拼写sql语句
		String sqlString =""+
							" delete from signinformation"+
							" where id = ?";
							//占位符，在下面的Sql语句预编译的时候把参数传进去
		//预编译sql语句
		PreparedStatement ptmy = conn.prepareStatement(sqlString);
		//传参，为预编译符赋值
		ptmy.setInt(1, id);

		//执行
		ptmy.execute();
		
	}
	public void updateInformation(SignInFormationUtil s) throws Exception {
//获得数据库连接
		Connection conn = DBUtil.getConnection();
		//拼写sql语句
		String sqlString =""+
							" update signinformation"+
							" set userName=?,location=?,fireStatus=?,scanTime=?,secretMes=?,remarks=?"+
							" where id=?";
							//占位符，在下面的Sql语句预编译的时候把参数传进去
		//预编译sql语句
		PreparedStatement ptmy = conn.prepareStatement(sqlString);
		//传参，为预编译符赋值
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
		//小技巧
		sBuilder.append("select * from signinformation where 1=1");
		if(params != null &&params.size()>0) {
			for (int i = 0; i < params.size(); i++) {
				Map<String, Object> map=params.get(i);
				sBuilder.append(" and "+map.get("name")+" "+map.get("rela")+" "+map.get("value"));
			}
		}
		PreparedStatement ptmtPreparedStatement  = conn.prepareStatement(sBuilder.toString());
		System.out.println(sBuilder.toString());
		//执行
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
