package cn.joymates.erp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.erp.dao.impl.MaterialDaoImpl;
import cn.joymates.erp.dao.impl.RowFlowImpl;
import cn.joymates.erp.domain.Material;
import cn.joymates.erp.domain.RowFlow;

public class RowFlowService extends BaseService<RowFlow> {
	
	public RowFlowService(){
		dao = new RowFlowImpl();
	}
	
	public List<Map<String, Object>> findQuery(String ec_rd,String queryStr, String serachType,HttpServletRequest req) {
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		resultsql.append("SELECT rf.`out_person`,rf.`in_or_out`,rf.`out_time`,rf.`weight`,sm.`ht_mat_no`,rf.`reamrk`,rf.`is_logout`,rf.`logout_reason` FROM "
				+ "`t_raw_flow` AS rf LEFT JOIN `t_material` AS m ON rf.`material_id` = m.`id` "
				+ "LEFT JOIN `t_supply_mat` AS sm ON m.`supplymat_id` = sm.`id` where 1 = 1 ");
		if(queryStr != null && serachType != null){
			resultsql.append(" AND ").append(serachType).append(" LIKE '%").append(queryStr).append("%'");
		}
		resultsql.append("  ORDER BY rf.`out_time` DESC limit ?, ?  ");
		searchsql.append("SELECT count(*) FROM `t_raw_flow` AS rf LEFT JOIN `t_material` AS m ON rf.`material_id` = m.`id`LEFT JOIN `t_supply_mat` AS sm ON m.`supplymat_id` = sm.`id` where 1 = 1 ");
		if(queryStr != null && serachType != null){
			searchsql.append(" AND ").append(serachType).append(" LIKE '%").append(queryStr).append("%'");
		}
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(), req);
	}
	
	public Material getWeight(Integer id){
		MaterialDaoImpl materialDao = new MaterialDaoImpl();
		Material m = new Material();
		m.setUuid(id);
		m = materialDao.selectOne(m);
		return m;
	}
}
