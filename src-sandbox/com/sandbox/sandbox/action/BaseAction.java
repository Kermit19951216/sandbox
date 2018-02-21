package com.sandbox.sandbox.action;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sandbox.sandbox.service.AdminService;
import com.sandbox.sandbox.service.AnalysisModelService;
import com.sandbox.sandbox.service.BaseService;
import com.sandbox.sandbox.service.ItemService;
import com.sandbox.sandbox.service.MenuItemService;
import com.sandbox.sandbox.service.UserService;



public class BaseAction<T> extends ActionSupport implements 
SessionAware, RequestAware, ApplicationAware, ModelDriven<T> {

	
	protected static Logger logger;
	protected Map<String,Object> request;
	protected Map<String, Object> session;
	protected Map<String, Object> application;
	protected T model;
	 
	protected BaseService sandboxBaseService;
	
	@Resource(name="UserService")
	protected UserService sandboxUserService;
	
	@Resource(name="ItemService")
	protected ItemService sandboxItemService;
	
	@Resource(name="MenuItemService")
	protected MenuItemService sandboxMenuItemService;
	
	@Resource(name="AdminService")
	protected AdminService sandboxAdminService;
	
	@Resource(name="AnalysisModelService")
	protected AnalysisModelService sandboxAnalysisModelService;
	
	public BaseAction(){
		logger = LogManager.getLogger(this.getClass().getName());//初始化logger
		logger.debug("初始化logger:{}",logger.getName());
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();//反射获取超类
		Class clazz = (Class) type.getActualTypeArguments()[0];//再通过超类获取泛型类型参数
		try{
			model = (T)clazz.newInstance();//新建实例并绑定为泛型类型，model即为泛型参数类型的实例，ModelDriven
		}catch(Exception e){               //按照model类型进行装配
			throw new RuntimeException(e);
		}
	}
	
	public T getModel(){
		return model;
	}
	
	@Override
	public void setApplication(Map<String, Object> application) {
		// TODO Auto-generated method stub
		this.application = application;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

}
