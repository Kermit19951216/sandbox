package com.sandbox.sandbox.dao;

import java.util.List;

import com.sandbox.sandbox.model.Admin;

public interface AdminDao extends BaseDao<Admin, Integer> {

	public abstract List<Admin> QueryAdminByName(String name);
	
}
