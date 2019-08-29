package com.myQrScanner.entity;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.myQrScanner.db.action.UserAction;
import com.myQrScanner.db.model.UserUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;


public class User extends ActionSupport {
	private String userName;
	private String password;
	private String phone;
	
	public void login() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		JSONObject json=new JSONObject();
		Boolean login = loginAction(userName, password);
		
		if(login) {
				json.put("status", "µÇÂ¼³É¹¦");			
//				json.put("url", "\""+getUrlString()+"\"");
				System.out.println("µÇÂ½³É¹¦ "+json.toString());
		}
		else {
			json.put("status", "µÇÂ¼Ê§°Ü");		
			System.out.println("µÇÂ½Ê§°Ü "+json.toString());
		}
		try {
			PrintWriter writer = response.getWriter();			
			writer.write(json.toString());
			writer.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	public Boolean loginAction(String userAccount, String userPassword) throws Exception {
		// TODO Auto-generated method stub
		if(userAccount !=null & userPassword != null) {
			UserAction userAction = new UserAction();
			UserUtil userUtil = (UserUtil)userAction.getUserByUserNameAndPassword(userAccount, userPassword);
			if(userUtil != null) {
				return true;
			}
		}
		return false;
	}

	public void saveUser() throws Exception {
		UserAction userAction = new UserAction();
		UserUtil userUtil = new UserUtil(userName,password,phone);		
		userAction.add(userUtil);	
	}
	
//	private void responseMes(String status) {
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("application/json; charset=utf-8");
//		JSONObject json=new JSONObject();
//		
//		
//		if(status.equals("SUCCESS")) {
//				json.put("status", "\""+status+"\"");			
//				json.put("url", "\""+getUrlString()+"\"");
//				json.put("xPosition", "\""+xPosition+"\"");
//				json.put("yPosition", "\""+yPosition+"\"");		
//				System.out.println("Ç©µ½³É¹¦£¬ÎÞ»ð¾¯ json= "+json.toString());
//		}
//		else if (status.equals("DANGEROUS")) {
//			json.put("status", "\""+status+"\"");			
//			json.put("url", "\""+getUrlString()+"\"");
//			json.put("xPosition", "\""+xPosition+"\"");
//			json.put("yPosition", "\""+yPosition+"\"");		
//			
//			Integer num = Integer.valueOf(fireStatus.substring(1, 4));
//			json.put("fireNum", "\""+num+"\"");
//			
//			if(num<=3) {
//				for(int i = 0;i<num;i++) {
//					int fireId = Integer.valueOf(fireStatus.substring(4+i*3, 7+i*3));
//					json.put("fire"+i, "\""+fireId+"\"");	
//				}
//			}
//			else {
//				for(int i = 0;i<3;i++) {
//					int fireId = Integer.valueOf(fireStatus.substring(4+i*3, 7+i*3));
//					json.put("fire"+i, "\""+fireId+"\"");					
//				}				
//			}
//			
//		}
//		else {
//				json.put("status", "\""+status+"\"");
//		}
//		
//		try {
//			PrintWriter writer = response.getWriter();			
//			writer.write(json.toString());
//			writer.flush();
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	@Override
//	public User getUser(int userId) {
//		// TODO Auto-generated method stub
//		Session session = HibernateSessionFactory.getSession();
//		User user = (User)session.load(User.class, userId);
//		return user;
//	}

//	public int getUserIdByUserAccount(String userAccount) {
//		// TODO Auto-generated method stub
//		Session session = HibernateSessionFactory.getSession();
//		String hql =  "from User as user where user.userAccount='"+ userAccount +"'";
//		Query query = (Query)session.createQuery(hql);
//		User user = (User)query.uniqueResult();
//		if(user != null) {
//			return user.getUserId();
//		}
//		HibernateSessionFactory.closeSession();
//		return user.getUserId();
//	}	
	
	public String getPhone() {
		return phone;
	}








	public void setPhone(String phone) {
		this.phone = phone;
	}








	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
