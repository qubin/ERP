package cn.joymates.erp.action.baseinfo;

import java.util.List;
import java.util.Map;
import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Product;
import cn.joymates.erp.service.ProductService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class ProductAction extends BaseAction {

	public String showHome() {
		List<Map<String, Object>> productList = service.find(product,product_key,product_name,ec_rd,req);
		req.setAttribute("productList", productList);
		req.setAttribute("logoutMap", Product.logoutMap);
		return "home";
	}
	
	public String find() {
		if (product == null) {
			product = new Product();
		}
		List<Map<String, Object>> productList = service.find(product,product_key,product_name,ec_rd,req);
		req.setAttribute("productList", productList);
		req.setAttribute("logoutMap", Product.logoutMap);
		return "home";
	}
	
	public String showAddUI() {
		return "addUI";
	}
	
	public String add() {
		service.save(product);
		return showHome();
	}
	
	public String showModifyUI() {
		product = service.selectOne(product);
		return "modifyUI";
	}
	
	public String delete(){
		try {
			service.update(product);
			return showHome();
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String modify() {
		service.update(product);
		return showHome();
	}
	
	public String showDetailUI() {
		
		String searchsql = "",resultsql = "";
		searchsql = " SELECT COUNT(*) FROM t_product AS p LEFT JOIN t_customer AS c ON p.customer_id=c.id LEFT JOIN t_pdct_detail AS pd ON p.id=pd.pdct_id LEFT JOIN t_warehouse AS w ON p.area=w.id WHERE p.id=" + product.getUuid() + " ";
		resultsql = " SELECT p.*,c.name AS cusName,pd.batch_code,pd.box_no,w.sign1,w.area FROM t_product AS p LEFT JOIN t_customer AS c ON p.customer_id=c.id LEFT JOIN t_pdct_detail AS pd ON p.id=pd.pdct_id LEFT JOIN t_warehouse AS w ON p.area=w.id WHERE p.id=" + product.getUuid() + " ";
		resultsql += " ORDER BY p.id DESC limit ?, ? ";
		List<Map<String, Object>> productList = service.getEcsideList(ec_rd, searchsql, resultsql, req);

		req.setAttribute("productInfo", productList.get(0));
		return "detailUI";
	}
	
	
	
	
	
	
	
	
	private static final long serialVersionUID = 1L;
	private ProductService service = ServiceProxyFactory.getInstanceNoMybatis(new ProductService());
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
