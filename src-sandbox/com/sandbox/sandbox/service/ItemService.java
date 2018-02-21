package com.sandbox.sandbox.service;

import java.util.List;

import com.sandbox.sandbox.model.Item;
import com.sandbox.sandbox.model.User;

public interface ItemService extends BaseService<Item, Integer>{

	public abstract List<Item> LoadUnusedItem(User user);
	
	public abstract List<Item> listAll();
	
	public abstract Item QueryByName(String name);
	
	public abstract void delete(Item item);
	
}
