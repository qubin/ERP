package cn.joymates.erp.action.baseinfo;

import java.util.List;
import java.util.Map;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.CustPdct;
import cn.joymates.erp.domain.Customer;
import cn.joymates.erp.domain.Product;
import cn.joymates.erp.service.CustPdctService;
import cn.joymates.erp.service.CustomerService;
import cn.joymates.erp.service.ProductService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class CustPdctAction extends BaseAction {

	private static final long serialVersionUID = 8867290999383483800L;
	private CustPdctService cpservice = ServiceProxyFactory.getInstanceNoMybatis(new CustPdctService());
	private CustomerService cservice = ServiceProxyFactory.getInstanceNoMybatis(new CustomerService());
	private ProductService pservice = ServiceProxyFactory.getInstanceNoMybatis(new ProductService());
	private CustPdct cp;
	
	public String showHome(){
		List<Map<String, Object>> cpList = cpservice.findAll(ec_rd, req);
		req.setAttribute("cpList", cpList);
		req.setAttribute("LOGOUT", CustPdct.logoutMap);
		return "home";
	}
	
	public String showAddUI(){
		req.setAttribute("cList", cservice.selectList(new Customer()));
		req.setAttribute("pList", pservice.selectList(new Product()));
		return "addUI";
	}
	public String showModifyUI(){
		List<Map<String, Object>> obj = cpservice.findDetail(cp.getCpId());
		req.setAttribute("cp", obj.get(0));
		return "modifyUI";
	}
	
	public String modify(){
		cpservice.update(cp);
		return showHome();
	}
	
	public String add(){
		cpservice.save(cp);
		return showHome();
	}
	
	public CustPdct getCp() {
		return cp;
	}
	public void setCp(CustPdct cp) {
		this.cp = cp;
	}
	
	
}
