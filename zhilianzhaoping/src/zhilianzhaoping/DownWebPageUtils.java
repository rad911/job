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
            // ������������
             uri = new URL(url);
            // ������
             urlConnection = uri.openConnection();
            //������
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
            //�ر���Դ
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
         	map.put("ְλ����", zwmc);
         	map.put("��˾����", gsmc);
         	map.put("ְλ��н", zwyx);
         	map.put("�����ص�", gzdd);
         	map.put("��������", gxsj);
         	list.add(map);
        }
        return list;
    }
    public static List<HashMap<String,String>> cleanData(List<HashMap<String, String>> list,String word){
    	//��java�Ĵ���
    	if("Java".equals(word)){
    		for(int i = 0;i<list.size();i++){
    			String zwmc = list.get(i).get("ְλ����");
    			//"ְλ����".equals(zwmc)
    			if(zwmc.contains("ְλ����") || !(zwmc.contains("JAva")||zwmc.contains("java") || zwmc.contains("JAVA") || zwmc.contains("Java"))){
 //   				System.out.println("zwmc:"+zwmc);
    				list.remove(i);
    				i--;
    			}else{
    				if(list.get(i).get("�����ص�").indexOf("-")>0){
    					String gzdd = list.get(i).get("�����ص�").substring(0,list.get(i).get("�����ص�").indexOf("-"));
    					list.get(i).remove("�����ص�");
    					list.get(i).put("�����ص�",gzdd);
    				}
    			}
    		}
    		//��c++�Ĵ���
    	}else if("C++".equals(word)){
    		for(int i = 0;i<list.size();i++){
    			String zwmc = list.get(i).get("ְλ����");
    			//"ְλ����".equals(zwmc) 
    			if(zwmc.contains("ְλ����")|| !( zwmc.contains("C++") || zwmc.contains("c++"))){
    				list.remove(i);
    				i--;
    			}else{
    				if(list.get(i).get("�����ص�").indexOf("-")>0){
    					String gzdd = list.get(i).get("�����ص�").substring(0,list.get(i).get("�����ص�").indexOf("-"));
    					list.get(i).remove("�����ص�");
    					list.get(i).put("�����ص�",gzdd);
    				}
    			}
    		}
    		//�Դ����ݵĴ���%E5%A4%A7%E6%95%B0%E6%8D%AE
    	}else if("������".equals(word)){
    		for(int i = 0;i<list.size();i++){
    			String zwmc = list.get(i).get("ְλ����");
    			//"ְλ����".equals(zwmc) 
    			if(zwmc.contains("ְλ����")|| !( zwmc.contains("������") || zwmc.contains("����") || zwmc.contains("hadoop") || zwmc.contains("HADOOP") )){
    				list.remove(i);
    				i--;
    			}else{
    				if(list.get(i).get("�����ص�").indexOf("-")>0){
    					String gzdd = list.get(i).get("�����ص�").substring(0,list.get(i).get("�����ص�").indexOf("-"));
    					list.get(i).remove("�����ص�");
    					list.get(i).put("�����ص�",gzdd);
    				}
    			}
    		}
    		//�Ի���ѧϰ�Ĵ���%E6%9C%BA%E5%99%A8%E5%AD%A6%E4%B9%A0
    	}else if("����ѧϰ".equals(word)){
    		for(int i = 0;i <list.size();i++){
    			String zwmc = list.get(i).get("ְλ����");
    			//"ְλ����".equals(zwmc) 
    			if(zwmc.contains("ְλ����")|| !(zwmc.contains("����ѧϰ") || zwmc.contains("�㷨"))){
    				list.remove(i);
    				i--;
    			}else{
    				if(list.get(i).get("�����ص�").indexOf("-")>0){
    					String gzdd = list.get(i).get("�����ص�").substring(0,list.get(i).get("�����ص�").indexOf("-"));
    					list.get(i).remove("�����ص�");
    					list.get(i).put("�����ص�",gzdd);
    				}
    			}
    		}
    		//��ǰ�˵Ĵ���%E5%89%8D%E7%AB%AF
    	}else if("ǰ��".equals(word)){
    		for(int i = 0;i<list.size();i++){
    			String zwmc = list.get(i).get("ְλ����");
    			//"ְλ����".equals(zwmc)
    			if(zwmc.contains("ְλ����") || !(zwmc.contains("ǰ��") || zwmc.contains("HTML") || zwmc.contains("html") || zwmc.contains("CSS") || zwmc.contains("css")|| zwmc.contains("js") || zwmc.contains("javascript") || zwmc.contains("JavaScript") ||zwmc.contains("JS") )){
    				list.remove(i);
    				i--;
    			}else{
    				if(list.get(i).get("�����ص�").indexOf("-")>0){
    					String gzdd = list.get(i).get("�����ص�").substring(0,list.get(i).get("�����ص�").indexOf("-"));
    					list.get(i).remove("�����ص�");
    					list.get(i).put("�����ص�",gzdd);
    				}
    			}
    		}
    		
    	}else if("�㷨".equals(word)){
    		for(int i = 0;i<list.size();i++){
    			String zwmc = list.get(i).get("ְλ����");
    			//"ְλ����".equals(zwmc)
    			if(zwmc.contains("ְλ����") || !zwmc.contains("�㷨")){
    				list.remove(i);
    				i--;
    			}else{
    				if(list.get(i).get("�����ص�").indexOf("-")>0){
    					String gzdd = list.get(i).get("�����ص�").substring(0,list.get(i).get("�����ص�").indexOf("-"));
    					list.get(i).remove("�����ص�");
    					list.get(i).put("�����ص�",gzdd);
    				}
    			}
    		}
    		
    	}
    	return list;
    }
}