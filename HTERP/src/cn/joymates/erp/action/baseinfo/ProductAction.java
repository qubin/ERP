package cn.joymates.erp.action.baseinfo;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.CustPdct;
import cn.joymates.erp.domain.CustPno;
import cn.joymates.erp.domain.Customer;
import cn.joymates.erp.domain.Product;
import cn.joymates.erp.service.CustPdctService;
import cn.joymates.erp.service.CustPnoService;
import cn.joymates.erp.service.CustomerService;
import cn.joymates.erp.service.ProductService;
import cn.joymates.erp.utils.JxlUtil;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class ProductAction extends BaseAction {

	public String showHome() {
		List<Map<String, Object>> productList = service.find(product,product_key,product_name,ec_rd,req);
		req.setAttribute("productList", productList);
		req.setAttribute("logoutMap", Product.logoutMap);
		req.setAttribute("propertiesMap", Product.propertiesMap);
		req.setAttribute("marketMap", Product.marketMap);
		req.setAttribute("patternTypeMap", Product.patternTypeMap);
		req.getSession().setAttribute("pexcel", productList);
		return "home";
	}
	
	public String find() {
		if (product == null) {
			product = new Product();
		}
		List<Map<String, Object>> productList = service.find(product,product_key,product_name,ec_rd,req);
		req.setAttribute("productList", productList);
		req.setAttribute("logoutMap", Product.logoutMap);
		req.setAttribute("propertiesMap", Product.propertiesMap);
		req.setAttribute("marketMap", Product.marketMap);
		req.setAttribute("patternTypeMap", Product.patternTypeMap);
		req.getSession().setAttribute("pexcel", productList);
		return "home";
	}
	public void getExcel(){
		try {
			Map<String, String> map = new LinkedHashMap<String,String>();
			map.put("HT_PN", "华天产品编号");
			map.put("CUS_PN", "客户产品编号");
			map.put("PROPERTIES", "产品性质");
			map.put("MARKET", "产品市场");
			map.put("PATTERN_TYPE", "模具类型");
			map.put("IS_LOGOUT", "是否注销");
			map.put("LOGOUT_REASON", "注销原因");
			String str = URLDecoder.decode(req.getParameter("excelName"), "utf-8");
			List<Map<String, Object>> data = (List<Map<String, Object>>) req.getSession().getAttribute("pexcel");
			boolean flag = JxlUtil.getExcel(map,data, req,resp,str);
			resp.getWriter().write(flag + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String showAddUI() {
		List<Customer> customerList = customerService.selectList(new Customer());
		String searchsql = "SELECT COUNT(*) FROM t_warehouse ";		
		String resultsql = "SELECT * FROM t_warehouse limit ?, ? ";
		List<Map<String, Object>> warehouseList =  service.getEcsideList("100", searchsql, resultsql, req);
		req.setAttribute("customerList", customerList);
		req.setAttribute("warehouseList", warehouseList);
		Customer c = new Customer();
		c.setIsLogout("0");
		req.setAttribute("clist", customerService.selectList(c));
		return "addUI";
	}
	
	public String add() {
		try {
			int prodId = service.save(product);
			cp.setProdId(prodId);
			int cpId = cpService.save(cp);
			CustPno cpNo = new CustPno();
			cpNo.setcPdctId(cpId);
			cpnService.save(cpNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showHome();
	}
	
	public String showModifyUI() {
		product = service.selectOne(product);
		Customer c = new Customer();
		c.setIsLogout("0");
		req.setAttribute("clist", customerService.selectList(c));
		List<Customer> customerList = customerService.selectList(new Customer());
		req.setAttribute("customerList", customerList);
		return "modifyUI";
	}
	
	public String delete(){
		try {
			if(!"".equals(product.getLogoutReason()) && product.getLogoutReason() != null){
				String lr = new String(product.getLogoutReason().getBytes("ISO-8859-1"),"UTF-8");
				product.setLogoutReason(lr);
			}
			service.update(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showHome();
	}
	
	public String modify() {
		try {
			String cpn = req.getParameter("cpn");
			cp.setProdId(product.getUuid());
			List<CustPdct> l = cpService.selectList(cp);
			if(l.size() > 0){
				for(int i = 0; i < l.size(); i ++){
					l.get(i).setCus_pn(cpn);
					cpService.update((CustPdct) l);
				}
			}else{
				CustPdct newCp = new CustPdct();
				newCp.setCus_pn(cpn);
				newCp.setCustId(cp.getCustId());
				newCp.setProdId(product.getUuid());
				cpService.save(newCp);
			}
			service.update(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showHome();
	}
	
	public String showDetailUI() {
		
		String searchsql = "",resultsql = "";
		searchsql = " SELECT count(*) FROM t_product AS p LEFT JOIN t_cust_pdct AS cp ON p.id = cp.product_id LEFT JOIN t_customer AS c ON c.id=cp.customer_id LEFT JOIN t_warehouse AS w  ON cp.area = w.id WHERE p.id=" + product.getUuid() + " ";
		resultsql = " SELECT p.*,c.name AS cusName,cp.cus_pn,w.sign1,w.area AS whArea FROM t_product AS p LEFT JOIN t_cust_pdct AS cp ON p.id = cp.product_id LEFT JOIN t_customer AS c ON c.id=cp.customer_id LEFT JOIN t_warehouse AS w  ON cp.area = w.id WHERE p.id=" + product.getUuid() + " ";
		resultsql += " ORDER BY p.id DESC limit ?, ? ";
		List<Map<String, Object>> productList = service.getEcsideList(ec_rd, searchsql, resultsql, req);

		req.setAttribute("productInfo", productList.get(0));
		return "detailUI";
	}
	
	public void getCpn(){
		try {
			cp.setProdId(product.getUuid());
			List<CustPdct> l = cpService.selectList(cp);
			if(l.size() == 0){
				resp.getWriter().write("");
			}else{
				JSONObject obj = JSONObject.fromObject(l.get(0));
				resp.getWriter().write(obj.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	private static final long serialVersionUID = 1L;
	private ProductService service = ServiceProxyFactory.getInstanceNoMybatis(new ProductService());
	private CustomerService customerService = ServiceProxyFactory.getInstanceNoMybatis(new CustomerService());
	private CustPdctService cpService = ServiceProxyFactory.getInstanceNoMybatis(new CustPdctService());
	private CustPnoService cpnService = ServiceProxyFactory.getInstanceNoMybatis(new CustPnoService());
	private CustPdct cp;
	private Product product;
	private String product_key;
	private String product_name;
	
	
	public CustPdct getCp() {
		return cp;
	}

	public void setCp(CustPdct cp) {
		this.cp = cp;
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

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}

