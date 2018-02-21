package com.sandbox.common.utils;

import java.util.List;

import com.sandbox.sandbox.model.Item;

public class AnalyseUtil {

	public static final int Judge_1 = 1;
	
	public static final int Judge_2 = 2;
	
	public static final int Judge_3 = 3;
	
	public static final int Judge_4 = 4;
	
	public static final int Judge_5 = 5;
	
	public static final int Judge_6 = 6;
	
	/**
	 * 分析类型1（威胁）：根据目标Item与其关联Item间的距离均值及标准差进行分析
	 * @param item 目标Item
	 * @param params 与目标Item相关联的其他Item
	 * @param standard 判别标准，此处为目标Item与其他关联Item间距离的"标准差,均值"
	 * @return 是否符合判别标准
	 */
	public static boolean judge_function_1(Item item , List<Item> params , String standard){
		
		
		String[] standards = standard.split(",");
		double standardAver , standardDvar;
		if(standards.length!=2){
			System.out.println("参数错误");
			return false;
		}else{
			standardAver = Double.parseDouble(standards[0]);
			standardDvar = Double.parseDouble(standards[1]);
			System.out.println(standardAver+"  "+standardDvar);
		}
		
		int size = params.size();
		double[] array = new double [size];
		
		for(int i=0;i<size;i++){
			double distance = DistanceUtil.getDistance(item.getXloc(), item.getYloc(),
					params.get(i).getXloc(), params.get(i).getYloc());
			array[i] = distance;
		}
		
		double average = MathUtil.Average(array);
		double dvar = MathUtil.StandardDiviation(array);
		
		System.out.println("aver: "+average);
		System.out.println("dvar: "+dvar);
		
		if(average < standardAver){
			return true;
		}else{
			if(dvar > standardDvar){
				return true;
			}
		}
		return false;
	}
	/**
	 * 分析类型二（限制）：判断目标Item是否在某矩形区域中
	 * @param item 目标Item
	 * @param standard 该矩形区域的左上角及右下角两点的坐标，格式为"x1,y1,x2,y2"
	 * @return 是否在指定的矩形区域中
	 */
	public static boolean judge_function_2(Item item , String standard){
		
		Double x1,y1,x2,y2;
		String[] standards = standard.split(",");
		if(standards.length!=4){
			System.out.println("参数错误");
			return false;
		}else{
			x1 = Double.parseDouble(standards[0]);
			y1 = Double.parseDouble(standards[1]);
			x2 = Double.parseDouble(standards[2]);
			y2 = Double.parseDouble(standards[3]);
		}
		double x = item.getXloc();
		double y = item.getYloc();
		if(x>=x1&&x<=(x2-100)&&y>=y1&&y<=(y2-100)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 分析类型三（连接）：判断目标Item处于特定两个Item间的区域时，是否属于连接关系
	 * @param item 目标Item
	 * @param other 特定Item的数组，长度为2
	 * @param standard 当两个特定Item的距离在一定范围内进行判断
	 * @return 是否属于连接关系
	 */
	public static boolean judge_function_3(Item item , Item[] other , String standard){
		if(other.length>2){
			System.out.println("参数错误");
			return false;
		}
		double left , right , top , bottom;
		double sta = Double.parseDouble(standard);
		
		if(other.length==1){
			
			double distance = DistanceUtil.getDistance(item.getXloc(), item.getYloc(), 
					other[0].getXloc(), other[0].getYloc());
			if(distance<sta){
				return true;
			}else{
				return false;
			}
			
		}
		
		double distance = DistanceUtil.getDistance(other[0].getXloc(), other[0].getYloc(),
				other[1].getYloc(), other[1].getYloc());
		System.out.println("distance "+distance);
		if(distance>sta){
			return false;
		}
		if(other[0].getXloc()<other[1].getXloc()){
			left = other[0].getXloc();
			right = other[1].getXloc();
		}else{
			right = other[0].getXloc();
			left = other[1].getXloc();
		}
		
		if(other[0].getYloc()<other[1].getYloc()){
			top = other[0].getYloc();
			bottom = other[1].getYloc();
		}else{
			bottom = other[0].getYloc();
			top = other[1].getYloc();
		}
		System.out.println("left  "+"right  "+"top  "+"bottom ");
		System.out.println( left+" "+right+" "+top+" "+bottom);
		if(item.getXloc()>=left&&item.getXloc()<=right&&item.getYloc()>=top&&item.getYloc()<=bottom){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 分析类型四（分裂）：当目前Item数量大于标准时，通过各个Item间距离的距离的均值与方差进行判断
	 * @param items 
	 * @param standard “Item数量,方差，均值”字符串
	 * @return 判别是否成立
	 */
	public static boolean judge_function_4(Item[] items , String standard){
		
		String[] params = standard.split(",");
		if(params.length!=3){
			System.out.println("参数错误");
			return false;
		}
		double stasize = Double.parseDouble(params[0]);
		double staaver = Double.parseDouble(params[1]);
		double stadvar = Double.parseDouble(params[2]);
		int length = items.length;
		if(length < stasize){
			return false;
		}
		int size = 2*length*length - 2*length;
		double[] s = new double[size];
		int cur = 0;
		for(int i=0;i<length;i++){
			for(int j=i+1;j<length;j++){
				s[cur++] = DistanceUtil.getDistance(items[i].getXloc(), items[i].getYloc(),
						items[j].getXloc(), items[j].getYloc());
			}
		}
		double average = MathUtil.Average(s);
		double dvar = MathUtil.StandardDiviation(s);
		if(average>staaver&&dvar<stadvar){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 分析类型五（空洞）：通过目前Item数量进行分析
	 * @param size Item数量
	 * @param standard 指定的标准
	 * @return 判别是否成立
	 */
	public static boolean judge_function_5(int size , String standard){
		
		int sta = Integer.parseInt(standard);
		if(size < sta){
			return true;
		}
		return false;
		
	}
	
}
