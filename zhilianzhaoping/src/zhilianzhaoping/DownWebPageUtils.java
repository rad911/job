package zhilianzhaoping;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DownWebPageUtils {
    public static String getHtmlCode(String url, String encoding) {
        URL uri =null;
        URLConnection urlConnection =null;
        InputStream inputStream =null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bReader =null;
        StringBuffer sBuffer= new StringBuffer();
        try {
            // 建立网络连接
             uri = new URL(url);
            // 打开连接
             urlConnection = uri.openConnection();
            //输入流
             inputStream = urlConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, encoding);
            bReader = new BufferedReader(inputStreamReader);
            String temp;
            while ((temp = bReader.readLine()) != null) {
                sBuffer.append(temp + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            //关闭资源
            if(bReader!=null){
                try {
                    bReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sBuffer.toString();
}
    public static List<HashMap<String, String>> analyzeHtml(String url, String encoding,String word){
    	String htmlCode = getHtmlCode(url,encoding);
        Document document = Jsoup.parse(htmlCode);
        Elements elements = document.getElementsByClass("newlist");
        List<HashMap<String, String>> list=new ArrayList<>();
        for (Element e : elements) {
            String zwmc = e.getElementsByClass("zwmc").text();
            String gsmc = e.getElementsByClass("gsmc").text();
            String zwyx = e.getElementsByClass("zwyx").text();
            String gzdd = e.getElementsByClass("gzdd").text();
            String gxsj = e.getElementsByClass("gxsj").text();
            HashMap<String, String> map = new HashMap<String,String>();
         	map.put("职位名称", zwmc);
         	map.put("公司名称", gsmc);
         	map.put("职位月薪", zwyx);
         	map.put("工作地点", gzdd);
         	map.put("发布日期", gxsj);
         	list.add(map);
        }
        return list;
    }
    public static List<HashMap<String,String>> cleanData(List<HashMap<String, String>> list,String word){
    	//对java的处理
    	if("Java".equals(word)){
    		for(int i = 0;i<list.size();i++){
    			String zwmc = list.get(i).get("职位名称");
    			//"职位名称".equals(zwmc)
    			if(zwmc.contains("职位名称") || !(zwmc.contains("JAva")||zwmc.contains("java") || zwmc.contains("JAVA") || zwmc.contains("Java"))){
 //   				System.out.println("zwmc:"+zwmc);
    				list.remove(i);
    				i--;
    			}else{
    				if(list.get(i).get("工作地点").indexOf("-")>0){
    					String gzdd = list.get(i).get("工作地点").substring(0,list.get(i).get("工作地点").indexOf("-"));
    					list.get(i).remove("工作地点");
    					list.get(i).put("工作地点",gzdd);
    				}
    			}
    		}
    		//对c++的处理
    	}else if("C++".equals(word)){
    		for(int i = 0;i<list.size();i++){
    			String zwmc = list.get(i).get("职位名称");
    			//"职位名称".equals(zwmc) 
    			if(zwmc.contains("职位名称")|| !( zwmc.contains("C++") || zwmc.contains("c++"))){
    				list.remove(i);
    				i--;
    			}else{
    				if(list.get(i).get("工作地点").indexOf("-")>0){
    					String gzdd = list.get(i).get("工作地点").substring(0,list.get(i).get("工作地点").indexOf("-"));
    					list.get(i).remove("工作地点");
    					list.get(i).put("工作地点",gzdd);
    				}
    			}
    		}
    		//对大数据的处理%E5%A4%A7%E6%95%B0%E6%8D%AE
    	}else if("大数据".equals(word)){
    		for(int i = 0;i<list.size();i++){
    			String zwmc = list.get(i).get("职位名称");
    			//"职位名称".equals(zwmc) 
    			if(zwmc.contains("职位名称")|| !( zwmc.contains("大数据") || zwmc.contains("数据") || zwmc.contains("hadoop") || zwmc.contains("HADOOP") )){
    				list.remove(i);
    				i--;
    			}else{
    				if(list.get(i).get("工作地点").indexOf("-")>0){
    					String gzdd = list.get(i).get("工作地点").substring(0,list.get(i).get("工作地点").indexOf("-"));
    					list.get(i).remove("工作地点");
    					list.get(i).put("工作地点",gzdd);
    				}
    			}
    		}
    		//对机器学习的处理%E6%9C%BA%E5%99%A8%E5%AD%A6%E4%B9%A0
    	}else if("机器学习".equals(word)){
    		for(int i = 0;i <list.size();i++){
    			String zwmc = list.get(i).get("职位名称");
    			//"职位名称".equals(zwmc) 
    			if(zwmc.contains("职位名称")|| !(zwmc.contains("机器学习") || zwmc.contains("算法"))){
    				list.remove(i);
    				i--;
    			}else{
    				if(list.get(i).get("工作地点").indexOf("-")>0){
    					String gzdd = list.get(i).get("工作地点").substring(0,list.get(i).get("工作地点").indexOf("-"));
    					list.get(i).remove("工作地点");
    					list.get(i).put("工作地点",gzdd);
    				}
    			}
    		}
    		//对前端的处理%E5%89%8D%E7%AB%AF
    	}else if("前端".equals(word)){
    		for(int i = 0;i<list.size();i++){
    			String zwmc = list.get(i).get("职位名称");
    			//"职位名称".equals(zwmc)
    			if(zwmc.contains("职位名称") || !(zwmc.contains("前端") || zwmc.contains("HTML") || zwmc.contains("html") || zwmc.contains("CSS") || zwmc.contains("css")|| zwmc.contains("js") || zwmc.contains("javascript") || zwmc.contains("JavaScript") ||zwmc.contains("JS") )){
    				list.remove(i);
    				i--;
    			}else{
    				if(list.get(i).get("工作地点").indexOf("-")>0){
    					String gzdd = list.get(i).get("工作地点").substring(0,list.get(i).get("工作地点").indexOf("-"));
    					list.get(i).remove("工作地点");
    					list.get(i).put("工作地点",gzdd);
    				}
    			}
    		}
    		
    	}else if("算法".equals(word)){
    		for(int i = 0;i<list.size();i++){
    			String zwmc = list.get(i).get("职位名称");
    			//"职位名称".equals(zwmc)
    			if(zwmc.contains("职位名称") || !zwmc.contains("算法")){
    				list.remove(i);
    				i--;
    			}else{
    				if(list.get(i).get("工作地点").indexOf("-")>0){
    					String gzdd = list.get(i).get("工作地点").substring(0,list.get(i).get("工作地点").indexOf("-"));
    					list.get(i).remove("工作地点");
    					list.get(i).put("工作地点",gzdd);
    				}
    			}
    		}
    		
    	}
    	return list;
    }
}