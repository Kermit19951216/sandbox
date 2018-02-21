package com.sandbox.sandbox.test.action;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sandbox.common.utils.AnalyseUtil;
import com.sandbox.sandbox.model.Item;

public class UtilsTest {

	@Test
	public void test(){
		
		Item target = new Item();
		target.setXloc(222);
		target.setYloc(151);
		
		List<Item> list = new ArrayList<>();
		Item other = new Item();
		other.setXloc(373);
		other.setYloc(89);
		
		Item another = new Item();
		another.setXloc(314);
		another.setYloc(290);
		
		Item item = new Item();
		item.setXloc(824);
		item.setYloc(146);
		
		list.add(other);
		list.add(another);
		list.add(item);
		
		AnalyseUtil.judge_function_1(target, list, "10,360");
		
	}
	
	@Test
	public void test2(){
		Item target = new Item();
		target.setXloc(222);
		target.setYloc(151);
		
		boolean flag = AnalyseUtil.judge_function_2(target, "250,100,400,500");
		System.out.println(flag);
	}
	
	@Test
	public void test3(){
		Item target = new Item();
		target.setXloc(457);
		target.setYloc(119);
		
		Item other = new Item();
		other.setXloc(327);
		other.setYloc(78);
		
		Item another = new Item();
		another.setXloc(564);
		another.setYloc(200);
		
		Item[] params = new Item[2];
		params[0] = other;params[1] = another;
		
		AnalyseUtil.judge_function_3(target, params, "500");
	}
	
}
