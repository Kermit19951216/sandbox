package com.sandbox.sandbox.dao;

import java.util.List;

import com.sandbox.sandbox.model.User;

public interface UserDao extends BaseDao<User, Integer> {

	/** 
     * 按用户名查找记录
     * @param name 用户名
     * @return List类型所有符合的记录
     */ 
	public abstract List<User> QueryByName(String name);
	
	/** 
     * 查找所有未被管理员确认的用户
     * @return List类型所有符合的记录
     */
	public abstract List<User> QueryUnConfirm();
	
	/** 
     * 返回tb_user表的所有记录
     * @return List类型所有符合的记录
     */
	public abstract List<User> listAll();
	
}
