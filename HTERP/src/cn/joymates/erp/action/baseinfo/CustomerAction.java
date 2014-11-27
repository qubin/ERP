package cn.joymates.erp.action.baseinfo;

import com.opensymphony.xwork2.ModelDriven;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Customer;
import cn.joymates.erp.service.CustomerService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class CustomerAction extends BaseAction implements ModelDriven<Customer>{
	
	private static final long serialVersionUID = -5181364297104798752L;
	
	private Customer cust;

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	@Override
	public Customer getModel() {
		if(cust == null){
			cust = new Customer();
		}
		return cust;
	}
	
	
}
