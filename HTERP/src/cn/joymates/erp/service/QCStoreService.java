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
	
	public List<Map<String, Object>> find(QCStore qcstore,String qcstore_key,String qcstore_name, String ec_rd,HttpServletRequest req) {
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		searchsql.append("SELECT COUNT(*) FROM t_qc AS q LEFT JOIN t_product AS p ON p.id=q.mat_pdct_id LEFT JOIN ( ");
		searchsql.append("SELECT m.id,sm.ht_mat_no FROM t_material AS m LEFT JOIN t_supply_mat AS sm ON m.supplymat_id=sm.id");
		searchsql.append(") AS ma ON q.mat_pdct_id=ma.id WHERE 1=1 ");
		
		resultsql.append("SELECT q.id,q.mat_or_pdct,q.mat_pdct_id,");
		resultsql.append("CASE WHEN q.mat_or_pdct = '1' THEN ma.ht_mat_no WHEN q.mat_or_pdct = '2' THEN p.ht_pn END AS mat_pdct_ht,");
		resultsql.append("q.weight,q.pic_count,q.isolate_reason,q.remark FROM t_qc AS q LEFT JOIN t_product AS p ON p.id=q.mat_pdct_id LEFT JOIN(");
		resultsql.append("SELECT m.id,sm.ht_mat_no FROM t_material AS m LEFT JOIN t_supply_mat AS sm ON m.supplymat_id=sm.id) AS ma ON q.mat_pdct_id=ma.id WHERE 1=1 ");
		if(!"".equals(qcstore.getMatOrPdct()) && qcstore.getMatOrPdct() != null){
			searchsql.append(" AND q.mat_or_pdct='" + qcstore.getMatOrPdct() + "' ");
			resultsql.append(" AND q.mat_or_pdct='" + qcstore.getMatOrPdct() + "' ");
		}
		if("MAT_PDCT_HT".equals(qcstore_key)){
			searchsql.append(" AND (ma.ht_mat_no LIKE '%" + qcstore_name + "%' OR p.ht_pn LIKE '%" + qcstore_name + "%') ");
			resultsql.append(" AND (ma.ht_mat_no LIKE '%" + qcstore_name + "%' OR p.ht_pn LIKE '%" + qcstore_name + "%') ");
		}else if("ISOLATE_REASON".equals(qcstore_key)){
			searchsql.append(" AND q.isolate_reason LIKE '%" + qcstore_name + "%' ");
			resultsql.append(" AND q.isolate_reason LIKE '%" + qcstore_name + "%' ");
		}
		resultsql.append(" ORDER BY id DESC limit ?, ? ");
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(), req);
	}
	
}
