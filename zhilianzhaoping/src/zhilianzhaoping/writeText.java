 package zhilianzhaoping;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;

public class writeText {
	public static void writeDataText(String path,String jsonData) throws IOException{
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path),"UTF-8")); 
		out.write(jsonData);
		out.close();
	}
}
