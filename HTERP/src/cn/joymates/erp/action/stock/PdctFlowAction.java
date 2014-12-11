package cn.joymates.erp.action.stock;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Customer;
import cn.joymates.erp.domain.PdctFlow;
import cn.joymates.erp.domain.Product;
import cn.joymates.erp.domain.Warehouse;
import cn.joymates.erp.service.CustomerService;
import cn.joymates.erp.service.PdctFlowService;
import cn.joymates.erp.service.ProductService;
import cn.joymates.erp.service.RowFlowService;
import cn.joymates.erp.service.WarehousService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class PdctFlowAction extends BaseAction {

	private static final long serialVersionUID = 6287252646839690486L;
	private PdctFlowService pdctService = ServiceProxyFactory.getInstanceNoMybatis(new PdctFlowService());
	private WarehousService wService = ServiceProxyFactory.getInstanceNoMybatis(new WarehousService());
	private CustomerService cService = ServiceProxyFactory.getInstanceNoMybatis(new CustomerService());
	private ProductService pService = ServiceProxyFactory.getInstanceNoMybatis(new ProductService());
	private Product product;
	
	public String showHome(){
		Warehouse w = new Warehouse();
		w.setIsLogout("0");
		List<Warehouse> wList = wService.selectList(w);
		req.setAttribute("wList", wList);
		
		Customer c = new Customer();
		c.setIsLogout("0");
		req.setAttribute("cList", cService.selectList(c));
		
		Product p = new Product();
		p.setIsLogout("0");
		req.setAttribute("pList", pService.selectList(p));
		return "home";
	}
	
	public void findDetail(){
		try {
			product = pService.selectOne(product);
			JSON obj = JSONObject.fromObject(product);
			resp.getWriter().write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
