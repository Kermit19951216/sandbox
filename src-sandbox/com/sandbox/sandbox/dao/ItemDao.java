package com.sandbox.sandbox.dao;

import java.util.List;

import com.sandbox.sandbox.model.Item;
import com.sandbox.sandbox.model.User;

public interface ItemDao extends BaseDao<Item, Integer> {

	/** 
     * 按物品名查找记录
     * @param name 物品名
     * @return List类型所有符合的记录
     */ 
	public abstract List<Item> QueryByName(String name);
	
	/** 
     * 返回tb_item表中的所有记录
     */ 
	public abstract List<Item> listAll();
	
	public abstract List<Item> LoadUnusedItem(User user);
	
	public abstract void deleteItemCheck(Item item);
	
	public abstract void clearUserItem(User user);
}
