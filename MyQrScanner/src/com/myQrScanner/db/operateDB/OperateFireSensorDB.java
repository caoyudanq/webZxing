package com.myQrScanner.db.operateDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.myQrScanner.db.getDB.DBUtil;
import com.myQrScanner.db.model.FirePanelInfoUtil;
import com.myQrScanner.db.model.FireSensorInfoUtil;

public class OperateFireSensorDB {
	
	public void addInformation(FireSensorInfoUtil f) throws Exception {
		//获得数据库连接
			Connection conn = DBUtil.getConnection();
			//拼写sql语句
			String sqlString ="insert into sensorInformation"
								+"(id,sensorId,xPosition,yPosition)"
								+"values(?,?,?,?)";//占位符，在下面的Sql语句预编译的时候把参数传进去
			//预编译sql语句
			PreparedStatement ptmy = conn.prepareStatement(sqlString);
			//传参，为预编译符赋值
			ptmy.setInt(1, f.getId());
			ptmy.setString(2,f.getSensorId());
			ptmy.setDouble(3, f.getxPosition());
			ptmy.setDouble(4, f.getyPosition());
			//执行
			ptmy.execute();
		}
		
		
		public void delInformation(Integer id) throws Exception {
	//获得数据库连接
			Connection conn = DBUtil.getConnection();

			//拼写sql语句
			String sqlString =""+
								" delete from sensorInformation"+
								" where id = ?";
								//占位符，在下面的Sql语句预编译的时候把参数传进去
			//预编译sql语句
			PreparedStatement ptmy = conn.prepareStatement(sqlString);
			//传参，为预编译符赋值
			ptmy.setInt(1, id);
			//执行
			ptmy.execute();
		}
		
		
		public void updateInformation(FireSensorInfoUtil f) throws Exception {
	//获得数据库连接
			Connection conn = DBUtil.getConnection();
			//拼写sql语句
			String sqlString =""+
								" update sensorInformation"+
								" set id=?,sensorId=?,xPosition=?,yPosition=?"+
								" where id=?";
								//占位符，在下面的Sql语句预编译的时候把参数传进去
			//预编译sql语句
			PreparedStatement ptmy = conn.prepareStatement(sqlString);
			//传参，为预编译符赋值
			ptmy.setInt(1, f.getId());
			ptmy.setString(2,f.getSensorId());
			ptmy.setDouble(3, f.getxPosition());
			ptmy.setDouble(4, f.getyPosition());
			ptmy.execute();		
		}
		
		public FireSensorInfoUtil queryByFireDetails(String sensorId) throws Exception  {
			//获得数据库连接
			Connection conn = DBUtil.getConnection();
			//拼写sql语句
			String sqlString ="select * from sensorInformation where sensorId = ? ";
								//占位符，在下面的Sql语句预编译的时候把参数传进去
			//预编译sql语句
			PreparedStatement ptmy = conn.prepareStatement(sqlString);
			ptmy.setString(1, sensorId);
			
			//执行
			ResultSet rs = ptmy.executeQuery();	
			FireSensorInfoUtil fireSensorInfoUtil =null;
			while (rs.next()) {	
				fireSensorInfoUtil = new FireSensorInfoUtil();
				fireSensorInfoUtil.setId(rs.getInt("id"));
				fireSensorInfoUtil.setSensorId(rs.getString("sensorId"));
				fireSensorInfoUtil.setxPosition(rs.getDouble("xPosition"));
				fireSensorInfoUtil.setyPosition(rs.getDouble("yPosition"));
				
			}		
			return fireSensorInfoUtil ;		
		}

		
		public List<FireSensorInfoUtil> queryInformation(List<Map<String, Object>> params) throws Exception {
			List<FireSensorInfoUtil> gs = new ArrayList<FireSensorInfoUtil>();
			
			Connection conn = DBUtil.getConnection();

			StringBuilder sBuilder =new StringBuilder();
			//小技巧
			sBuilder.append("select * from sensorInformation where 1=1");
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
			FireSensorInfoUtil g = null;	
			while (rs.next()) {	
				g = new FireSensorInfoUtil();
				g.setId(rs.getInt("id"));
				g.setSensorId(rs.getString("secret"));
				g.setxPosition(rs.getDouble("xPosition"));
				g.setyPosition(rs.getDouble("yPosition"));
				gs.add(g);		
//				System.out.println(rs.getString("user_name"));
			}
			return gs;	
		}

}
