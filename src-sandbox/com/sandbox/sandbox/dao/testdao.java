package com.sandbox.sandbox.dao;

import com.sandbox.sandbox.model.Item;
import com.sandbox.sandbox.model.User;

public interface testdao {

	public void insertItem(Item item);
	public Item getItemById(int id);
	
	public void insertUser(User user);
	public User getUserById(int id);
	
}
