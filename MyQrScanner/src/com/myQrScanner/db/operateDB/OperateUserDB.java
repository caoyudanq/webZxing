package com.myQrScanner.db.operateDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.myQrScanner.db.getDB.DBUtil;
import com.myQrScanner.db.model.FireSensorInfoUtil;
import com.myQrScanner.db.model.UserUtil;

public class OperateUserDB {
	public void addInformation(UserUtil s) throws Exception {
		//获得数据库连接
			Connection conn = DBUtil.getConnection();
			//拼写sql语句
			String sqlString ="insert into user1"
								+"(userName,password,phone)"
								+"values(?,?,?)";//占位符，在下面的Sql语句预编译的时候把参数传进去
			//预编译sql语句
			PreparedStatement ptmy = conn.prepareStatement(sqlString);
			//传参，为预编译符赋值
			ptmy.setString(1, s.getUserName());
			ptmy.setString(2,s.getPassword());
			ptmy.setString(3,s.getPhone());
			//执行
			ptmy.execute();
		}
		
		
		public void delInformation(Integer id) throws Exception {
			//获得数据库连接
			Connection conn = DBUtil.getConnection();

			//拼写sql语句
			String sqlString =""+
								" delete from user1"+
								" where id = ?";
								//占位符，在下面的Sql语句预编译的时候把参数传进去
			//预编译sql语句
			PreparedStatement ptmy = conn.prepareStatement(sqlString);
			//传参，为预编译符赋值
			ptmy.setInt(1, id);

			//执行
			ptmy.execute();
			
		}
		public void updateInformation(UserUtil s) throws Exception {
	//获得数据库连接
			Connection conn = DBUtil.getConnection();
			//拼写sql语句
			String sqlString =""+
								" update user1"+
								" set userName=?,password=?,phone=?"+
								" where id=?";
								//占位符，在下面的Sql语句预编译的时候把参数传进去
			//预编译sql语句
			PreparedStatement ptmy = conn.prepareStatement(sqlString);
			//传参，为预编译符赋值
			ptmy.setString(1, s.getUserName());
			ptmy.setString(2,s.getPassword());
			ptmy.setString(3, s.getPhone());
			ptmy.execute();		
		}

		
		public List<UserUtil> queryInformation(List<Map<String, Object>> params) throws Exception {
			List<UserUtil> gs = new ArrayList<UserUtil>();
			
			Connection conn = DBUtil.getConnection();

			StringBuilder sBuilder =new StringBuilder();
			//小技巧
			sBuilder.append("select * from user1 where 1=1");
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
			UserUtil g = null;	
			while (rs.next()) {	
				g = new UserUtil();
				g.setUserName(rs.getString("userName"));
				g.setPassword(rs.getString("password"));
				g.setPhone(rs.getString("phone"));
				gs.add(g);		
//				System.out.println(rs.getString("user_name"));
			}
			return gs;	
		}
		
		public UserUtil getUserByName(String userName,String password) throws Exception {
			
			Connection conn = DBUtil.getConnection();

			String sqlString ="select * from user1 where userName=? and password=?";
			PreparedStatement ptmy  = conn.prepareStatement(sqlString);

			ptmy.setString(1, userName);
			ptmy.setString(2, password);
			//执行
			ResultSet rs=ptmy.executeQuery();	
			UserUtil g = null;	
			while (rs.next()) {	
				g = new UserUtil();
				g.setUserName(rs.getString("userName"));
				g.setPassword(rs.getString("password"));
				g.setPhone(rs.getString("phone"));
			}
			return g;	
			
			
//			//获得数据库连接
//			Connection conn = DBUtil.getConnection();
//			//拼写sql语句
//			String sqlString ="select * from sensorInformation where userName = ? and password = ?";
//								//占位符，在下面的Sql语句预编译的时候把参数传进去
//			//预编译sql语句
//			PreparedStatement ptmy = conn.prepareStatement(sqlString);
//			
//			ptmy.setString(1, userName);
//			ptmy.setString(2, password);
//			
//			//执行
//			ResultSet rs = ptmy.executeQuery();	
//			UserUtil userUtil =null;
//			while (rs.next()) {	
//				userUtil = new UserUtil();
//				userUtil.setUserName(rs.getString("userName"));
//				userUtil.setPassword("password");
//				userUtil.setPhone("phone");		
//			}		
//			return userUtil ;					
		}
}
