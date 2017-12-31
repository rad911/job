package zhilianzhaoping;

import java.util.HashMap;
import java.util.List;

public class SumTotal {
	public static HashMap<String,Integer> sumAreaCount(List<HashMap<String,String>> list){
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		for(int i = 0;i<list.size();i++){
			//使用hashmap的查找key的效率高，时间复杂度应该远低于n2
			String gzdd = list.get(i).get("工作地点");
			if(!map.containsKey(gzdd)){
				map.put(gzdd,1);
			}else{
				map.put(gzdd,map.get(gzdd)+1);
			}
		}
		return map;
	}
	public static double averageLow(List<HashMap<String,String>> list){
		double low = 0;
		int count  = 0;
		double averageLow = 0;
		for(int i = 0;i<list.size();i++){
			String zwyx = list.get(i).get("职位月薪");
			if(!("面议".equals(zwyx) || zwyx.contains("以"))){
				count++;
				low = low+Integer.parseInt(zwyx.substring(0,zwyx.indexOf("-")));
			}
		}
//		System.out.println("共统计了"+count+"个数据");
		averageLow = low/count;
		averageLow =Double.parseDouble((averageLow+"").substring(0,((averageLow+"").indexOf(".")+3)>((averageLow+"").length())?(averageLow+"").length():(averageLow+"").indexOf(".")+3));
		return averageLow;
	}
	public static double averageHigh(List<HashMap<String,String>> list){
		double high = 0;
		int count = 0;
		double  averageHigh;
		for(int i = 0;i<list.size();i++){
			String zwyx = list.get(i).get("职位月薪");
			if(!("面议".equals(zwyx) || zwyx.contains("以"))){
				count++;
				high = high + Integer.parseInt(zwyx.substring(zwyx.indexOf("-")+1));
			}
		}
//		System.out.println("共统计了"+count+"个数据");
		averageHigh = high/count;
		averageHigh = Double.parseDouble((averageHigh+"").substring(0,((averageHigh+"").indexOf(".")+3)>((averageHigh+"").length())?(averageHigh+"").length():(averageHigh+"").indexOf(".")+3));
		return averageHigh  ;
	}
	public static HashMap<String,Integer> sumLowCount(List<HashMap<String,String>> list){
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		for(int i = 0;i<list.size();i++){
			String zwyx = list.get(i).get("职位月薪");
			if(!("面议".equals(zwyx) || zwyx.contains("以"))){
//				System.out.println("zwyx:"+zwyx);
				String low = zwyx.substring(0,zwyx.indexOf("-"));
				if(!map.containsKey(low)){
					map.put(low,1);
				}else{
					map.put(low,map.get(low)+1);
				}
			}
		}
//		System.out.println("map中的个数:"+map.size());
		return map;
	}
	public static HashMap<String,Integer> sumHighCount(List<HashMap<String,String>> list){
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		for(int i = 0;i<list.size();i++){
			String zwyx = list.get(i).get("职位月薪");
			if(!("面议".equals(zwyx) || zwyx.contains("以"))){
				String high = zwyx.substring(zwyx.indexOf("-")+1);
				if(!map.containsKey(high)){
					map.put(high,1);
				}else{
					map.put(high,map.get(high)+1);
				}
			}
		}
//		System.out.println("map中的size"+map.size());
		return map;
	}
}
