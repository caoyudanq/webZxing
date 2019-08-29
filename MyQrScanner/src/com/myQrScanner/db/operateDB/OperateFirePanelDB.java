package com.myQrScanner.db.operateDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.myQrScanner.db.getDB.DBUtil;
import com.myQrScanner.db.model.FirePanelInfoUtil;
import com.myQrScanner.db.model.SignInFormationUtil;

public class OperateFirePanelDB {
	
	public void addInformation(FirePanelInfoUtil f) throws Exception {
		//������ݿ�����
			Connection conn = DBUtil.getConnection();
			//ƴдsql���
			String sqlString ="insert into panelinformation"
								+"(id,secret,xPosition,yPosition,panelId)"
								+"values(?,?,?,?,?)";//ռλ�����������Sql���Ԥ�����ʱ��Ѳ�������ȥ
			//Ԥ����sql���
			PreparedStatement ptmy = conn.prepareStatement(sqlString);
			//���Σ�ΪԤ�������ֵ
			ptmy.setInt(1, f.getId());
			ptmy.setString(2,f.getSecret());
			ptmy.setDouble(3, f.getxPosition());
			ptmy.setDouble(4, f.getyPosition());
			ptmy.setString(5,f.getPanelId());
//			ptmy.setDate(3, new Date(s.getScanTime().getTime()));
			//ִ��
			ptmy.execute();
		}
		
		
		public void delInformation(Integer id) throws Exception {
	//������ݿ�����
			Connection conn = DBUtil.getConnection();

			//ƴдsql���
			String sqlString =""+
								" delete from panelinformation"+
								" where id = ?";
								//ռλ�����������Sql���Ԥ�����ʱ��Ѳ�������ȥ
			//Ԥ����sql���
			PreparedStatement ptmy = conn.prepareStatement(sqlString);
			//���Σ�ΪԤ�������ֵ
			ptmy.setInt(1, id);
			//ִ��
			ptmy.execute();
		}
		
		
		public void updateInformation(FirePanelInfoUtil f) throws Exception {
	//������ݿ�����
			Connection conn = DBUtil.getConnection();
			//ƴдsql���
			String sqlString =""+
								" update panelinformation"+
								" set id=?,secret=?,xPosition=?,yPosition=?,floor=?,num=?,panelId=?"+
								" where id=?";
								//ռλ�����������Sql���Ԥ�����ʱ��Ѳ�������ȥ
			//Ԥ����sql���
			PreparedStatement ptmy = conn.prepareStatement(sqlString);
			//���Σ�ΪԤ�������ֵ
			ptmy.setInt(1, f.getId());
			ptmy.setString(2,f.getSecret());
			ptmy.setDouble(3, f.getxPosition());
			ptmy.setDouble(4, f.getyPosition());
			ptmy.setInt(5, f.getFloor());
			ptmy.setInt(6, f.getNum());
			ptmy.setString(7, f.getPanelId());
			ptmy.execute();		
		}
		
		public FirePanelInfoUtil queryByFireDetails(String fireDetails) throws Exception  {
			//������ݿ�����
			Connection conn = DBUtil.getConnection();
			//ƴдsql���
			String sqlString ="select * from panelinformation where panelId = ? ";
								//ռλ�����������Sql���Ԥ�����ʱ��Ѳ�������ȥ
			//Ԥ����sql���
			PreparedStatement ptmy = conn.prepareStatement(sqlString);
			ptmy.setString(1, fireDetails);
			
			//ִ��
			ResultSet rs = ptmy.executeQuery();	
			FirePanelInfoUtil firePanelInfoUtil =null;
			while (rs.next()) {	
				firePanelInfoUtil = new FirePanelInfoUtil();
				firePanelInfoUtil.setId(rs.getInt("id"));
				firePanelInfoUtil.setSecret(rs.getString("secret"));
				firePanelInfoUtil.setxPosition(rs.getDouble("xPosition"));
				firePanelInfoUtil.setyPosition(rs.getDouble("yPosition"));
				firePanelInfoUtil.setFloor(rs.getInt("floor"));
				firePanelInfoUtil.setNum(rs.getInt("num"));
				firePanelInfoUtil.setPanelId(rs.getString("panelId"));
				
			}		
			return firePanelInfoUtil ;		
		}

		
		public List<FirePanelInfoUtil> queryInformation(List<Map<String, Object>> params) throws Exception {
			List<FirePanelInfoUtil> gs = new ArrayList<FirePanelInfoUtil>();
			
			Connection conn = DBUtil.getConnection();

			StringBuilder sBuilder =new StringBuilder();
			//С����
			sBuilder.append("select * from panelinformation where 1=1");
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
			FirePanelInfoUtil g = null;	
			while (rs.next()) {	
				g = new FirePanelInfoUtil();
				g.setId(rs.getInt("id"));
				g.setSecret(rs.getString("secret"));
				g.setxPosition(rs.getDouble("xPosition"));
				g.setyPosition(rs.getDouble("yPosition"));
				g.setFloor(rs.getInt("floor"));
				g.setNum(rs.getInt("num"));
				g.setPanelId(rs.getString("panelId"));
				gs.add(g);		
//				System.out.println(rs.getString("user_name"));
			}
			return gs;	
		}

}
