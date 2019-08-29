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
		//������ݿ�����
			Connection conn = DBUtil.getConnection();
			//ƴдsql���
			String sqlString ="insert into sensorInformation"
								+"(id,sensorId,xPosition,yPosition)"
								+"values(?,?,?,?)";//ռλ�����������Sql���Ԥ�����ʱ��Ѳ�������ȥ
			//Ԥ����sql���
			PreparedStatement ptmy = conn.prepareStatement(sqlString);
			//���Σ�ΪԤ�������ֵ
			ptmy.setInt(1, f.getId());
			ptmy.setString(2,f.getSensorId());
			ptmy.setDouble(3, f.getxPosition());
			ptmy.setDouble(4, f.getyPosition());
			//ִ��
			ptmy.execute();
		}
		
		
		public void delInformation(Integer id) throws Exception {
	//������ݿ�����
			Connection conn = DBUtil.getConnection();

			//ƴдsql���
			String sqlString =""+
								" delete from sensorInformation"+
								" where id = ?";
								//ռλ�����������Sql���Ԥ�����ʱ��Ѳ�������ȥ
			//Ԥ����sql���
			PreparedStatement ptmy = conn.prepareStatement(sqlString);
			//���Σ�ΪԤ�������ֵ
			ptmy.setInt(1, id);
			//ִ��
			ptmy.execute();
		}
		
		
		public void updateInformation(FireSensorInfoUtil f) throws Exception {
	//������ݿ�����
			Connection conn = DBUtil.getConnection();
			//ƴдsql���
			String sqlString =""+
								" update sensorInformation"+
								" set id=?,sensorId=?,xPosition=?,yPosition=?"+
								" where id=?";
								//ռλ�����������Sql���Ԥ�����ʱ��Ѳ�������ȥ
			//Ԥ����sql���
			PreparedStatement ptmy = conn.prepareStatement(sqlString);
			//���Σ�ΪԤ�������ֵ
			ptmy.setInt(1, f.getId());
			ptmy.setString(2,f.getSensorId());
			ptmy.setDouble(3, f.getxPosition());
			ptmy.setDouble(4, f.getyPosition());
			ptmy.execute();		
		}
		
		public FireSensorInfoUtil queryByFireDetails(String sensorId) throws Exception  {
			//������ݿ�����
			Connection conn = DBUtil.getConnection();
			//ƴдsql���
			String sqlString ="select * from sensorInformation where sensorId = ? ";
								//ռλ�����������Sql���Ԥ�����ʱ��Ѳ�������ȥ
			//Ԥ����sql���
			PreparedStatement ptmy = conn.prepareStatement(sqlString);
			ptmy.setString(1, sensorId);
			
			//ִ��
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
			//С����
			sBuilder.append("select * from sensorInformation where 1=1");
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
