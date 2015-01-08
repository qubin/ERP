package cn.joymates.erp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.erp.dao.impl.FinancePayDaoImpl;
import cn.joymates.erp.domain.FinancePay;
import cn.joymates.erp.domain.SellBill;


public class FinanceService extends BaseService<FinancePay> {
	public FinanceService() {
		dao = new FinancePayDaoImpl();
	}
	
	/*
	 * 查询已审核销售单列表
	 */
	public List<Map<String, Object>> findSellBillList01(SellBill sellBill,String finance_key,String finance_name,String ec_rd, HttpServletRequest req){
		String searchsql = "", resultsql = "";
		
		searchsql  = " SELECT ";
		searchsql += " COUNT(*) ";
		searchsql += " FROM ";
		searchsql += " t_sell_bill AS sb ";
		searchsql += " LEFT JOIN t_sell_details AS sd ";
		searchsql += " ON sb.id = sd.sell_bill_id ";
		searchsql += " LEFT JOIN t_cust_pdct AS cp ";
		searchsql += " ON sd.cust_pdct_id = cp.id ";
		searchsql += " LEFT JOIN (SELECT sell_bill_id,SUM(prepaid) AS prepaid,finance_verify_status FROM  t_finance_pay WHERE finance_verify_status='1' GROUP BY sell_bill_id) AS fp ";
		searchsql += " ON fp.sell_bill_id=sb.id ";
		searchsql += " WHERE sb.verify_status = '1' AND sd.amount <> IFNULL(fp.prepaid, 0) ";
		
		resultsql  = " SELECT ";
		resultsql += " sb.*,sd.*,cp.*,sb.id AS billId,sd.id AS detailsId,IFNULL(fp.prepaid, 0.000) AS prepaid,fp.sell_bill_id AS fpsbid,IFNULL(fp.finance_verify_status, 0) AS finance_verify_status ";
		resultsql += " FROM ";
		resultsql += " t_sell_bill AS sb ";
		resultsql += " LEFT JOIN t_sell_details AS sd ";
		resultsql += " ON sb.id = sd.sell_bill_id ";
		resultsql += " LEFT JOIN t_cust_pdct AS cp ";
		resultsql += " ON sd.cust_pdct_id = cp.id ";
		resultsql += " LEFT JOIN (SELECT sell_bill_id,SUM(prepaid) AS prepaid,finance_verify_status FROM  t_finance_pay WHERE finance_verify_status='1' GROUP BY sell_bill_id) AS fp ";
		resultsql += " ON fp.sell_bill_id=sb.id ";
		resultsql += " WHERE sb.verify_status = '1' AND sd.amount <> IFNULL(fp.prepaid, 0) ";
		
		if("CUS_PN".equals(finance_key)){
			searchsql += " AND cp." + finance_key + " LIKE '%" + finance_name + "%'";
			resultsql += " AND cp." + finance_key + " LIKE '%" + finance_name + "%'";
		}else if("finance".equals(finance_key)){
			searchsql += " AND fp.id is not null and fp.finance_verify_status='0' ";
			resultsql += " AND fp.id is not null and fp.finance_verify_status='0' ";
		}else if(!"ALL".equals(finance_key) && finance_key != null && !"".equals(finance_key) && !"finance".equals(finance_key)){
			searchsql += " AND sb." + finance_key + " LIKE '%" + finance_name + "%'";
			resultsql += " AND sb." + finance_key + " LIKE '%" + finance_name + "%'";
		}
		
		resultsql += " ORDER BY sb.id DESC limit ?, ? ";
		
		return dao.getEcsideList(ec_rd, searchsql, resultsql, req);
	}
	
