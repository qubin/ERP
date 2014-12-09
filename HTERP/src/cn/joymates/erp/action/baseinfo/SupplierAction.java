package cn.joymates.erp.action.baseinfo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
		List<Map<String, Object>> supplierList = service.find(supplier,product_key,product_name,ec_rd,req);
		req.setAttribute("supplierList", supplierList);
		req.setAttribute("logoutMap", Supplier.logoutMap);
		return "home";
	}
	
	public String find() {
		if (supplier == null) {
			supplier = new Supplier();
		}
		List<Map<String, Object>> supplierList = service.find(supplier,product_key,product_name,ec_rd,req);
		req.setAttribute("supplierList", supplierList);
		req.setAttribute("logoutMap", Supplier.logoutMap);
		return "home";
	}
	
	public String showAddUI() {
		return "addUI";
	}
	
	public String add() {
		service.save(supplier);	//添加供应商信息
		return showHome();
	}
	
	public String showModifyUI() {
		supplier = service.selectOne(supplier);	//获取供应商 
		return "modifyUI";
	}
	
	public String delete(){
		try {
			if(!"".equals(supplier.getLogoutReason()) && supplier.getLogoutReason() != null){
				String lr = new String(supplier.getLogoutReason().getBytes("ISO-8859-1"),"UTF-8");
				supplier.setLogoutReason(lr);
			}
			service.update(supplier);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return showHome();
	}
	
	public String modify() {
		service.update(supplier);
		return showHome();
	}
	
	
	public void checkCodeIsNull(){
		String strSupCode = req.getParameter("supcode");
		try {
			supplier = new Supplier();
			supplier.setCode(strSupCode);
			List<Supplier> sl = service.selectList(supplier);
			String msg;
			if(sl.size() > 0){
				msg = "false";
			}else{
				msg = "true";
			}
			resp.getWriter().write(msg);
			resp.getWriter().flush();
			resp.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	private static final long serialVersionUID = 1L;
	private SupplierService service = ServiceProxyFactory.getInstanceNoMybatis(new SupplierService());
	private Supplier supplier;
	private String product_key;
	private String product_name;
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getProduct_key() {
		return product_key;
	}

	public void setProduct_key(String product_key) {
		this.product_key = product_key;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
}
