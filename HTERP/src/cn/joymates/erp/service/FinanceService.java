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
	
	public List<Map<String, Object>> findSellBillList(SellBill sellBill,String finance_key,String finance_name,String ec_rd, HttpServletRequest req){
		String searchsql = "", resultsql = "";
		
		searchsql  = " SELECT COUNT(*) FROM t_sell_bill AS sb ";
		searchsql += " LEFT JOIN t_sell_details AS sd ON sb.id = sd.sell_bill_id ";
		searchsql += " LEFT JOIN t_customer AS c ON sb.customer_id=c.id ";
		searchsql += " LEFT JOIN t_cust_pdct AS cp ON c.id=cp.customer_id ";
		searchsql += " LEFT JOIN t_finance_pay AS fp ON sb.id=fp.sell_bill_id ";
		searchsql += " WHERE sb.verify_status='1' AND sd.amount<>IFNULL(fp.prepaid,0) ";
		
		resultsql  = " SELECT sb.*,sd.*,c.*,cp.*,sb.id AS billId,sd.id AS detailsId,IFNULL(fp.prepaid,0.000) AS prepaid,fp.sell_bill_id AS fpSbId,IFNULL(fp.finance_verify_status,0) AS finance_verify_status,fp.finance_verify_remark,fp.id AS fpid FROM t_sell_bill AS sb ";
		resultsql += " LEFT JOIN t_sell_details AS sd ON sb.id = sd.sell_bill_id ";
		resultsql += " LEFT JOIN t_customer AS c ON sb.customer_id=c.id ";
		resultsql += " LEFT JOIN t_cust_pdct AS cp ON c.id=cp.customer_id ";
		resultsql += " LEFT JOIN t_finance_pay AS fp ON sb.id=fp.sell_bill_id ";
		resultsql += " WHERE sb.verify_status='1' AND sd.amount<>IFNULL(fp.prepaid,0) ";
		if("CUS_PN".equals(finance_key)){
			searchsql += " AND cp." + finance_key + " LIKE '%" + finance_name + "%'";
			resultsql += " AND cp." + finance_key + " LIKE '%" + finance_name + "%'";
		}else if(!"ALL".equals(finance_key) && finance_key != null && !"".equals(finance_key) && !"finance".equals(finance_key)){
			searchsql += " AND sb." + finance_key + " LIKE '%" + finance_name + "%'";
			resultsql += " AND sb." + finance_key + " LIKE '%" + finance_name + "%'";
		}else if("finance".equals(finance_key)){
			searchsql += " AND fp.id is not null and fp.finance_verify_status='0' ";
			resultsql += " AND fp.id is not null and fp.finance_verify_status='0' ";
		}
		resultsql += " ORDER BY sb.id DESC limit ?, ? ";
		
		return dao.getEcsideList(ec_rd, searchsql, resultsql, req);
	}

}
