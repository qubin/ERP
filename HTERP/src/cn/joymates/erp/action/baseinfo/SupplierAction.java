package cn.joymates.erp.action.baseinfo;

import java.util.List;
import java.util.Map;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Supplier;
import cn.joymates.erp.service.SupplierService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class SupplierAction extends BaseAction {

	public String showHome() {
		if (supplier == null) {
			supplier = new Supplier();
		}
		List<Map<String, Object>> supplierList = service.find(supplier, ec_rd, req);
		req.setAttribute("supplierList", supplierList);
		return "home";
	}
	
	public String find() {
		if (supplier == null) {
			supplier = new Supplier();
		}
		List<Map<String, Object>> supplierList = service.find(supplier, ec_rd, req);
		req.setAttribute("supplierList", supplierList);
		return "home";
	}
	
	public String showAddUI() {
		return "addUI";
	}
	
	public String add() {
		service.save(supplier);
		return "home";
	}
	
	public String modifyUI() {
		return "modifyUI";
	}
	
	public String delete(){
		return "home";
	}
	
	public String modify() {
		return "home";
	}
	
	
	
	
	
	
	
	
	
	private static final long serialVersionUID = 1L;
	private SupplierService service = ServiceProxyFactory.getInstanceNoMybatis(new SupplierService());
	private Supplier supplier;
	
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	
}
