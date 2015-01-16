package cn.joymates.erp.action.baseinfo;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.collections.map.HashedMap;

import com.opensymphony.xwork2.ActionContext;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.CustPdct;
import cn.joymates.erp.domain.Customer;
import cn.joymates.erp.domain.Product;
import cn.joymates.erp.domain.User;
import cn.joymates.erp.service.CustPdctService;
import cn.joymates.erp.service.CustomerService;
import cn.joymates.erp.service.ProductService;
import cn.joymates.erp.service.UserService;
import cn.joymates.erp.utils.JxlUtil;
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
		req.getSession().setAttribute("cexcel", custList);
		return "home";
	}
	
	public void getExcel(){
		try {
			Map<String, String> map = new LinkedHashMap<String,String>();
			map.put("NAME", "名称");
			map.put("CON_PHONE", "联系电话");
			map.put("FAX", "传真");
			map.put("CON_PERSON", "联系人");
			map.put("TARIFF", "税号");
			map.put("BANK", "开户银行");
			map.put("ACCOUNT", "银行账号");
			map.put("ADDRESS", "单位地址");
			map.put("IS_LOGOUT", "是否注销");
			map.put("LOGOUT_REASON", "注销原因");
			map.put("REMARK", "备注");
			String str = URLDecoder.decode(req.getParameter("excelName"), "utf-8");
			List<Map<String, Object>> data = (List<Map<String, Object>>) req.getSession().getAttribute("cexcel");
			boolean flag = JxlUtil.getExcel(map,data, req,resp,str);
			resp.getWriter().write(flag + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String find(){
		String queryStr = req.getParameter("queryStr");
		String serachType = req.getParameter("serachType");
		if(queryStr != null && !"".equals(queryStr)){
			if("all".equals(queryStr)){
				return showHome();
			}else{
				if(serachType != null && !"".equals(serachType)){
					List<Map<String, Object>> custList = service.findQuery(ec_rd,queryStr,serachType,req);
					req.setAttribute("custList", custList);
					req.getSession().setAttribute("cexcel", custList);
					return "home";
				}
			}
		}
		return showHome();
	}
	public String add(){
		try {
			int custId = service.save(cust);
			String loginName = req.getParameter("loginName");
			UserService userService = ServiceProxyFactory.getInstanceNoMybatis(new UserService());
			User u = new User();
			u.setUserId("300");
			u.setUserLoginId(loginName);
			u.setPassword1("123456");
			u.setCreateTime(new Date());
			User createPerson = (User) req.getSession().getAttribute("loggedUser");
			u.setCreatePerson(createPerson.getUserLoginId());
			u.setCustId(custId);
			userService.save(u);
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
		return "addUI";
	}
	
	public String modify(){
		try {
			service.update(cust);
			return showHome();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showHome();
	}
	
	public String showModifyUI(){
		try {
			cust = service.selectOne(cust);
			UserService uService = ServiceProxyFactory.getInstanceNoMybatis(new UserService());
			User user = new User();
			user.setCustId(cust.getCustId());
			req.setAttribute("loginName", uService.selectList(user).get(0).getUserLoginId());
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
