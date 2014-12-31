package cn.joymates.erp.action.baseinfo;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.CustPdct;
import cn.joymates.erp.domain.Customer;
import cn.joymates.erp.domain.Product;
import cn.joymates.erp.domain.User;
import cn.joymates.erp.service.CustPdctService;
import cn.joymates.erp.service.CustomerService;
import cn.joymates.erp.service.ProductService;
import cn.joymates.erp.service.UserService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class CustomerAction extends BaseAction{
	
	private static final long serialVersionUID = -5181364297104798752L;
	private CustomerService service = ServiceProxyFactory.getInstanceNoMybatis(new CustomerService());
	private ProductService pservice = ServiceProxyFactory.getInstanceNoMybatis(new ProductService());
	private CustPdctService cpservice = ServiceProxyFactory.getInstanceNoMybatis(new CustPdctService());
	private Customer cust;
	
	public String showHome(){
		if(cust == null){
			cust = new Customer();
		}
		List<Map<String, Object>> custList = service.findAll(cust, ec_rd, req);
		req.setAttribute("LOGOUT", Customer.logoutMap);
		req.setAttribute("custList", custList);
		return "home";
	}
	public String find(){
		String queryStr = req.getParameter("queryStr");
		String serachType = req.getParameter("serachType");
		if(queryStr != null && serachType != null){
			if("all".equals(serachType)){
				return showHome();
			}else{
				List<Map<String, Object>> custList = service.findQuery(ec_rd,queryStr,serachType,req);
				req.setAttribute("custList", custList);
				return "home";
			}
		}
		return "home";
	}
	public String add(){
		try {
			int custId = service.save(cust);
			String loginName = req.getParameter("loginName");
			UserService userService = ServiceProxyFactory.getInstanceNoMybatis(new UserService());
			User u = new User();
			u.setUserId("300");
			u.setUserLoginId(loginName);
			u.setPassword1("123");
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//未完
			
			String[] cusPn = req.getParameterValues("cusPn");
			String[] prodId = req.getParameterValues("prodId");
			if((cusPn != null && !"null".equals(cusPn)) && (prodId != null && !"null".equals(prodId))){
				for(int i = 0; i < cusPn.length; i ++){
					CustPdct cp = new CustPdct();
					cp.setCustId(custId);
					cp.setProdId(Integer.valueOf(prodId[i]));
					cp.setCus_pn(cusPn[i]);
					cpservice.save(cp);
				}
				return showHome();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showHome();
	}
	
	public String delete(){
		try {
			if("0".equals(cust.getIsLogout())){
				cust.setLogOutReason(" ");
				service.update(cust);
				return showHome();
			}else{
				String str = URLDecoder.decode(cust.getLogOutReason(), "utf-8");
				cust.setLogOutReason(str);
				service.update(cust);
				return showHome();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "home";
	}
	
	public String showAddUI(){
		List pList = pservice.selectList(new Product());
		req.setAttribute("pList", pList);
		return "addUI";
	}
	
	public String modify(){
		try {
			service.update(cust);
			String[] cusPn = req.getParameterValues("cusPn");
			String[] cpId = req.getParameterValues("cpId");
			for(int i = 0;i < cusPn.length; i ++){
				CustPdct temp = new CustPdct();
				temp.setCpId(Integer.valueOf(cpId[i]));
				temp = cpservice.selectOne(temp);
				temp.setCus_pn(cusPn[i]);
				cpservice.update(temp);
			}
			
			return showHome();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showHome();
	}
	
	public String showModifyUI(){
		try {
			cust = service.selectOne(cust);
			List<Map<String, Object>> list = service.findModify(cust.getCustId());
			req.setAttribute("dataList", list);
			return "modifyUI";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "home";
	}
	
	
	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}	
}
