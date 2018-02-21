package com.sandbox.sandbox.dao;

import java.util.List;

import com.sandbox.sandbox.model.MenuItem;
import com.sandbox.sandbox.model.User;

public interface MenuItemDao extends BaseDao<MenuItem, Integer> {

	public List<MenuItem> list(User user);
	
	public List<MenuItem> listAll();
}
