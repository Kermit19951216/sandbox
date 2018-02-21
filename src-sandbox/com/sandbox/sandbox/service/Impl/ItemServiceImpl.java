package com.sandbox.sandbox.service.Impl;

import java.util.List;

import org.hibernate.Session;

import com.sandbox.sandbox.model.Item;
import com.sandbox.sandbox.model.User;
import com.sandbox.sandbox.service.ItemService;

public class ItemServiceImpl extends BaseServiceImpl<Item, Integer> implements ItemService {

	
	@Override
	public List<Item> LoadUnusedItem(User user) {
		// TODO Auto-generated method stub
		return sandboxItemDao.LoadUnusedItem(user);
		//System.out.println(id);
	}

	@Override
	public Integer create(Item item){
		Integer key = (Integer)sandboxItemDao.create(item);
		return key;
	}

	@Override
	public List<Item> listAll() {
		// TODO Auto-generated method stub
		return sandboxItemDao.listAll();
	}

	@Override
	public Item QueryByName(String name) {
		// TODO Auto-generated method stub
		List<Item> list = sandboxItemDao.QueryByName(name);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public void delete(Item item){
		sandboxItemDao.delete(item);
		sandboxItemDao.deleteItemCheck(item);
	}
}
