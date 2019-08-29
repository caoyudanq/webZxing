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
		//������ݿ�����
			Connection conn = DBUtil.getConnection();
			//ƴдsql���
			String sqlString ="insert into user1"
								+"(userName,password,phone)"
								+"values(?,?,?)";//ռλ�����������Sql���Ԥ�����ʱ��Ѳ�������ȥ
			//Ԥ����sql���
			PreparedStatement ptmy = conn.prepareStatement(sqlString);
			//���Σ�ΪԤ�������ֵ
			ptmy.setString(1, s.getUserName());
			ptmy.setString(2,s.getPassword());
			ptmy.setString(3,s.getPhone());
			//ִ��
			ptmy.execute();
		}
		
		
		public void delInformation(Integer id) throws Exception {
			//������ݿ�����
			Connection conn = DBUtil.getConnection();

			//ƴдsql���
			String sqlString =""+
								" delete from user1"+
								" where id = ?";
								//ռλ�����������Sql���Ԥ�����ʱ��Ѳ�������ȥ
			//Ԥ����sql���
			PreparedStatement ptmy = conn.prepareStatement(sqlString);
			//���Σ�ΪԤ�������ֵ
			ptmy.setInt(1, id);

			//ִ��
			ptmy.execute();
			
		}
		public void updateInformation(UserUtil s) throws Exception {
	//������ݿ�����
			Connection conn = DBUtil.getConnection();
			//ƴдsql���
			String sqlString =""+
								" update user1"+
								" set userName=?,password=?,phone=?"+
								" where id=?";
								//ռλ�����������Sql���Ԥ�����ʱ��Ѳ�������ȥ
			//Ԥ����sql���
			PreparedStatement ptmy = conn.prepareStatement(sqlString);
			//���Σ�ΪԤ�������ֵ
			ptmy.setString(1, s.getUserName());
			ptmy.setString(2,s.getPassword());
			ptmy.setString(3, s.getPhone());
			ptmy.execute();		
		}

		
		public List<UserUtil> queryInformation(List<Map<String, Object>> params) throws Exception {
			List<UserUtil> gs = new ArrayList<UserUtil>();
			
			Connection conn = DBUtil.getConnection();

			StringBuilder sBuilder =new StringBuilder();
			//С����
			sBuilder.append("select * from user1 where 1=1");
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
			//ִ��
			ResultSet rs=ptmy.executeQuery();	
			UserUtil g = null;	
			while (rs.next()) {	
				g = new UserUtil();
				g.setUserName(rs.getString("userName"));
				g.setPassword(rs.getString("password"));
				g.setPhone(rs.getString("phone"));
			}
			return g;	
			
			
//			//������ݿ�����
//			Connection conn = DBUtil.getConnection();
//			//ƴдsql���
//			String sqlString ="select * from sensorInformation where userName = ? and password = ?";
//								//ռλ�����������Sql���Ԥ�����ʱ��Ѳ�������ȥ
//			//Ԥ����sql���
//			PreparedStatement ptmy = conn.prepareStatement(sqlString);
//			
//			ptmy.setString(1, userName);
//			ptmy.setString(2, password);
//			
//			//ִ��
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
