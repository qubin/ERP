package cn.joymates.erp.action.baseinfo;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Customer;
import cn.joymates.erp.domain.Supplier;
import cn.joymates.erp.domain.SupplyMat;
import cn.joymates.erp.service.SupplyMatService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class SupplyMatAction extends BaseAction {

	private static final long serialVersionUID = -6756044586112508508L;
	private SupplyMatService service = ServiceProxyFactory.getInstanceNoMybatis(new SupplyMatService());	
	private SupplyMat supplyMat;
	
	public String showHome(){
		if(supplyMat == null){
			supplyMat = new SupplyMat();
		}
		List<Map<String, Object>> smList = service.findQuery(ec_rd, null, null, req);
		req.setAttribute("LOGOUT", Customer.logoutMap);
		req.setAttribute("smList", smList);
		return "home";
	}
	
	public String showAddUI(){
		try {
			List<Supplier> supplierList = service.getSupplierList();
			req.setAttribute("supplierList", supplierList);
			return "addUI";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "home";
	}
	
	public String delete(){
		try {
			if("0".equals(supplyMat.getIsLogout())){
				supplyMat.setLogoutReason(" ");
				service.update(supplyMat);
				return showHome();
			}else{
				String str = URLDecoder.decode(supplyMat.getLogoutReason(), "utf-8");
				supplyMat.setLogoutReason(str);
				service.update(supplyMat);
				return showHome();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showHome();
	}
	
	public String find(){
		String queryStr = req.getParameter("queryStr");
		String serachType = req.getParameter("serachType");
		if(queryStr != null && serachType != null){
			if("all".equals(serachType)){
				return showHome();
			}else{
				List<Map<String, Object>> smList = service.findQuery(ec_rd,queryStr,serachType,req);
				req.setAttribute("smList", smList);
				return "home";
			}
		}
		return showHome();
	}
	
	public String add(){
		try {
			service.save(supplyMat);
			return showHome();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "home";
	}
	
	
	
	public String showModifyUI(){
		try {
			List<Supplier> supplierList = service.getSupplierList();
			req.setAttribute("supplierList", supplierList);
			supplyMat = service.selectOne(supplyMat);
			return "modifyUI";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "home";
	}
	
	public String modify(){
		try {
			service.update(supplyMat);
			return showHome();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "home";
	}
	
	public SupplyMat getSupplyMat() {
		return supplyMat;
	}

	public void setSupplyMat(SupplyMat supplyMat) {
		this.supplyMat = supplyMat;
	}
	
	
}
