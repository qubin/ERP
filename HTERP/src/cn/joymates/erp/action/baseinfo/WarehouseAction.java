package cn.joymates.erp.action.baseinfo;

import java.util.List;
import java.util.Map;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Customer;
import cn.joymates.erp.domain.Warehouse;
import cn.joymates.erp.service.CustomerService;
import cn.joymates.erp.service.WarehousService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class WarehouseAction extends BaseAction{

	private static final long serialVersionUID = 7984522836906622668L;
	private WarehousService service = ServiceProxyFactory.getInstanceNoMybatis(new WarehousService());
	private Warehouse wh;
	
	public String showhome(){
		if(wh == null){
			wh = new Warehouse();
		}
		List<Map<String, Object>> whList = service.findAll(wh,ec_rd,req);
		req.setAttribute("LOGOUT", Warehouse.logoutMap);
		req.setAttribute("whList", whList);
		return "home";
	}
	
	public String find(){
		String queryStr = req.getParameter("queryStr");
		String serachType = req.getParameter("serachType");
		if(queryStr != null && serachType != null){
			if("all".equals(serachType)){
				return showhome();
			}else{
				List<Map<String, Object>> whList = service.findQuery(ec_rd,queryStr,serachType,req);
				req.setAttribute("whList", whList);
				return "home";
			}
		}
		return "home";
	}
	
	public String showAddUI(){
		return "addUI";
	}
	
	public String add(){
		try {
			service.save(wh);
			return showhome();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "home";
	}
	
	public String showModifyUI(){
		try {
			wh = service.selectOne(wh);
			return "modifyUI";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "home";
	}
	
	public String modify(){
		try {
			service.update(wh);
			return showhome();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "home";
	}
	
	public String delete(){
		try {
			if("0".equals(wh.getIsLogout())){
				wh.setLogOutReason(" ");
				service.update(wh);
				return showhome();
			}else{
				//
				int result = service.checkDeleteWarehouse(wh);
				if(result == 0){
					service.update(wh);
					return showhome();
				}
				req.setAttribute("msg", 1);
				return showhome();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "home";
	}
	
	public Warehouse getWh() {
		return wh;
	}

	public void setWh(Warehouse wh) {
		this.wh = wh;
	}
	
}
