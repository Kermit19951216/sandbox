package com.sandbox.sandbox.service.Impl;

import java.util.List;

import com.sandbox.sandbox.model.User;
import com.sandbox.sandbox.service.UserService;

public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {


	@Override
	public User UserLogin(String name, String password) {
		// TODO Auto-generated method stub
		List<User> users = sandboxUserDao.QueryByName(name);
		User user;
		if(users.size()>0){
			user = users.get(0);
		}else{
			return null;
		}
		String userpassword = user.getPassword();
		if(userpassword.equals(password)){
			return user;
		}else{
			return null;
		}
		
	}

	@Override
	public User UserSignIn(String name, String password) {
		// TODO Auto-generated method stub
		List<User> users = sandboxUserDao.QueryByName(name);
		if(users.size() > 0){
			return null;
		}
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setIsConfirm(1);
		sandboxUserDao.create(user);
		return user;
	}

	@Override
	public User QueryUserByName(String name) {
		// TODO Auto-generated method stub
		List<User> users = sandboxUserDao.QueryByName(name);
		if(users.size() == 0){
			return null;
		}
		User user = users.get(0);
		return user;
	}

	@Override
	public void update(User user){
		sandboxItemDao.clearUserItem(user);
		sandboxUserDao.update(user);
	}
	
	@Override
	public void delete(User user){
		sandboxUserDao.delete(user);
	}

	@Override
	public List<User> listAll() {
		// TODO Auto-generated method stub
		return sandboxUserDao.listAll();
	}
	
}
