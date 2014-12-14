package cn.joymates.erp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.erp.dao.impl.MatsoflowDaoImpl;
import cn.joymates.erp.domain.Matsoflow;


public class MatsoflowService  extends BaseService<Matsoflow> {
	
	public MatsoflowService() {
		dao = new MatsoflowDaoImpl();
	}
	
	public List<Map<String, Object>> find(Matsoflow matsoflow,String matsoflow_key,String matsoflow_name, String ec_rd,HttpServletRequest req) {
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		searchsql.append("SELECT COUNT(*) FROM t_so_flow AS sf LEFT JOIN t_material AS m ON sf.mat_d=m.id LEFT JOIN t_supply_mat AS sm ON m.supplymat_id=sm.id where 1=1 ");
		resultsql.append("SELECT sf.*,sm.mat_supplier_name FROM t_so_flow AS sf LEFT JOIN t_material AS m ON sf.mat_d=m.id LEFT JOIN t_supply_mat AS sm ON m.supplymat_id=sm.id where 1=1 ");
		if(!"ALL".equals(matsoflow_key) && !"".equals(matsoflow_key) && matsoflow_key != null){
			resultsql.append(" AND " + matsoflow_key + " LIKE '%" + matsoflow_name + "%' ");
			searchsql.append(" AND " + matsoflow_key + " LIKE '%" + matsoflow_name + "%' ");
		}
		resultsql.append(" ORDER BY sf.id DESC limit ?, ? ");
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(), req);
	}
	
}
