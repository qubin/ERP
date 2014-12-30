package cn.joymates.erp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.erp.dao.impl.PdctFlowDaoImpl;
import cn.joymates.erp.domain.PdctFlow;

public class PdctFlowService extends BaseService<PdctFlow> {
	public PdctFlowService(){
		dao = new PdctFlowDaoImpl();
	}
	
	public List<Map<String, Object>> findFlow(String ec_rd,String queryType,String queryStr,HttpServletRequest req){
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		resultsql.append("SELECT w.`sign1`,pf.`box_num`,pf.`istct_id`,pf.`batch_code`,pf.`box_no`,pf.`out_person`,pf.`in_or_out`,pf.`out_time`,pf.`count`,p.`ht_pn`,pf.`reamrk`,pf.`is_logout`,pf.`logout_reason` "
				+ "FROM `t_pdct_flow` AS pf  LEFT JOIN `t_cust_pdct` AS cp ON pf.`cust_pdct_Id` = cp.`id` "
				+ "LEFT JOIN `t_product` AS p ON cp.`product_id` = p.`id` "
				+ "LEFT JOIN `t_warehouse` AS w ON cp.`area` = w.`area` where 1 = 1 ");
		if(queryType != null && queryStr != null){
			resultsql.append(" AND ").append(queryType).append(" LIKE '%").append(queryStr).append("%'");
		}
		resultsql.append(" ORDER BY pf.`out_time` DESC limit ?, ? ");
		searchsql.append("SELECT count(*) "
				+ "FROM `t_pdct_flow` AS pf  LEFT JOIN `t_cust_pdct` AS cp ON pf.`cust_pdct_Id` = cp.`id` "
				+ "LEFT JOIN `t_product` AS p ON cp.`product_id` = p.`id` where 1 = 1 ");
		if(queryType != null && queryStr != null){
			searchsql.append(" AND ").append(queryType).append(" LIKE '%").append(queryStr).append("%'");
		}
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(),req);
	}
}
