package com.sandbox.sandbox.service.Impl;

import java.util.List;

import com.sandbox.sandbox.model.MenuItem;
import com.sandbox.sandbox.model.User;
import com.sandbox.sandbox.service.MenuItemService;

public class MenuItemServiceImpl extends BaseServiceImpl<MenuItem, Integer> implements MenuItemService {

	@Override
	public List<MenuItem> list(User user) {
		// TODO Auto-generated method stub
		return sandboxMenuItemDao.list(user);
	}

	@Override
	public List<MenuItem> listAll() {
		// TODO Auto-generated method stub
		return sandboxMenuItemDao.listAll();
	}

}
