package zhilianzhaoping;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.City;
import entity.Offer;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
public class testDemo {
	  public static void main(String[] args) throws RowsExceededException, WriteException, IOException {   	
		  long startTime = System.currentTimeMillis();
		  //1.得到所有文件
		  List <Offer> list = getDataFile();
		  //2.生成可用的json数据
		  createJsonData(list);
		  long endTime = System.currentTimeMillis();
		  System.out.println("执行完毕!");
		  System.out.println("共耗时"+(endTime-startTime)/1000+"s");
	  }
	  public static Offer getOffer(String word) throws RowsExceededException, WriteException, IOException{
		  //1.数据采集
		  Offer offer = new Offer();
		  List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		  for(int i = 1;i<90;i++){
			  String url = "http://sou.zhaopin.com/jobs/searchresult.ashx?jl=%E5%85%A8%E5%9B%BD&kw="+word+"&sm=0&isfilter=0&fl=489&isadv=0&sg=6e5b1cd733f740ee83ef45814867de27&p="+i;
			  resultList.addAll(DownWebPageUtils.analyzeHtml(url,"UTF-8", word));
		  }
		  switch(word){
			  case "%E5%A4%A7%E6%95%B0%E6%8D%AE":
				  word = "大数据";
				  break;
			  case "%E6%9C%BA%E5%99%A8%E5%AD%A6%E4%B9%A0":
				  word = "机器学习";
				  break;
			  case "%E5%89%8D%E7%AB%AF":
				  word="前端";
				  break;
			  case "%E7%AE%97%E6%B3%95":
				  word="算法";
				  break;
		  }
		  writeExcel.writeDataExcel("D:/demo/rawData/"+word+"_Data.xls",resultList);
//		  System.out.println("数据采集的个数:"+resultList.size());
		  //2.数据清洗
		  resultList = DownWebPageUtils.cleanData(resultList, word);
		  writeExcel.writeDataExcel("D:/demo/processedData/"+word+"_clearData.xls",resultList);
		  offer.setName(word);
		  offer.setData(resultList);
		  //3.统计
		  //统计地区个数
		  HashMap<String, Integer> map = SumTotal.sumAreaCount(resultList);
		  writeExcel.writeAreaCountExcel("D:/demo/sumData/"+word+"_areaCount.xls", map);
		  offer.setAreaCount(map);
		  //统计最低薪资的频率
		  map = SumTotal.sumLowCount(resultList);
		  writeExcel.writeSumDataExcel("D:/demo/sumData/"+word+"_priceLow.xls", map);
		  offer.setLowCount(map);
		  //统计最高薪资的频率
		  map = SumTotal.sumHighCount(resultList);
		  writeExcel.writeSumDataExcel("D:/demo/sumData/"+word+"_priceHigt.xls",map);
		  offer.setHighCount(map);
		  //统计平均的最低薪资
		  double low = SumTotal.averageLow(resultList);
		  offer.setLowAverage(low);
		  System.out.println(word+"的平均最低薪资:"+low);
		  //统计最高的平均薪资
		  double high = SumTotal.averageHigh(resultList);
		  offer.setHighAverage(high);
		  System.out.println(word+"的平均最高薪资:"+high);
		  return offer;
	  }
	  public static void fileIsExist(){
		  File rawData = new File("D:/demo/rawData");
		  String list[] = rawData.list();
		  if(list!=null){
			  for(int i = 0;i<list.length;i++){
				  new File(rawData,list[i]).delete();
			  }
		  }
		  File processedData = new File("D:/demo/processedData");
		  list = processedData.list();
		  if(list!=null){
			  for(int i = 0;i<list.length;i++){
				  new File(processedData,list[i]).delete();
			  }
		  }
		  File sumData = new File("D:/demo/sumData");
		  list = sumData.list();
		  if(list!=null){
			  for(int i = 0;i<list.length;i++){
				  new File(sumData,list[i]).delete();
			  }
		  }
		  File jsonData = new File("D:/demo/jsonData");
		  list = jsonData.list();
		  if(list!=null){
			  for(int i = 0;i<list.length;i++){
				  new File(jsonData,list[i]).delete();
			  }
		  }
	  }
	  public static List<Offer> getDataFile() throws RowsExceededException, WriteException, IOException{
		  //0.准备工作
		  List<Offer> list = new ArrayList<Offer>();
		  fileIsExist();
		  //1.Java
		  list.add(getOffer("Java"));
		  //2.C++/C#
		  list.add(getOffer("C++"));
		  //3.大数据
		  list.add(getOffer("%E5%A4%A7%E6%95%B0%E6%8D%AE"));
		  //4.机器学习
		  list.add(getOffer("%E6%9C%BA%E5%99%A8%E5%AD%A6%E4%B9%A0"));
		  //5.前端
		  list.add(getOffer("%E5%89%8D%E7%AB%AF"));
		  //6.算法
		  list.add(getOffer("%E7%AE%97%E6%B3%95"));
		  return list;
	  }
	  public static void createJsonData(List<Offer> list){
		  JSONArray array = new JSONArray();
		  for(int i = 0;i<list.size();i++){
			  JSONObject obj = new JSONObject();
			  obj.put("name",list.get(i).getName());
			  obj.put("value",list.get(i).getData().size());
			  array.put(obj);
		  }
		  try {
			writeText.writeDataText("D:/demo/jsonData/countNumber.json","countNumber:"+array.toString());
			System.out.println("写入countNumber成功!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		  List<Double> low = new ArrayList<Double>();
		  for(int i = 0;i<list.size();i++){
			  low.add(list.get(i).getLowAverage());
		  }
		  List<Double> high = new ArrayList<Double>();
		  for(int i = 0;i<list.size();i++){
			  high.add(list.get(i).getHighAverage());
		  }
		  try {
			writeText.writeDataText("D:/demo/jsonData/jobPrice_low.json","jobPrice_low:"+low.toString());
			writeText.writeDataText("D:/demo/jsonData/jobPrice_high.json","jobPrice_high:"+high.toString());
			System.out.println("jobPrice写入成功!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		  for(int i = 0;i<list.size();i++){
			  HashMap<String,Integer> areaCountMap = list.get(i).getAreaCount();
			  JSONArray areaArray = new JSONArray();
			  for(String areaName:areaCountMap.keySet()){
				  JSONObject areaDataJson = new JSONObject();
				  areaDataJson.put("name",areaName);
				  areaDataJson.put("value",areaCountMap.get(areaName));
				  areaArray.put(areaDataJson);
			  }
			 try {
				writeText.writeDataText("D:/demo/jsonData/areaCount_"+list.get(i).getName()+".json","areaCount:"+areaArray.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }
		  System.out.println("areaCount写入成功!");
		//统计城市的情况
		  //1.先将数据放到城市里面
		  List<City> cityList = new ArrayList<City>();
		  City city1 = new City();
		  city1.setName("北京");
		  cityList.add(city1);
		  City city2 = new City();
		  city2.setName("上海");
		  cityList.add(city2);
		  City city3 = new City();
		  city3.setName("广州");
		  cityList.add(city3);
		  City city4 = new City();
		  city4.setName("深圳");
		  cityList.add(city4);
		  City city5 = new City();
		  city5.setName("杭州");
		  cityList.add(city5);
		  City city6 = new City();
		  city6.setName("成都");
		  cityList.add(city6);
		  for(int i = 0;i<cityList.size();i++){
//			  HashMap<String,List<HashMap<String,String>>> offerDataMap = cityList.get(i).getData();
			  HashMap<String,List<HashMap<String,String>>> offerDataMap  = new HashMap<String,List<HashMap<String, String>>>();
			  for(int j = 0;j<list.size();j++){
				  List<HashMap<String, String>> dataList = list.get(j).getData();
				  List<HashMap<String,String>> offerDataList = new ArrayList<HashMap<String,String>>();
				  for(int k = 0;k<dataList.size();k++){
					  if(cityList.get(i).getName().equals(dataList.get(	k).get("工作地点"))){
						  HashMap<String,String> dataMap = new HashMap<String,String>();
						  dataMap.put("职位月薪",dataList.get(k).get("职位月薪"));
	/*					  cityDataMap.put(list.get(j).getName(),dataMap);*/
						  offerDataList.add(dataMap);
					  }
				  }
				  offerDataMap.put(list.get(j).getName(),offerDataList);
			  }
			  cityList.get(i).setData(offerDataMap);
		  } 
		  System.out.println("-----------------------------------------------------------------");
		  System.out.println(cityList);
		  //2.再进行统计
		  for(int i = 0;i<cityList.size();i++){
			  HashMap<String,List<HashMap<String,String>>> offerDataMap = cityList.get(i).getData();
			  double averageLowCityPrice = 0,averageHighCityPrice = 0;
			  int averageCityCount = 0;
			  HashMap<String,Double> lowPriceMap = new HashMap<String,Double>();
			  HashMap<String,Double> highPriceMap = new HashMap<String,Double>();
			  for(String offerName:offerDataMap.keySet()){
				  double lowPrice = 0,highPrice=0;
				  int count = 0;
				  List<HashMap<String, String>> offerDataList = offerDataMap.get(offerName);
				  for(int j = 0;j<offerDataList.size();j++){
					  String price = offerDataList.get(j).get("职位月薪");
					  if( (!price.equals("面议")) && (!price.contains("以"))){
						  int low_price = Integer.parseInt(price.substring(0,price.indexOf("-")));
						  int high_price = Integer.parseInt(price.substring(price.indexOf("-")+1));
						  lowPrice = lowPrice+low_price;
						  averageLowCityPrice = averageLowCityPrice+low_price;
						  highPrice = highPrice + high_price;
						  averageHighCityPrice = averageHighCityPrice+high_price;
						  count=count+1;
						  averageCityCount = averageCityCount+1;
					  }
				  }
				  lowPrice = lowPrice/count;
				  highPrice = highPrice/count;
				  System.out.println("lowPrice:"+lowPrice);
				  System.out.println("highPrice:"+highPrice);
				  lowPriceMap.put(offerName,Double.parseDouble((lowPrice+"").substring(0,((lowPrice+"").indexOf(".")+3)>((lowPrice+"").length())?(lowPrice+"").length():(lowPrice+"").indexOf(".")+3)));
				  highPriceMap.put(offerName,Double.parseDouble((highPrice+"").substring(0,((highPrice+"").indexOf(".")+3)>((highPrice+"").length())?(highPrice+"").length():(highPrice+"").indexOf(".")+3)));
			  }
			  averageLowCityPrice = averageLowCityPrice/averageCityCount;
			  averageHighCityPrice = averageHighCityPrice/averageCityCount;
			  cityList.get(i).setAverageLowPrice(lowPriceMap);
			  cityList.get(i).setAverageHighPrice(highPriceMap);
			  cityList.get(i).setAverageLowCityPrice(Double.parseDouble((averageLowCityPrice+"").substring(0,((averageLowCityPrice+"").indexOf(".")+3)>((averageLowCityPrice+"").length())?(averageLowCityPrice+"").length():(averageLowCityPrice+"").indexOf(".")+3)));
			  cityList.get(i).setAverageHighCityPrice(Double.parseDouble((averageHighCityPrice+"").substring(0,((averageHighCityPrice+"").indexOf(".")+3)>((averageHighCityPrice+"").length())?(averageHighCityPrice+"").length():(averageHighCityPrice+"").indexOf(".")+3)));
		  }
		  System.out.println("--------------------------------------------");
		  System.out.println(cityList);
		  //将统计的数据变成和echars相关的数组
		  HashMap<String,List<Double>> averageLowPrice = new HashMap<String,List<Double>>();
		  HashMap<String,List<Double>> averageHighPrice = new HashMap<String,List<Double>>();
		  List<Double> averageCityLowPrice = new ArrayList<Double>();
		  List<Double> averageCityHighPrice = new ArrayList<Double>();
		  for(int i = 0;i<cityList.size();i++){
			  averageCityLowPrice.add(cityList.get(i).getAverageLowCityPrice());
			  averageCityHighPrice.add(cityList.get(i).getAverageHighCityPrice());
		  }
		  for(int i = 0;i<list.size();i++){
			  List<Double> cityLowPrice = new ArrayList<Double>();
			  List<Double> cityHighPrice = new ArrayList<Double>();
			  for(int j = 0;j<cityList.size();j++){
				  cityLowPrice.add(cityList.get(j).getAverageLowPrice().get(list.get(i).getName()));
				  cityHighPrice.add(cityList.get(j).getAverageHighPrice().get(list.get(i).getName()));
			  }
			  averageLowPrice.put(list.get(i).getName(),cityLowPrice);
			  averageHighPrice.put(list.get(i).getName(),cityHighPrice);
		  }
		  System.out.println("-----------------------------------------------------");
		  System.out.println("averageLowPrice:"+averageLowPrice);
		  System.out.println("averageHighPrice:"+averageHighPrice);
		  System.out.println("averageCityLowPrice:"+averageCityLowPrice);
		  System.out.println("averageCityHighPrice:"+averageCityHighPrice);
		//3.输出json数据
		  try {
			writeText.writeDataText("D:/demo/jsonData/cityAverageLow.json","cityAverageLow:"+averageCityLowPrice.toString());
			writeText.writeDataText("D:/demo/jsonData/cityAverageHigh.json","cityAverageHigh:"+averageCityHighPrice.toString());
			for(int i=0;i<list.size();i++){
				writeText.writeDataText("D:/demo/jsonData/cityLowData_"+list.get(i).getName()+".json",list.get(i).getName()+":"+averageLowPrice.get(list.get(i).getName()).toString());
				writeText.writeDataText("D:/demo/jsonData/cityHighData_"+list.get(i).getName()+".json",list.get(i).getName()+":"+averageHighPrice.get(list.get(i).getName()).toString());
			}
		  } catch (IOException e) {
			e.printStackTrace();
		}
	  } 
}
