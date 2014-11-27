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
	
	public List<Map<String, Object>> find(Supplier supplier, String ec_rd, HttpServletRequest req) {
		String suppName = supplier.getName();
		String suppConPerson = supplier.getConPerson();
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		resultsql.append("SELECT * FROM t_supplier where 1=1 ");
		searchsql.append("SELECT COUNT(*) FROM t_supplier where 1=1 ");
		if(!"".equals(suppName) && suppName != null){
			resultsql.append(" AND name LIKE '%" + suppName + "%' ");
			searchsql.append(" AND name LIKE '%" + suppName + "%' ");
		}
		if(!"".equals(suppConPerson) && suppConPerson != null){
			resultsql.append(" AND con_person LIKE '%" + suppConPerson + "%' ");
			searchsql.append(" AND con_person LIKE '%" + suppConPerson + "%' ");
		}
		resultsql.append(" ORDER BY id DESC limit ?, ? ");
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(), req);
	}
	
}
