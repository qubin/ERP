package cn.joymates.erp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.erp.dao.BaseDao;
import cn.joymates.erp.dao.impl.CustomerDaoImpl;
import cn.joymates.erp.dao.impl.SupplierDaoImpl;
import cn.joymates.erp.domain.Customer;

public class CustomerService extends BaseService<Customer> {
	public CustomerService() {
		dao = new CustomerDaoImpl();
	}
	
	public List<Map<String, Object>> findAll(Customer cust,String ec_rd, HttpServletRequest req){
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		resultsql.append("SELECT * FROM t_customer where 1=1 ORDER BY id DESC limit ?, ? ");
		searchsql.append("SELECT COUNT(*) FROM t_customer where 1=1 ");
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(),req);
	}

	public List<Map<String, Object>> findPage(Customer cust, String ec_rd,HttpServletRequest req) {
		
		return null;
	}

	public List<Map<String, Object>> findQuery(String ec_rd,String queryStr, String serachType,HttpServletRequest req) {
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		resultsql.append("SELECT * FROM t_customer where 1=1 ");
		searchsql.append("SELECT COUNT(*) FROM t_customer where 1=1 ");
		resultsql.append(" AND ").append(serachType).append(" LIKE '%").append(queryStr).append("%'");
		searchsql.append(" AND ").append(serachType).append(" LIKE '%").append(queryStr).append("%'");
		resultsql.append(" ORDER BY id DESC limit ?, ? ");
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(), req);
	}
}
