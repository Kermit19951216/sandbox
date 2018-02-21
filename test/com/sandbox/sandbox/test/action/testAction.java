package com.sandbox.sandbox.test.action;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class testAction extends ActionSupport implements SessionAware{
	
	public String param;
	private static Logger logger;
	protected Map<String, Object> session;
	
	public String execute(){
		logger = LogManager.getLogger(this.getClass().getName());
		logger.debug("test初始化logger:{}",logger.getName());
		session.put("user", "Kermit");
		return "ok";
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
}
