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
	private String password1;
	
	public void login() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		JSONObject json=new JSONObject();
		Boolean login = loginAction(userName, password);
		
		if(login) {
				json.put("status", "��¼�ɹ�");			
				System.out.println("��½�ɹ� "+json.toString());
		}
		else {
			json.put("status", "��¼ʧ��");		
			System.out.println("��½ʧ�� "+json.toString());
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
	
	
	public void signup() throws Exception {
		System.out.println("ע�Ὺʼ ");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		JSONObject json=new JSONObject();
		if(!password.equals(password1)) {
			json.put("status", "ע��ʧ��");
			System.out.println("ע��ʧ�� "+json.toString());
			return;
		}
		else {
			UserAction userAction = new UserAction();
			UserUtil userUtil = new UserUtil(userName,password,phone);		
			userAction.add(userUtil);	
			json.put("status", "ע��ɹ�");
			System.out.println("ע��ɹ� "+json.toString());
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
	
	
	public void getUserByName() throws Exception {
		System.out.println("huoqu ��ʼ ");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		JSONObject json=new JSONObject();
		UserAction userAction = new UserAction();
		UserUtil userUtil = userAction.getUserByUserName(userName);		
		if(userUtil == null) {
			json.put("status", "��ȡ��Ϣʧ��");
			System.out.println("��ȡ��Ϣʧ�ܣ�"+json.toString());
		}
		else {
			System.out.println("��ȡ��Ϣ�ɹ���"+json.toString());
			json.put("status", "��ȡ��Ϣ�ɹ�");
			json.put("userName", userUtil.getUserName());
			json.put("phone", userUtil.getPhone());		
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

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	
	
	

}
