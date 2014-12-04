package cn.joymates.erp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.erp.dao.impl.SupplierDaoImpl;
import cn.joymates.erp.domain.Supplier;


public class SupplierService  extends BaseService<Supplier> {
	
	public SupplierService() {
		dao = new SupplierDaoImpl();
	}
	
	public List<Map<String, Object>> find(Supplier supplier,String product_key,String product_name, String ec_rd,HttpServletRequest req) {
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		resultsql.append("SELECT * FROM t_supplier where 1=1 ");
		searchsql.append("SELECT COUNT(*) FROM t_supplier where 1=1 ");
		if(!"ALL".equals(product_key) && !"".equals(product_key) && product_key != null){
			resultsql.append(" AND " + product_key + " LIKE '%" + product_name + "%' ");
			searchsql.append(" AND " + product_key + " LIKE '%" + product_name + "%' ");
		}
		resultsql.append(" ORDER BY id DESC limit ?, ? ");
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(), req);
	}
	
}
