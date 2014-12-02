package cn.joymates.erp.action.baseinfo;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ModelDriven;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Customer;
import cn.joymates.erp.service.CustomerService;
import cn.joymates.erp.service.SupplierService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class CustomerAction extends BaseAction{
	
	private static final long serialVersionUID = -5181364297104798752L;
	private CustomerService service = ServiceProxyFactory.getInstanceNoMybatis(new CustomerService());
	private Customer cust;
	
	public String showhome(){
		if(cust == null){
			cust = new Customer();
		}
		List<Map<String, Object>> custList = service.findAll(cust, ec_rd, req);
		req.setAttribute("custList", custList);
		return "home";
	}
	public String find(){
		String queryStr = req.getParameter("queryStr");
		String serachType = req.getParameter("serachType");
		if(queryStr != null && serachType != null){
			
				List<Map<String, Object>> custList = service.findQuery(ec_rd,queryStr,serachType,req);
				req.setAttribute("custList", custList);
				return "home";
			
		}
		return "fail";
	}
	public String add(){
		try {
			service.save(cust);
			return showhome();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}
	
	public String delete(){
		try {
			service.delete(cust);
			return showhome();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}
	
	public String showAddUI(){
		return "addUI";
	}
	
	public String modify(){
		try {
			service.update(cust);
			return showhome();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}
	
	public String showModifyUI(){
		try {
			cust = service.selectOne(cust);
			return "modifyUI";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}
	
	
	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}	
}
