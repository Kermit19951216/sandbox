package com.sandbox.common.action;

import java.util.List;

import com.sandbox.sandbox.action.BaseAction;
import com.sandbox.sandbox.model.MenuItem;
import com.sandbox.sandbox.model.User;

public class IndexAction extends BaseAction<User> {

	public String execute(){
		logger.debug("访问首页{}");
		List<MenuItem> list = sandboxMenuItemService.listAll();
		session.put("MenuItem", list);
		return "index";
	}
	
	public String test(){
		logger.debug("访问测试页{}");
		return "test";
	}
}
