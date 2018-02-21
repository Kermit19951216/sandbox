package com.sandbox.common.utils;


public class DistanceUtil {

	public static double getDistance(double x1,double y1,double x2,double y2){
		
		double x = x1-x2;
		double y = y1-y2;
		return Math.sqrt(x*x+y*y);
		
	}
	
}
