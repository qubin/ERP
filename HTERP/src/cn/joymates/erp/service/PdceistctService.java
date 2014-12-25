package cn.joymates.erp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.erp.dao.impl.PdceistctDaoImpl;
import cn.joymates.erp.domain.Pdceistct;

public class PdceistctService  extends BaseService<Pdceistct> {
	
	public PdceistctService() {
		dao = new PdceistctDaoImpl();
	}
	
	public List<Map<String, Object>> find(Pdceistct pdceistct,String product_key,String product_name,String ec_rd,HttpServletRequest req) {
		String searchsql = "",resultsql = "";
		searchsql = "SELECT COUNT(*) FROM t_sell_bill WHERE is_order_over='0' AND verify_status='1' ";
		resultsql = "SELECT * FROM t_sell_bill WHERE is_order_over='0' AND verify_status='1' ";
		if(!"ALL".equals(product_key) && product_key != null && !"".equals(product_key)){
			searchsql += " AND " + product_key + " LIKE '%" + product_name + "%'";
			resultsql += " AND " + product_key + " LIKE '%" + product_name + "%'";
		}
		resultsql += " ORDER BY id DESC limit ?, ? ";
		return dao.getEcsideList(ec_rd, searchsql, resultsql, req);
	}
	
	//审核打印查询
	public List<Map<String, Object>> findPdceistct(Pdceistct pdceistct,String pdceistct_key,String pdceistct_name,String ec_rd,HttpServletRequest req) {
		String searchsql = "",resultsql = "";
		searchsql  = "SELECT COUNT(*) FROM t_pdce_istct AS PI LEFT JOIN t_sell_details AS sd ON pi.s_detail_id=sd.id LEFT JOIN t_sell_bill AS sb ON sb.id=sd.sell_bill_id WHERE pi.verify_status='0' ";
		resultsql  = "SELECT *,pi.id AS pdceIstctId,sd.id AS sellDetailsId,sb.id AS sellBillId FROM t_pdce_istct AS PI LEFT JOIN t_sell_details AS sd ON pi.s_detail_id=sd.id ";
		resultsql += "LEFT JOIN t_sell_bill AS sb ON sb.id=sd.sell_bill_id WHERE pi.verify_status='0' ";
		if(!"ALL".equals(pdceistct_key) && pdceistct_key != null && !pdceistct_key.equals("")){
			searchsql += " and " + pdceistct_key + " like '%" + pdceistct_name + "%'";
			resultsql += " and " + pdceistct_key + " like '%" + pdceistct_name + "%'";
		}
		resultsql += "ORDER BY pi.id DESC limit ?, ?";
		return dao.getEcsideList(ec_rd, searchsql, resultsql, req);
	}
	
}
