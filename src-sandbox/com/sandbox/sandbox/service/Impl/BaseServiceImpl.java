package com.sandbox.sandbox.service.Impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sandbox.sandbox.dao.AdminDao;
import com.sandbox.sandbox.dao.AnalysisModelDao;
import com.sandbox.sandbox.dao.BaseDao;
import com.sandbox.sandbox.dao.ItemDao;
import com.sandbox.sandbox.dao.MenuItemDao;
import com.sandbox.sandbox.dao.UserDao;
import com.sandbox.sandbox.service.BaseService;


public class BaseServiceImpl<T,PK extends Serializable> implements BaseService<T, PK> {

	
	protected Class clazz;
	
	protected static Logger logger;
	
	protected BaseDao baseDao;
	
	@Resource(name="UserDao")
	protected UserDao sandboxUserDao;
	
	@Resource(name="ItemDao")
	protected ItemDao sandboxItemDao;
	
	@Resource(name="MenuItemDao")
	protected MenuItemDao sandboxMenuItemDao;
	
	@Resource(name="AdminDao")
	protected AdminDao sandboxAdminDao;
	
	@Resource(name="AnalysisModelDao")
	protected AnalysisModelDao sandboxAnalysisModelDao;
	
	
	public BaseServiceImpl() {
		// TODO Auto-generated constructor stub
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class)type.getActualTypeArguments()[0];
		logger = LogManager.getLogger(this.getClass().getName());
		logger.debug("初始化logger:{}",logger.getName());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PK create(T object) {
		// TODO Auto-generated method stub
		logger.debug("参数:{}",object);
		PK key = (PK)baseDao.create(object);
		return key;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(T object) {
		// TODO Auto-generated method stub
		logger.debug("参数:{}",object);
		baseDao.update(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(T object) {
		// TODO Auto-generated method stub
		logger.debug("参数:{}",object);
		baseDao.delete(object);
	}


	
}
