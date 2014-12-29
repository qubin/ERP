package cn.joymates.erp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.erp.dao.impl.PdceistctDaoImpl;
import cn.joymates.erp.domain.Pdceistct;

public class PdceistctService extends BaseService<Pdceistct> {

	public PdceistctService() {
		dao = new PdceistctDaoImpl();
	}

	public List<Map<String, Object>> find(Pdceistct pdceistct, String product_key, String product_name, String ec_rd, HttpServletRequest req) {
		
		String searchsql = "", resultsql = "";
		
		searchsql  = " SELECT COUNT(*) FROM t_sell_bill AS sb ";
		searchsql += " LEFT JOIN t_sell_details AS sd ON sb.id = sd.sell_bill_id ";
		searchsql += " LEFT JOIN t_customer AS c ON sb.customer_id=c.id ";
		searchsql += " LEFT JOIN t_cust_pdct AS cp ON c.id=cp.customer_id ";
		searchsql += " WHERE sd.id NOT IN (SELECT DISTINCT s_detail_id FROM t_pdce_istct) ";
		searchsql += " AND sb.verify_status='1' ";
		
		resultsql  = " SELECT *,sb.id as billId,sd.id as detailsId FROM t_sell_bill AS sb ";
		resultsql += " LEFT JOIN t_sell_details AS sd ON sb.id = sd.sell_bill_id ";
		resultsql += " LEFT JOIN t_customer AS c ON sb.customer_id=c.id ";
		resultsql += " LEFT JOIN t_cust_pdct AS cp ON c.id=cp.customer_id ";
		resultsql += " WHERE sd.id NOT IN (SELECT DISTINCT s_detail_id FROM t_pdce_istct) ";
		resultsql += " AND sb.verify_status='1' ";
		
		if (!"ALL".equals(product_key) && product_key != null && !"".equals(product_key)) {
			searchsql += " AND " + product_key + " LIKE '%" + product_name + "%'";
			resultsql += " AND " + product_key + " LIKE '%" + product_name + "%'";
		}
		
		resultsql += " ORDER BY sb.id DESC limit ?, ? ";
		
		return dao.getEcsideList(ec_rd, searchsql, resultsql, req);
	}

