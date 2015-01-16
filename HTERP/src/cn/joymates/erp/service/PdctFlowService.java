package cn.joymates.erp.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mysql.jdbc.ResultSetMetaData;

import cn.joymates.erp.dao.impl.PdctFlowDaoImpl;
import cn.joymates.erp.domain.PdctFlow;
import cn.joymates.erp.utils.db.DbUtils;

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
				+ "LEFT JOIN `t_warehouse` AS w ON cp.`area` = w.`id` where 1 = 1 ");
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

	public List<Map<String, Object>> findBoxNum(String num){
		try {
			Connection conn = null;
			PreparedStatement prst = null;
			ResultSet rs = null;
			String sql = "SELECT CAST(cpn.`box_num` - cpn.`box_no` AS SIGNED) AS prefix ,"
					+ "CAST(cpn.`box_num` AS SIGNED) AS suffix "
					+ "FROM `t_cust_pdct` AS cp "
					+ "LEFT JOIN `t_cus_pno` AS cpn "
					+ "ON cp.`id` = cpn.`cust_pdct_id` "
					+ "LEFT JOIN `t_warehouse` AS w "
					+ "ON cp.`area` = w.`id` "
					+ "WHERE CONCAT(w.`sign1`,cpn.`batch_code`) LIKE '%" + num + "%'";
			conn = DbUtils.getConnection();
			prst = conn.prepareStatement(sql);
			rs = prst.executeQuery();
			ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			int columnCount = md.getColumnCount();
			while (rs.next()) {
				Map<String, Object> rowData = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i), rs.getObject(i));
				}
				list.add(rowData);
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
