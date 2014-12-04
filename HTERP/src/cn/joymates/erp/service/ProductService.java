package cn.joymates.erp.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import cn.joymates.erp.dao.impl.ProductDaoImpl;
import cn.joymates.erp.domain.Product;

public class ProductService  extends BaseService<Product> {
	
	public ProductService() {
		dao = new ProductDaoImpl();
	}
	
	public List<Map<String, Object>> find(Product product,String product_key,String product_name,String ec_rd,HttpServletRequest req) {
		String searchsql = "",resultsql = "";
		searchsql = " SELECT count(*) FROM (SELECT p.*,c.name AS cusName,pd.batch_code,pd.box_no,w.sign1,w.area AS whArea FROM t_product AS p LEFT JOIN t_customer AS c ON p.customer_id=c.id LEFT JOIN t_pdct_detail AS pd ON p.id=pd.pdct_id LEFT JOIN t_warehouse AS w ON p.area=w.id ) AS pdu WHERE 1=1 ";
		resultsql = " SELECT * FROM (SELECT p.*,c.name AS cusName,pd.batch_code,pd.box_no,w.sign1,w.area AS whArea FROM t_product AS p LEFT JOIN t_customer AS c ON p.customer_id=c.id LEFT JOIN t_pdct_detail AS pd ON p.id=pd.pdct_id LEFT JOIN t_warehouse AS w ON p.area=w.id ) AS pdu WHERE 1=1 ";
		if(!"ALL".equals(product_key) && product_key != null && !"".equals(product_key)){
			searchsql += " AND " + product_key + " LIKE '%" + product_name + "%'";
			resultsql += " AND " + product_key + " LIKE '%" + product_name + "%'";
		}
		resultsql += " ORDER BY id DESC limit ?, ? ";
		return dao.getEcsideList(ec_rd, searchsql, resultsql, req);
	}
	
}