	// 审核打印查询
	public List<Map<String, Object>> findPdceistct(Pdceistct pdceistct, String pdceistct_key, String pdceistct_name, String ec_rd, HttpServletRequest req) {
		
		String searchsql = "", resultsql = "";
		
		searchsql = "SELECT COUNT(*) FROM t_pdce_istct AS PI LEFT JOIN t_sell_details AS sd ON pi.s_detail_id=sd.id LEFT JOIN t_sell_bill AS sb ON sb.id=sd.sell_bill_id WHERE pi.verify_status='0' ";
		
		resultsql  = " SELECT *,pi.id AS pdceIstctId,sd.id AS sellDetailsId,sb.id AS sellBillId FROM t_pdce_istct AS PI LEFT JOIN t_sell_details AS sd ON pi.s_detail_id=sd.id ";
		resultsql += " LEFT JOIN t_sell_bill AS sb ON sb.id=sd.sell_bill_id ";
		resultsql += " LEFT JOIN t_customer AS c ON sb.customer_id=c.id ";
		resultsql += " LEFT JOIN t_cust_pdct AS cp ON cp.customer_id=c.id ";
		resultsql += " WHERE pi.verify_status='0' ";
		
		if (!"ALL".equals(pdceistct_key) && pdceistct_key != null && !pdceistct_key.equals("")) {
			searchsql += " and " + pdceistct_key + " like '%" + pdceistct_name + "%'";
			resultsql += " and " + pdceistct_key + " like '%" + pdceistct_name + "%'";
		}
		
		resultsql += "ORDER BY pi.id DESC limit ?, ?";
		
		return dao.getEcsideList(ec_rd, searchsql, resultsql, req);
	}
	
	
	// 审核打印-获得指令单信息
	public List<Map<String, Object>> findAuditPdceistct(Pdceistct pdceistct, String ec_rd, HttpServletRequest req) {
		
		String searchsql = "", resultsql = "";
		
		searchsql  = "SELECT COUNT(*) FROM t_pdce_istct AS PI LEFT JOIN ";
		searchsql += "(SELECT m.*,sm.mat_supplier_name,sm.ht_mat_no FROM t_material AS m LEFT JOIN t_supply_mat AS sm ON m.supplymat_id = sm.id) AS mater ON pi.material_id = mater.id ";
		searchsql += "LEFT JOIN t_product AS p ON pi.product_id = p.id ";
		searchsql += "LEFT JOIN t_sell_details AS sd ON pi.s_detail_id = sd.id ";
		searchsql += "LEFT JOIN t_sell_bill AS sb ON sd.sell_bill_id = sb.id ";
		searchsql += "where pi.id=" + pdceistct.getUuid() + " ";
		
		resultsql  = "SELECT *,pi.id AS sczldId FROM t_pdce_istct AS PI LEFT JOIN ";
		resultsql += "(SELECT m.*,sm.mat_supplier_name,sm.ht_mat_no FROM t_material AS m LEFT JOIN t_supply_mat AS sm ON m.supplymat_id = sm.id) AS mater ON pi.material_id = mater.id ";
		resultsql += "LEFT JOIN t_product AS p ON pi.product_id = p.id ";
		resultsql += "LEFT JOIN t_sell_details AS sd ON pi.s_detail_id = sd.id ";
		resultsql += "LEFT JOIN t_sell_bill AS sb ON sd.sell_bill_id = sb.id ";
		resultsql += "LEFT JOIN t_customer AS c ON sb.customer_id=c.id ";
		resultsql += "LEFT JOIN t_cust_pdct AS cp ON c.id=cp.customer_id ";
		resultsql += "where pi.id=" + pdceistct.getUuid() + " ";
		resultsql += "ORDER BY pi.id DESC limit ?, ?";
		
		return dao.getEcsideList(ec_rd, searchsql, resultsql, req);
	}
	

	// 查询已审核的指令单
	public List<Map<String, Object>> findOverPdceistct(Pdceistct pdceistct, String pdceistct_key, String pdceistct_name, String ec_rd, HttpServletRequest req) {
		String searchsql = "", resultsql = "";
		searchsql = "SELECT COUNT(*) FROM t_pdce_istct AS PI LEFT JOIN t_sell_details AS sd ON pi.s_detail_id=sd.id LEFT JOIN t_sell_bill AS sb ON sb.id=sd.sell_bill_id WHERE pi.verify_status='0' ";
		resultsql = "SELECT *,pi.id AS pdceIstctId,sd.id AS sellDetailsId,sb.id AS sellBillId FROM t_pdce_istct AS PI LEFT JOIN t_sell_details AS sd ON pi.s_detail_id=sd.id ";
		resultsql += "LEFT JOIN t_sell_bill AS sb ON sb.id=sd.sell_bill_id WHERE pi.verify_status='1' ";
		if (!"ALL".equals(pdceistct_key) && pdceistct_key != null && !pdceistct_key.equals("")) {
			searchsql += " and " + pdceistct_key + " like '%" + pdceistct_name + "%'";
			resultsql += " and " + pdceistct_key + " like '%" + pdceistct_name + "%'";
		}
		resultsql += "ORDER BY pi.id DESC limit ?, ?";
		return dao.getEcsideList(ec_rd, searchsql, resultsql, req);
	}
	
