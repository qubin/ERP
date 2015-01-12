package cn.joymates.erp.action.download;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

import cn.joymates.erp.action.BaseAction;

public class DownloadAction extends BaseAction {

	private static final long serialVersionUID = -9042509384653704092L;
	private String fileName;
	
	public InputStream getExcel() throws IOException{
		String str = URLDecoder.decode(req.getParameter("excelName"), "utf-8");
		String tempStr = new String(str.getBytes(), "ISO8859-1");
		fileName = tempStr + ".xls";
		StringBuilder path = new StringBuilder();
		path.append(req.getSession().getServletContext().getRealPath("/")).append("/WEB-INF/excels/").append(str).append(".xls");
		return new FileInputStream(path.toString());
	}
	
	public String execute(){
		return SUCCESS;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
