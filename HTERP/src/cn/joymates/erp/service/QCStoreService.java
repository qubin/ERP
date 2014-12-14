package cn.joymates.erp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.erp.dao.impl.QCStoreDaoImpl;
import cn.joymates.erp.domain.QCStore;


public class QCStoreService  extends BaseService<QCStore> {
	
	public QCStoreService() {
		dao = new QCStoreDaoImpl();
	}
	
	public List<Map<String, Object>> find(QCStore qcstore,String product_key,String product_name, String ec_rd,HttpServletRequest req) {
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		searchsql.append("SELECT count(*) FROM t_qc where 1=1 ");
		resultsql.append("SELECT * FROM t_qc where 1=1 ");
//		if(!"ALL".equals(product_key) && !"".equals(product_key) && product_key != null){
//			resultsql.append(" AND " + product_key + " LIKE '%" + product_name + "%' ");
//			searchsql.append(" AND " + product_key + " LIKE '%" + product_name + "%' ");
//		}
		resultsql.append(" ORDER BY id DESC limit ?, ? ");
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(), req);
	}
	
}
