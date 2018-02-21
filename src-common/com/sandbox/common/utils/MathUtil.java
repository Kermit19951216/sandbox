package com.sandbox.common.utils;


public class MathUtil {

	
	public static double Average(double[] s){
		
		int m = s.length;
		int sum = 0;
		for(int i =0;i<m;i++){
			sum += s[i];
		}
		return sum/m;
	}
	
	public static double StandardDiviation(double[] s){
		
		int m = s.length;
		double sum = 0;
		
		for(int i=0;i<m;i++){
			sum +=s[i];
		}
		
		double average = sum/m;
		double dvar = 0;
		for(int i=0;i<m;i++){
			dvar += (s[i]-average)*(s[i]-average);
		}
		return Math.sqrt(dvar/m);
	}
	
}
