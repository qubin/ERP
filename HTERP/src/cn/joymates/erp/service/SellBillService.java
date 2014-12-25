package cn.joymates.erp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.erp.dao.impl.SellBillDaoImpl;
import cn.joymates.erp.domain.Customer;
import cn.joymates.erp.domain.SellBill;

public class SellBillService extends BaseService<SellBill> {
	public SellBillService(){
		dao = new SellBillDaoImpl();
	}
	
	public List<Map<String, Object>> findAll(String ec_rd, HttpServletRequest req,String str){
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		resultsql.append("SELECT sb.`verify_remark`,sb.`verify_status`,sb.`id`,sb.`code`,sb.`order_date`,c.`name`,c.`con_person`,c.`con_phone`,sb.`remark` ");
		resultsql.append("FROM `t_sell_bill` AS sb LEFT JOIN `t_customer` AS c ON sb.`customer_id` = c.`id` where 1 = 1 ");
		if("showHome".equals(str)){
			resultsql.append(" AND sb.verify_status = '0' or sb.verify_status = '2' ");
		}else if("examine".equals(str)){
			resultsql.append(" AND sb.verify_status = '1' ");
		}
		resultsql.append(" ORDER BY sb.`order_date` DESC limit ?, ? ");
		searchsql.append("SELECT count(*) FROM `t_sell_bill` AS sb LEFT JOIN `t_customer` AS c ON sb.`customer_id` = c.`id` where 1 = 1 ");
		if("showHome".equals(str)){
			searchsql.append(" AND sb.verify_status = '0' or sb.verify_status = '2' ");
		}else if("examine".equals(str)){
			searchsql.append(" AND sb.verify_status = '1' ");
		}
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(),req);
	}

	public List<Map<String, Object>> findQuery(String ec_rd, String queryStr,
			String serachType, HttpServletRequest req,String str) {
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		resultsql.append("SELECT  sb.`verify_remark`,sb.`verify_status`,sb.`id`,sb.`code`,sb.`order_date`,c.`name`,c.`con_person`,c.`con_phone`,sb.`remark` ");
		resultsql.append("FROM `t_sell_bill` AS sb LEFT JOIN `t_customer` AS c ON sb.`customer_id` = c.`id` where 1 =1 AND ");
		if("showHome".equals(str)){
			resultsql.append("(sb.verify_status = '0' or sb.verify_status = '2') AND ");
		}else if("examine".equals(str)){
			resultsql.append("sb.verify_status = '1' AND ");
		}
		resultsql.append(serachType).append(" like ").append("'%").append(queryStr).append("%'");
		resultsql.append(" ORDER BY sb.`order_date` DESC limit ?, ? ");
		searchsql.append("SELECT count(*) FROM `t_sell_bill` AS sb LEFT JOIN `t_customer` AS c ON sb.`customer_id` = c.`id` where 1 = 1 AND ");
		if("showHome".equals(str)){
			searchsql.append("(sb.verify_status = '0' or sb.verify_status = '2') AND ");
		}else if("examine".equals(str)){
			searchsql.append("sb.verify_status = '1' AND ");
		}
		searchsql.append(serachType).append(" like ").append("'%").append(queryStr).append("%'");
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(),req);
	}
}
