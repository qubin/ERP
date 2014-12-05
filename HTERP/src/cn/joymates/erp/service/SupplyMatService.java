package cn.joymates.erp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import cn.joymates.erp.dao.ISupplyMatDao;
import cn.joymates.erp.dao.impl.CustomerDaoImpl;
import cn.joymates.erp.dao.impl.SupplyMatImpl;
import cn.joymates.erp.domain.Supplier;
import cn.joymates.erp.domain.SupplyMat;
import cn.joymates.erp.utils.db.SessionFactoryUtil;

public class SupplyMatService extends BaseService<SupplyMat> {
	
	public SupplyMatService() {
		dao = new SupplyMatImpl();
	}
	
	public List<Map<String, Object>> findAll(SupplyMat supplyMat, String ec_rd,
			HttpServletRequest req) {
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		resultsql.append("SELECT * FROM t_supply_mat where 1=1 ORDER BY id DESC limit ?, ? ");
		searchsql.append("SELECT COUNT(*) FROM t_supply_mat where 1=1 ");
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(),req);
	}
	
	public List<Map<String, Object>> findQuery(String ec_rd,String queryStr, String serachType,HttpServletRequest req) {
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		resultsql.append("SELECT * FROM t_supply_mat where 1=1 ");
		searchsql.append("SELECT COUNT(*) FROM t_supply_mat where 1=1 ");
		resultsql.append(" AND ").append(serachType).append(" LIKE '%").append(queryStr).append("%'");
		searchsql.append(" AND ").append(serachType).append(" LIKE '%").append(queryStr).append("%'");
		resultsql.append(" ORDER BY id DESC limit ?, ? ");
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(), req);
	} 
	
	public List<Supplier> getSupplierList(){
		SupplierService supplierService = new SupplierService();
		Supplier supplier = new Supplier();
		supplier.setIsLogout("0");
		List<Supplier> supplierList = supplierService.selectList(supplier);
		return supplierList;
	}
}
