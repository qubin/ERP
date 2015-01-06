package cn.joymates.erp.action.baseinfo;

import java.util.List;
import java.util.Map;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Customer;
import cn.joymates.erp.domain.Product;
import cn.joymates.erp.service.CustomerService;
import cn.joymates.erp.service.ProductService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class ProductAction extends BaseAction {

	public String showHome() {
		List<Map<String, Object>> productList = service.find(product,product_key,product_name,ec_rd,req);
		req.setAttribute("productList", productList);
		req.setAttribute("logoutMap", Product.logoutMap);
		req.setAttribute("propertiesMap", Product.propertiesMap);
		req.setAttribute("marketMap", Product.marketMap);
		req.setAttribute("patternTypeMap", Product.patternTypeMap);
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
		return "home";
	}
	
	public String showAddUI() {
		List<Customer> customerList = customerService.selectList(new Customer());
		String searchsql = "SELECT COUNT(*) FROM t_warehouse ";		
		String resultsql = "SELECT * FROM t_warehouse limit ?, ? ";
		List<Map<String, Object>> warehouseList =  service.getEcsideList("100", searchsql, resultsql, req);
		req.setAttribute("customerList", customerList);
		req.setAttribute("warehouseList", warehouseList);
		return "addUI";
	}
	
	public String add() {
		service.save(product);
		return showHome();
	}
	
	public String showModifyUI() {
		List<Customer> customerList = customerService.selectList(new Customer());
		String searchsql = "SELECT COUNT(*) FROM t_warehouse ";		
		String resultsql = "SELECT * FROM t_warehouse limit ?, ? ";
		List<Map<String, Object>> warehouseList =  service.getEcsideList("100", searchsql, resultsql, req);
		product = service.selectOne(product);
		req.setAttribute("customerList", customerList);
		req.setAttribute("warehouseList", warehouseList);
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
		service.update(product);
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
	
	
	
	
	
	
	
	
	private static final long serialVersionUID = 1L;
	private ProductService service = ServiceProxyFactory.getInstanceNoMybatis(new ProductService());
	private CustomerService customerService = ServiceProxyFactory.getInstanceNoMybatis(new CustomerService());
	private Product product;
	private String product_key;
	private String product_name;
	

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
