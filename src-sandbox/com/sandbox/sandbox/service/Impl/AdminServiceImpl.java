package com.sandbox.sandbox.service.Impl;

import java.util.List;

import com.sandbox.sandbox.model.Admin;
import com.sandbox.sandbox.service.AdminService;

public class AdminServiceImpl extends BaseServiceImpl<Admin, Integer> implements AdminService {

	@Override
	public Admin QueryAdminByName(String name) {
		// TODO Auto-generated method stub
		List<Admin> list = sandboxAdminDao.QueryAdminByName(name);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public Admin AdminLogin(String name, String password) {
		// TODO Auto-generated method stub
		List<Admin> list = sandboxAdminDao.QueryAdminByName(name);
		if(list!=null){
			if(password.equals(list.get(0).getPassword()))
				return list.get(0);
		}
		return null;
	}

}
