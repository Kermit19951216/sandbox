package com.sandbox.sandbox.test.action;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding.Use;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sandbox.sandbox.dao.BaseDao;
import com.sandbox.sandbox.dao.ItemDao;
import com.sandbox.sandbox.dao.UserDao;
import com.sandbox.sandbox.model.Admin;
import com.sandbox.sandbox.model.AnalysisModel;
import com.sandbox.sandbox.model.Item;
import com.sandbox.sandbox.model.MenuItem;
import com.sandbox.sandbox.model.User;
import com.sandbox.sandbox.service.AdminService;
import com.sandbox.sandbox.service.AnalysisModelService;
import com.sandbox.sandbox.service.ItemService;
import com.sandbox.sandbox.service.MenuItemService;
import com.sandbox.sandbox.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-*.xml")
public class testService {

	@Resource(name="MenuItemService")
	public MenuItemService testMenuItemService;
	
	@Resource(name="UserService")
	public UserService testUserSerivce;
	
	@Resource(name="ItemService")
	public ItemService testItemService;
	
	@Resource(name="ItemDao")
	public ItemDao testItemDao;

	@Resource(name="AdminService")
	public AdminService testAdminService;
	
	@Resource(name="AnalysisModelService")
	public AnalysisModelService testAnalysisModelService;
	
	@Test
	public void test1(){
		List<AnalysisModel> list = testAnalysisModelService.list();
		for(AnalysisModel a:list){
			System.out.println(a);
		}
	}

	@Test
	public void test2(){
		String name = "kermit";
		User user = testUserSerivce.QueryUserByName(name);
		List<Item> items = testItemService.LoadUnusedItem(user);
		System.out.println(items.get(0));
		//Set<Item> set = user.getItems();
		
	}

	@Test
	public void test3(){
		String name = "testitem";
		Item item = new Item();
		item.setName(name);
		testItemService.create(item);
		item = testItemService.QueryByName(name);
		if(item!=null){
			User user  = testUserSerivce.QueryUserByName("kermit");
			if(user!=null){
				user.getItems().add(item);
				testUserSerivce.update(user);
			}
		}
	}
	
	@Test
	public void test4(){

		Item item = testItemService.QueryByName("testitem");
		if(item!=null){
			testItemService.delete(item);
		}
	}
	
	@Test
	public void test5(){
		String name = "kermit";
		User user = testUserSerivce.QueryUserByName(name);
		user.getItems().clear();
		testUserSerivce.update(user);
	}
	
	@Test
	public void test6(){
		String name = "kermit";
		User user = testUserSerivce.QueryUserByName(name);
		List<MenuItem> list = testMenuItemService.list(user);
		System.out.println(list.get(0));
	}
	
	@Test
	public void test7(){
		User user = testUserSerivce.QueryUserByName("haha");
		testItemDao.clearUserItem(user);
	}
	
	@Test
	public void test8(){
		
		String name = "zelebrate";
		Admin admin = testAdminService.QueryAdminByName(name);
		if(admin!=null)
			System.out.println(admin);
		
	}
}
