package cn.joymates.erp.action.stock;

import cn.joymates.erp.action.BaseAction;

public class StockAction extends BaseAction {

	private static final long serialVersionUID = 5587430387777417773L;
	
	public String showHome(){
		return "home";
	}
	
	public String find(){
		String str = req.getParameter("str");
		String secStr = req.getParameter("secStr");
		String queryStr = req.getParameter("queryStr");
		if(str != null && secStr != null){
			if("all".equals(secStr)){
				//所有
				if("1".equals(str)){
					
				}else{
					
				}
			}else{
				//条件
			}
		}
		return "home";
	}
}
