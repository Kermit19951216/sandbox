package com.sandbox.sandbox.test.action;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sandbox.common.utils.JsonUtil;
import com.sandbox.sandbox.model.Item;

public class testJson {

	@Test
	public void test1(){
		Item item = new Item();
		item.setName("apple");
		item.setPicname("apple.png");
		String string = JsonUtil.toJson(item);
		System.out.println(string);
	}
	
	@Test
	public void test2(){
		String string = "[{\"isPublic\":0,\"name\":\"apple\",\"picname\":\"apple.png\",\"xloc\":0,\"yloc\":0}]";
		List<Item> items = JsonUtil.toBean(string, Item.class);
		System.out.println(items.get(0));
	}
	
	@Test
	public void test3(){
		List<Item> list = new ArrayList<>();
		Item item = new Item();
		item.setName("apple");
		item.setPicname("apple");
		list.add(item);
		item.setName("banana");
		item.setPicname("banana");
		list.add(item);
		String string = JsonUtil.toJson(list);
		System.out.println(string);
	}
}
