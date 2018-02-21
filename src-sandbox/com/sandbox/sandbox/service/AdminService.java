package com.sandbox.sandbox.service;

import com.sandbox.sandbox.model.Admin;

public interface AdminService extends BaseService<Admin, Integer> {

	public abstract Admin QueryAdminByName(String name);
	
	public abstract Admin AdminLogin(String name , String password);
}