	/*
	 * 根据销售单 ID查询销售单信息
	 */
	public List<Map<String, Object>> findSellBillList02(SellBill sellBill,String finance_key,String finance_name,String ec_rd, HttpServletRequest req){
		String searchsql = "", resultsql = "";
		
		searchsql  = " SELECT COUNT(*) FROM t_sell_bill AS sb ";
		searchsql += " LEFT JOIN t_sell_details AS sd ON sb.id = sd.sell_bill_id ";
		searchsql += " LEFT JOIN t_cust_pdct AS cp ON sd.cust_pdct_id = cp.id ";
		searchsql += " LEFT JOIN t_finance_pay AS fp ON sb.id=fp.sell_bill_id ";
		searchsql += " WHERE sb.verify_status='1' AND sd.amount<>IFNULL(fp.prepaid,0) ";
		
		resultsql  = " SELECT sb.*,sd.*,cp.*,sb.id AS billId,sd.id AS detailsId,IFNULL(ffpp.ffppPrepaid,0.000) AS prepaid,fp.sell_bill_id AS fpSbId,IFNULL(fp.finance_verify_status,0) AS finance_verify_status,fp.finance_verify_remark,fp.id AS fpid FROM t_sell_bill AS sb ";
		resultsql += " LEFT JOIN t_sell_details AS sd ON sb.id = sd.sell_bill_id ";
		resultsql += " LEFT JOIN t_cust_pdct AS cp ON sd.cust_pdct_id = cp.id ";
		resultsql += " LEFT JOIN t_finance_pay AS fp ON sb.id=fp.sell_bill_id ";
		resultsql += " LEFT JOIN (SELECT sell_bill_id,SUM(prepaid) AS ffppPrepaid FROM t_finance_pay WHERE finance_verify_status='1' GROUP BY sell_bill_id) AS ffpp ";
		resultsql += " ON sb.id=ffpp.sell_bill_id ";
		resultsql += " WHERE sb.verify_status='1' AND sd.amount<>IFNULL(fp.prepaid,0) ";
		
		if("finance".equals(finance_key)){
			searchsql += " AND fp.id is not null and fp.finance_verify_status='0' ";
			resultsql += " AND fp.id is not null and fp.finance_verify_status='0' ";
		}else if("CUS_PN".equals(finance_key)){
			searchsql += " AND cp." + finance_key + " LIKE '%" + finance_name + "%'";
			resultsql += " AND cp." + finance_key + " LIKE '%" + finance_name + "%'";
		}else if(!"ALL".equals(finance_key) && finance_key != null && !"".equals(finance_key) && !"finance".equals(finance_key)){
			searchsql += " AND sb." + finance_key + " LIKE '%" + finance_name + "%'";
			resultsql += " AND sb." + finance_key + " LIKE '%" + finance_name + "%'";
		}
		resultsql += " ORDER BY sb.id DESC limit ?, ? ";
		
		return dao.getEcsideList(ec_rd, searchsql, resultsql, req);
	}
	
	
	/*
	 * 财务-审核查询
	 */
	public List<Map<String, Object>> findSellBillList03(SellBill sellBill,String type,String finance_key,String finance_name,String ec_rd, HttpServletRequest req){
		String searchsql = "", resultsql = "";
		
		searchsql  = " SELECT ";
		searchsql += " COUNT(*) ";
		searchsql += " FROM ";
		searchsql += " t_sell_bill AS sb ";
		searchsql += " LEFT JOIN t_sell_details AS sd ";
		searchsql += " ON sb.id = sd.sell_bill_id ";
		searchsql += " LEFT JOIN t_cust_pdct AS cp ";
		searchsql += " ON sd.cust_pdct_id = cp.id ";
		searchsql += " LEFT JOIN (SELECT sell_bill_id,SUM(prepaid) AS prepaid,finance_verify_status FROM  t_finance_pay WHERE finance_verify_status='1' GROUP BY sell_bill_id) AS fp ";
		searchsql += " ON fp.sell_bill_id=sb.id ";
		searchsql += " WHERE sb.verify_status = '1' AND fp.finance_verify_status='1' ";
		
		resultsql  = " SELECT ";
		resultsql += " sb.*,sd.*,cp.*,sb.id AS billId,sd.id AS detailsId,IFNULL(fp.prepaid, 0.000) AS prepaid,fp.sell_bill_id AS fpsbid,IFNULL(fp.finance_verify_status, 0) AS finance_verify_status ";
		resultsql += " FROM ";
		resultsql += " t_sell_bill AS sb ";
		resultsql += " LEFT JOIN t_sell_details AS sd ";
		resultsql += " ON sb.id = sd.sell_bill_id ";
		resultsql += " LEFT JOIN t_cust_pdct AS cp ";
		resultsql += " ON sd.cust_pdct_id = cp.id ";
		resultsql += " LEFT JOIN (SELECT sell_bill_id,SUM(prepaid) AS prepaid,finance_verify_status FROM  t_finance_pay WHERE finance_verify_status='1' GROUP BY sell_bill_id) AS fp ";
		resultsql += " ON fp.sell_bill_id=sb.id ";
		resultsql += " WHERE sb.verify_status = '1' AND fp.finance_verify_status='1' ";
		
		if("3".equals(type) && finance_key != null && !"".equals(finance_key)){
			searchsql += " AND order_date BETWEEN '" + finance_key + "' AND '" + finance_name + "' ";
			resultsql += " AND order_date BETWEEN '" + finance_key + "' AND '" + finance_name + "' ";
		}else if(!"ALL".equals(finance_key) && finance_key != null && !"".equals(finance_key) && !"3".equals(finance_key)){
			searchsql += " AND sb." + finance_key + " LIKE '%" + finance_name + "%' ";
			resultsql += " AND sb." + finance_key + " LIKE '%" + finance_name + "%' ";
		}
		
		resultsql += " ORDER BY sb.id DESC limit ?, ? ";
		
		return dao.getEcsideList(ec_rd, searchsql, resultsql, req);
	}

}
