package com.sandbox.sandbox.service;

import java.util.List;

import com.sandbox.sandbox.model.User;

public interface UserService extends BaseService<User, Integer> {

	public abstract User UserLogin(String name,String password);
	
	public abstract User UserSignIn(String name,String password);
	
	public abstract User QueryUserByName(String name);
	
	public abstract void update(User user);
	
	public abstract void delete(User user);
	
	public abstract List<User> listAll();
}
