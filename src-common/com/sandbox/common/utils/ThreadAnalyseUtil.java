package com.sandbox.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import com.sandbox.sandbox.model.AnalysisModel;
import com.sandbox.sandbox.model.Item;

public class ThreadAnalyseUtil {

	private ExecutorService exec;
	private static final int poolsize = 5;
	private static final int threadnumber = 3;//启用线程数目
	private List<Future<String>> tasks = new ArrayList<Future<String>>();//Future集合，每个元素表示线程的一次异步操作
	                                                                     //获取分析返回结果依据
	class Analysor implements Callable<String>{
		
		private List<AnalysisModel> list = new ArrayList<>();
		private Map<String, Item> items = new HashMap<>();
		private int start;
		private int end;
		
		public Analysor(List<AnalysisModel> list, Map<String, Item> items, int start, int end) {
			// TODO Auto-generated constructor stub
			this.list = list;
			this.items = items;
			this.start = start;
			this.end = end;
		}
		
		@Override
		public String call() throws Exception{
			
			String totalresult = "";
			for(int j=start;j<end;j++){
				AnalysisModel a = list.get(j);
				Item item;
				boolean flag = false;
				int judge = list.get(j).getJudge();
				switch (judge) {
				case 1://威胁
					String[] params1 = a.getParams().split(",");
					List<Item> paramlist1 = new ArrayList<>(); 
					for(int i=0;i<params1.length;i++){
						item = items.get(params1[i]);
						if(item!=null)
							paramlist1.add(item);
					}
					item = items.get(a.getItemname());
					if(paramlist1.size()>0 && item!=null){
						flag = AnalyseUtil.judge_function_1(item, paramlist1, a.getStandard());
						if(flag){
							//判别成立
							//System.out.println(a.getResult());
							totalresult += a.getResult() + ";";
							//System.out.println(totalresult);
						}
					}
					break;
                case 2://限制
				    item = items.get(a.getItemname());
				    String[] result = a.getResult().split("；");
				    if(item!=null){
				    	flag = AnalyseUtil.judge_function_2(item, a.getStandard());
				    	if(flag){
					    	//判别成立
					    	//System.out.println(result[0]);
					    	/*builder.append(result[0]);
					    	builder.append(";");*/
				    		totalresult += result[0] + ";";
				    		//System.out.println(totalresult);
					    }else if(!flag&&result.length==2){
					    	//System.out.println(result[1]);
					    	/*builder.append(result[1]);
					    	builder.append(";");*/
					    	totalresult += result[1] + ";";
					    	//System.out.println(totalresult);
					    }
				    }
					break;
                case 3://连接
                	item = items.get(a.getItemname());
                	if(item!=null){
                		String[] params3 = a.getParams().split(",");
                		List<Item> itemlist = new ArrayList<>();
                		for(int i=0;i<params3.length;i++){
                			Item other = items.get(params3[i]);
                			if(other!=null)
                				itemlist.add(other);
                		}
                		if(itemlist.size()>0){
                			Item[] paramlist3 = new Item [itemlist.size()];
                    		itemlist.toArray(paramlist3);
                    		flag = AnalyseUtil.judge_function_3(item, paramlist3, a.getStandard());
                		}
                		if(flag){
                			//判别成立
                			//System.out.println(a.getResult());
                			/*builder.append(a.getResult());
                			builder.append(";");*/
                			totalresult += a.getResult() + ";";
                			//System.out.println(totalresult);
                					
                		}
                	}
                	break;
                //此项可能会导致分析结果矛盾，暂时不使用
                 /*case 4://分裂
                	 Item[] params = new Item[items.size()];
                	 items.values().toArray(params);
                	 flag = AnalyseUtil.judge_function_4(params, a.getStandard());
                	 if(flag){
                		 //判别成立
                	 }
                	 break;*/
                 case 5://空洞
                	 int size = items.size();
                	 flag = AnalyseUtil.judge_function_5(size, a.getStandard());
                	 if(flag){
                		 //判别成立
                		 //System.out.println(a.getResult());
                		 /*builder.append(a.getResult());
                		 builder.append(";");*/
                		 totalresult += a.getResult() + ";";
                		 //System.out.println(totalresult);
                	 }
                	 break;
                 case 6://灵性，转化
                	 if(items.get(a.getItemname())!=null){
                		 //判别成立
                		 //System.out.println(a.getResult());
                		 /*builder.append(a.getResult());
                		 builder.append(";");*/
                		 totalresult += a.getResult() + ";";
                		 //System.out.println(totalresult);
                	 }
                	 break;
				default:
					break;
				}
				
			}
			return totalresult;
		}
		
	}
	
	public ThreadAnalyseUtil(){
		
		exec = Executors.newFixedThreadPool(poolsize);
		
	}
	
	public String Analyse(List<AnalysisModel> list, Map<String, Item> items){
		
		int increment = list.size()/threadnumber;
		for(int i=0;i<threadnumber;i++){
			
			int start = increment * i;
			int end = start + increment;
			if(i==threadnumber-1){
				end = list.size();
				//System.out.println(end);
			}
			Analysor analysor = new Analysor(list, items, start, end);
			FutureTask<String> task = new FutureTask<>(analysor);//FutureTask Future接口的实现类
			tasks.add(task);
			if(!exec.isShutdown()){
				exec.submit(task);
			}
		}
		return getTotalResutl();
	}

	public void close(){
		exec.shutdown();
	}
	
	public String getTotalResutl(){//获取、整合分析结果
		
		String totalresult = "";
		for(Future<String> task:tasks){
			
			try{
				String result = task.get();//当线程未完成call()时此处阻塞
				totalresult += result;
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		//System.out.println("11111:"+totalresult);
		return totalresult;
	}
}
