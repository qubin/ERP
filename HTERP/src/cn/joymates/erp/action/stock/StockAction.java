package cn.joymates.erp.action.stock;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.service.StockService;
import cn.joymates.erp.service.SupplierService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class StockAction extends BaseAction {

	private static final long serialVersionUID = 5587430387777417773L;
	private StockService sService = ServiceProxyFactory.getInstanceNoMybatis(new StockService());
	
	public String showHome(){
		req.setAttribute("mList", sService.findAllMaterial("",""));
		return "home";
	}
	
	public String find(){
		String serachType = req.getParameter("serachType");
		String secStr = req.getParameter("secStr");
		String queryStr = req.getParameter("queryStr");
		if(serachType != null && secStr != null){
			if("all".equals(secStr)){
				//全部
				if("1".equals(serachType)){ //原材料
					req.setAttribute("mList", sService.findAllMaterial("",""));
				}else{ //成品
					//待定
				}
				return "home";
			}else{
				//条件 
				if(queryStr != null){
					if("1".equals(serachType)){//原材料
						req.setAttribute("mList", sService.findAllMaterial(secStr,queryStr));
					}else{//成品
						//待定
					}
				}
				return "home";
			}
		}
		return "home";
	}
}
