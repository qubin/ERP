package cn.joymates.erp.utils;

import javax.servlet.http.HttpServletResponse;

public class ResponseWriteUtil {
	/**
	 * 返回html
	 * @param response
	 * @param str
	 */
	public static void  responseWrite(HttpServletResponse response,String str) throws Exception{
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.getWriter().write(str.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}
}
