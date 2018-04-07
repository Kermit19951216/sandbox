package com.sandbox.sandbox.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.sandbox.common.utils.AnalyseUtil;
import com.sandbox.common.utils.JsonUtil;
import com.sandbox.common.utils.ThreadAnalyseUtil;
import com.sandbox.sandbox.model.AnalysisModel;
import com.sandbox.sandbox.model.Item;
import com.sandbox.sandbox.model.MenuItem;
import com.sandbox.sandbox.model.User;
import com.sandbox.sandbox.service.MenuItemService;

public class UserAction extends BaseAction<User> {

	
	private String UserExisted;
	
	private String jsonstr;
	
	private String location;
	
	private String totalresult;
	
	public String execute(){
		return "login";
	}
	
	public String register(){
		return "signin";
	}
	
	public String Login(){
		logger.debug("model={}",model);
		if(model == null
		   ||model.getName() == null
		   ||model.getName().trim().equals("")
		   ||model.getPassword() == null
		   ||model.getPassword().trim().equals("")){
			logger.debug("登录失败,用户名或密码为空");
			session.put("loginFlag", 1);
			return "login";
		}
		model = sandboxUserService.UserLogin(model.getName(), model.getPassword());
		if(model == null||model.getIsConfirm()==0){
			logger.debug("登录失败,用户名或密码错误");
			session.put("loginFlag", 1);
			return "login";
		}else{
			logger.debug("登录成功,信息放入session");
			session.put("loginFlag", "");
			session.put("user", model);
			if(model.getItems().size()>0){
				List<MenuItem> list = sandboxMenuItemService.list(model);
				System.out.println(list.size());
				//System.out.println(list.get(0));
				if(list.size()>0){
					for(MenuItem item :list){
						System.out.println("##### "+item);
					}
					session.put("MenuItem", list);
					//session.put("MenuItem", list);
				}
			}else{
				List<MenuItem> list = sandboxMenuItemService.listAll();
				System.out.println(list.get(0));
				if(list.size()>0){
					session.put("MenuItem", list);
				}
			}
		}
		location = (String)session.get("location");
		if(location!=null){
			System.out.println("location"+location);
			return "itemlogin";
		}else{
			return "success";
		}
	}
	
	public String SignIn(){
		logger.debug("model={}",model);
		if(model == null
		   ||model.getName() == null
		   ||model.getName().trim().equals("")
		   ||model.getPassword() == null
		   ||model.getPassword().trim().equals("")){
			logger.debug("注册失败");
			session.put("signinFlag", 1);
			return "signin";
		}
		model = sandboxUserService.UserSignIn(model.getName(), model.getPassword());
		if(model == null){
			logger.debug("注册失败");
			session.put("signinFlag", 1);
			return "signin";
		}else{
			logger.debug("注册成功,信息放入session");
			session.put("signinFlag", "");
			session.put("user", model);
			List<MenuItem> list = sandboxMenuItemService.list(model);
			System.out.println(list.size());
			session.put("MenuItem", list);
		}
		return "success";
	}
	
	public String Check(){
		logger.debug("model={}",model);
		model = sandboxUserService.QueryUserByName(model.getName());
		System.out.println(model);
		if(model != null){
			this.setUserExisted("existed");
		}
		return "UserExisted";
	}
	
	public String Logout(){
		logger.debug("用户登出，清空session信息");
		session.put("user", null);
		session.put("signinFlag", null);
		session.put("loginFlag", null);
		List<MenuItem> list = sandboxMenuItemService.listAll();
		session.put("MenuItem", list);
		session.put("location", null);
		location = null;
		return "logout";
	}

	public String SaveUserItem(){
		logger.debug("传入JSON{}",jsonstr);
		User user = (User)session.get("user");
		try{
			@SuppressWarnings("unchecked")
			List<Item> list = JsonUtil.toBean(jsonstr, Item.class);
			user.getItems().clear();
			for(Item i:list){
				String[] str = i.getPicname().split("img/sandbox/");
				System.out.println(str[1]+" ###  ");
				i.setPicname(str[1]);
				Integer id = sandboxItemService.create(i);
				i.setId(id);
				logger.debug("user-Save用户参数{}",i);
				user.getItems().add(i);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			sandboxUserService.update(user);
		}
		location = (String)session.get("location");
		if(location.length()>0){
			if(user!=null){//若用户已登录要将location中的目标url清空
				List<MenuItem> list = sandboxMenuItemService.list(user);
				session.put("MenuItem", list);
				session.put("location", null);
				location = null;
			}
			return "index";
		}else{
			return "success";
		}
	}
	
	@SuppressWarnings("unchecked")
	public String UserAnalyse(){
		
		logger.debug("传入JSON{}",jsonstr);
		StringBuilder builder = new StringBuilder();
		Map<String, Item> items = new HashMap<>();
		List<Item> list = JsonUtil.toBean(jsonstr, Item.class);
		if(list.size() > 0){
			for(Item i:list){
				items.put(i.getName(), i);
			}
		}
		List<AnalysisModel> analysisModels = sandboxAnalysisModelService.list();
		if(analysisModels.size() > 0){
			ThreadAnalyseUtil threadAnalyseUtil = new ThreadAnalyseUtil();
			totalresult = threadAnalyseUtil.Analyse(analysisModels, items);//请用多个线程进行分析，减少分析等待时间
			threadAnalyseUtil.close();
		}
		/*totalresult = builder.toString();*/
		System.out.println("totalresult"+totalresult);
		return "analyse";
	}
	
	public void setUserExisted(String userExisted) {
		UserExisted = userExisted;
	}

	public String getUserExisted() {
		return UserExisted;
	}

	public String getJsonstr() {
		return jsonstr;
	}

	public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTotalresult() {
		return totalresult;
	}

	public void setTotalresult(String totalresult) {
		this.totalresult = totalresult;
	}
}
