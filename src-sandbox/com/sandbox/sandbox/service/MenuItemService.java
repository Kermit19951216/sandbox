package com.sandbox.sandbox.service;

import java.util.List;

import com.sandbox.sandbox.model.MenuItem;
import com.sandbox.sandbox.model.User;

public interface MenuItemService extends BaseService<MenuItem, Integer> {

	public abstract List<MenuItem> list(User user);
	
	public abstract List<MenuItem> listAll();
	
}