	// 查询已审核且已结单的指令单
	public List<Map<String, Object>> findAuditOverPdceistct(String verifyStatus,String isOver,Pdceistct pdceistct,String pdceistct_key, String pdceistct_name, String ec_rd, HttpServletRequest req) {
		
		String searchsql = "", resultsql = "";
		
		searchsql = "SELECT COUNT(*) FROM t_pdce_istct AS PI LEFT JOIN t_sell_details AS sd ON pi.s_detail_id=sd.id LEFT JOIN t_sell_bill AS sb ON sb.id=sd.sell_bill_id WHERE pi.verify_status='" + verifyStatus + "' AND pi.is_over='" + isOver + "' ";
		
		resultsql  = "SELECT *,pi.id AS pdceIstctId,sd.id AS sellDetailsId,sb.id AS sellBillId FROM t_pdce_istct AS PI LEFT JOIN t_sell_details AS sd ON pi.s_detail_id=sd.id ";
		resultsql += "LEFT JOIN t_sell_bill AS sb ON sb.id=sd.sell_bill_id  ";
		resultsql += "LEFT JOIN t_customer AS c ON sb.customer_id=c.id ";
		resultsql += "LEFT JOIN t_cust_pdct AS cp ON c.id=cp.customer_id ";
		resultsql += "WHERE pi.verify_status='" + verifyStatus + "' AND pi.is_over='" + isOver + "'"; 
		
//		if (!"ALL".equals(pdceistct_key) && pdceistct_key != null && !pdceistct_key.equals("")) {
//			searchsql += " and " + pdceistct_key + " like '%" + pdceistct_name + "%'";
//			resultsql += " and " + pdceistct_key + " like '%" + pdceistct_name + "%'";
//		}
		
		resultsql += " ORDER BY pi.id DESC limit ?, ?";
		
		return dao.getEcsideList(ec_rd, searchsql, resultsql, req);
	}
	

	/*
	 * 根据生产指令单ID查询指令单相关信息
	 */
	public List<Map<String, Object>> findPdceistctInfoById(Pdceistct pdceistct, String ec_rd, HttpServletRequest req) {
		
		String searchsql = "", resultsql = "";
		
		searchsql  = "SELECT COUNT(*) FROM t_pdce_istct AS PI LEFT JOIN ";
		searchsql += "(SELECT m.*,sm.mat_supplier_name,sm.ht_mat_no FROM t_material AS m LEFT JOIN t_supply_mat AS sm ON m.supplymat_id = sm.id) AS mater ON pi.material_id = mater.id ";
		searchsql += "LEFT JOIN t_product AS p ON pi.product_id = p.id ";
		searchsql += "LEFT JOIN t_sell_details AS sd ON pi.s_detail_id = sd.id ";
		searchsql += "LEFT JOIN t_sell_bill AS sb ON sd.sell_bill_id = sb.id ";
		resultsql += "LEFT JOIN t_customer AS c ON sb.customer_id=c.id ";
		resultsql += "LEFT JOIN t_cust_pdct AS cp ON c.id=cp.customer_id ";
		searchsql += "where pi.id=" + pdceistct.getUuid() + " ";
		
		resultsql  = "SELECT *,pi.id AS sczldId,mater.id AS materialId,p.id AS productId FROM t_pdce_istct AS PI LEFT JOIN ";
		resultsql += "(SELECT m.*,sm.mat_supplier_name,sm.ht_mat_no FROM t_material AS m LEFT JOIN t_supply_mat AS sm ON m.supplymat_id = sm.id) AS mater ON pi.material_id = mater.id ";
		resultsql += "LEFT JOIN t_product AS p ON pi.product_id = p.id ";
		resultsql += "LEFT JOIN t_sell_details AS sd ON pi.s_detail_id = sd.id ";
		resultsql += "LEFT JOIN t_sell_bill AS sb ON sd.sell_bill_id = sb.id ";
		resultsql += "LEFT JOIN t_customer AS c ON sb.customer_id=c.id ";
		resultsql += "LEFT JOIN t_cust_pdct AS cp ON c.id=cp.customer_id ";
		resultsql += "where pi.id=" + pdceistct.getUuid() + " ORDER BY pi.id DESC limit ?, ? ";
		
		return dao.getEcsideList(ec_rd, searchsql, resultsql, req);
	}

}
