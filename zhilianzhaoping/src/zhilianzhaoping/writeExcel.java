package zhilianzhaoping;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class writeExcel {
	  public static boolean writeDataExcel(String path,List<HashMap<String, String>> list) throws IOException, RowsExceededException, WriteException{
	    	File file = new File(path);
	    	if(!file.exists()){
	    		FileOutputStream fos = new FileOutputStream(file);
	    		WritableWorkbook workbook = Workbook.createWorkbook(fos);
	    		WritableSheet sheet = workbook.createSheet("First Sheet",0);
	    		Label gsmc = new Label(0,0,"公司名称");
	    		sheet.addCell(gsmc);
	    		Label zwmc = new Label(1,0,"职位名称");
	    		sheet.addCell(zwmc);
	    		Label zwyx = new  Label(2,0,"职位月薪");
	    		sheet.addCell(zwyx);
	    		Label gzdd = new Label(3,0,"工作地点");
	    		sheet.addCell(gzdd);
	    		Label fbrq = new Label(4,0,"发布日期");
	    		sheet.addCell(fbrq);   		
	    		//添加数据
	    		for(int i = 0;i <list.size();i++){
	    			Label gsmcData = new Label(0,i+1,list.get(i).get("公司名称"));
	    			sheet.addCell(gsmcData);
	    			Label zwmcData = new Label(1,i+1,list.get(i).get("职位名称"));
	    			sheet.addCell(zwmcData);
	    			Label zwyxData = new Label(2,i+1,list.get(i).get("职位月薪"));
	    			sheet.addCell(zwyxData);
	    			Label gzddData =  new Label(3,i+1,list.get(i).get("工作地点"));
	    			sheet.addCell(gzddData);
	    			Label fbrqData = new Label(4,i+1,list.get(i).get("发布日期"));
	    			sheet.addCell(fbrqData);
	    		}
	    		
	    		workbook.write();
	    		workbook.close();
	    		fos.close();
	    	}
	    	return true;
	    }
	  public static void writeSumDataExcel(String path,HashMap<String,Integer> map) throws RowsExceededException, WriteException, IOException{
		  File file = new File(path);
	    	if(!file.exists()){
	    		FileOutputStream fos = new FileOutputStream(file);
	    		WritableWorkbook workbook = Workbook.createWorkbook(fos);
	    		WritableSheet sheet = workbook.createSheet("First Sheet",0);
	    		Label zwyx = new Label(0,0,"薪资");
	    		sheet.addCell(zwyx);
	    		Label count = new Label(1,0,"个数");
	    		sheet.addCell(count);
	    		//添加数据
	    		int i = 0;
	    		for(String SumData:map.keySet()){
	    			Label data = new Label(0,++i,SumData);
	    			sheet.addCell(data);
	    			Label dataCount = new Label(1,i,map.get(SumData)+"");
	    			sheet.addCell(dataCount);
	    		}
	    		workbook.write();
	    		workbook.close();
	    		fos.close();
	    	}
	  }
	  public static void writeAreaCountExcel(String path,HashMap<String,Integer> map) throws IOException, RowsExceededException, WriteException{
		  File file = new File(path);
	    	if(!file.exists()){
	    		FileOutputStream fos = new FileOutputStream(file);
	    		WritableWorkbook workbook = Workbook.createWorkbook(fos);
	    		WritableSheet sheet = workbook.createSheet("First Sheet",0);
	    		Label zwyx = new Label(0,0,"城市");
	    		sheet.addCell(zwyx);
	    		Label count = new Label(1,0,"个数");
	    		sheet.addCell(count);
	    		//添加数据
	    		int i = 0;
	    		for(String SumData:map.keySet()){
	    			Label data = new Label(0,++i,SumData);
	    			sheet.addCell(data);
	    			Label dataCount = new Label(1,i,map.get(SumData)+"");
	    			sheet.addCell(dataCount);
	    		}
	    		workbook.write();
	    		workbook.close();
	    		fos.close();
	    	}
	  }
}