package com.sandbox.sandbox.action;

import java.util.List;

import com.sandbox.sandbox.model.Admin;
import com.sandbox.sandbox.model.User;

public class AdminAction extends BaseAction<Admin> {

	private String username;
	
	private int statuschange; 
	
	public String Login(){
		if(session.get("admin")!=null){
			return "index";
		}
		logger.debug("admin{}",model);
		if(model == null
		    ||model.getName() == null
			||model.getName().trim().equals("")
			||model.getPassword() == null
		    ||model.getPassword().trim().equals("")){
			logger.debug("登录失败,用户名或密码为空");
			session.put("loginFlag", 1);
			return "login";
		}else{
			model = sandboxAdminService.AdminLogin(model.getName(), model.getPassword());
			if(model==null){
				logger.debug("登录失败,用户名或密码错误");
				session.put("loginFlag", 1);
				return "login";
			}else{
				logger.debug("登录成功,信息放入session");
				session.put("loginFlag", "");
				session.put("admin", model);
				List<User> list = sandboxUserService.listAll();
				if(list!=null){
					System.out.println(list.size());
					session.put("users", list);
				}
			}
		}
		return "index";
	}
	
	public String Check(){
		System.out.println("check "+username);
		User user = sandboxUserService.QueryUserByName(username);
		if(user.getItems().size()>0){
			session.put("UserItem", user.getItems());
		}else{
			session.put("UserItem", null);
		}
		return "check";
	}
	
	@SuppressWarnings("unchecked")
	public String ChangeStatus(){
		System.out.println("statuschange "+statuschange);
		User user = sandboxUserService.QueryUserByName(username);
		if(user !=null){
			user.setIsConfirm(statuschange);
			sandboxUserService.update(user);
			List<User> list = (List<User>)session.get("users");
			if(list.size()>0){
				for(User u : list){
					if(u.getId()==user.getId()){
						u.setIsConfirm(statuschange);
					}
				}
				session.put("users", list);
			}
		}
		return "changed";
	}

	public String Logout(){
		session.put("admin", null);
		session.put("users", null);
		session.put("UserItem", null);
		session.put("loginFlag", null);
		return "logout";
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getStatuschange() {
		return statuschange;
	}

	public void setStatuschange(int statuschange) {
		this.statuschange = statuschange;
	}
}
