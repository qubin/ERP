package cn.joymates.erp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class JxlUtil {
	public static boolean getExcel(Map<String, String> colums,List<Map<String, Object>> data,HttpServletRequest req,HttpServletResponse resp,String name){
		try {
			//excel路径
			StringBuilder path = new StringBuilder();
			path.append(req.getSession().getServletContext().getRealPath("/")).append("/WEB-INF/excels/").append(name).append(".xls");
			WritableWorkbook book = Workbook.createWorkbook(new File(path.toString()));
			WritableSheet sheet = book.createSheet("第一页", 0);
			//表头
			int num = 0 ;
			for(Map.Entry<String, String> entry : colums.entrySet()){
				sheet.addCell(new Label(num, 0, entry.getValue()));
				num ++;
			}
			//excel内容
			int num2 = 0;
			for(int i = 0 ; i < data.size(); i ++){
				for(Map.Entry<String, String> entry2 : colums.entrySet()){
					if(entry2.getKey().equals("IS_LOGOUT")){
						if(data.get(i).get(entry2.getKey()).equals("1")){
							sheet.addCell(new Label(num2 ,i + 1,"已注销"));
						}else{
							sheet.addCell(new Label(num2 ,i + 1,"未注销"));
						}
					}else if(entry2.getKey().equals("WEIGHT")){
						sheet.addCell(new Label(num2 ,i + 1,(data.get(i).get(entry2.getKey())).toString()));
					}else{
						sheet.addCell(new Label(num2 ,i + 1,(String) data.get(i).get(entry2.getKey())));
					}
					num2 ++;
				}
				num2 = 0;
			}
			book.write();
	        book.close();
	        return true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return false;
	}
}
