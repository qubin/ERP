package cn.joymates.erp.utils;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
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
			WritableCellFormat wcf = new WritableCellFormat();
			wcf.setBackground(getNearestColour("#64B7E2"));
			//表头
			int num = 0 ;
			for(Map.Entry<String, String> entry : colums.entrySet()){
				sheet.addCell(new Label(num, 0, entry.getValue(),wcf));
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
	//转换十六进制颜色为jxl颜色
	public static Colour getNearestColour(String strColor) {  
        Color cl = Color.decode(strColor);  
        Colour color = null;  
        Colour[] colors = Colour.getAllColours();  
        if ((colors != null) && (colors.length > 0)) {  
           Colour crtColor = null;  
           int[] rgb = null;  
           int diff = 0;  
           int minDiff = 999;  
           for (int i = 0; i < colors.length; i++) {  
                crtColor = colors[i];  
                rgb = new int[3];  
                rgb[0] = crtColor.getDefaultRGB().getRed();  
                rgb[1] = crtColor.getDefaultRGB().getGreen();  
                rgb[2] = crtColor.getDefaultRGB().getBlue();  
      
                diff = Math.abs(rgb[0] - cl.getRed())  
                  + Math.abs(rgb[1] - cl.getGreen())  
                  + Math.abs(rgb[2] - cl.getBlue());  
                if (diff < minDiff) {  
                 minDiff = diff;  
                 color = crtColor;  
                }  
           }  
        }  
        if (color == null)  
           color = Colour.BLACK;  
        return color;  
    }  
}
