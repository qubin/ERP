package cn.joymates.erp.utils;

import java.io.UnsupportedEncodingException;

/**
 * 常用工具类
 * 
 * @author Jackie Hou
 * 
 */
public class Common {

	public static String getEncoding(String str) {
		String[] encodes = {"GB2312", "ISO-8859-1", "UTF-8", "GBK"};
		String retValue = "未知";
		
		for (String ecd : encodes) {
			try {
				String temp = new String(str.getBytes(ecd), ecd);
				if (str.equals(temp)) {
					retValue = ecd;
					break;
				}
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return retValue;
		
	}
}
