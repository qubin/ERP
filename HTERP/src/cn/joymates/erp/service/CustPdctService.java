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

import cn.joymates.erp.dao.impl.CustPdctDaoImpl;
import cn.joymates.erp.domain.CustPdct;
import cn.joymates.erp.utils.db.DbUtils;

public class CustPdctService extends BaseService<CustPdct> {
	
	public CustPdctService(){
		dao = new CustPdctDaoImpl();
	}
	
	public List<Map<String, Object>> findDetail(int id){
		try {
			Connection conn = null;
			PreparedStatement prst = null;
			ResultSet rs = null;
			String sql = "SELECT cp.id,c.name,p.`ht_pn`,cp.`cus_pn` FROM `t_cust_pdct` AS cp "
					+ "LEFT JOIN `t_customer` AS c ON cp.`customer_id` = c.`id` "
					+ "LEFT JOIN `t_product` AS p ON p.`id` = cp.`product_id` where cp.id = " + id ;

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

	
	public List<Map<String, Object>> findAll(String ec_rd,HttpServletRequest req){
		String resultSql = "SELECT cp.id,c.name,cp.cus_pn,p.ht_pn,cp.is_logout,cp.logout_reason "
				+ "FROM t_customer AS c LEFT JOIN t_cust_pdct AS cp "
				+ "ON c.id = cp.customer_id "
				+ "LEFT JOIN t_product AS p ON p.id = cp.product_id where 1 = 1 ORDER BY cp.id DESC limit ?, ? ";
		
		String searchsql = "SELECT count(*) "
				+ "FROM t_customer AS c LEFT JOIN t_cust_pdct AS cp "
				+ "ON c.id = cp.customer_id "
				+ "LEFT JOIN t_product AS p ON p.id = cp.product_id where 1 = 1";
		return dao.getEcsideList(ec_rd, searchsql, resultSql,req);
	}
	
	public List<Map<String, Object>> findAllCustPdct(String queryType,String queryStr){
		try {
			Connection conn = null;
			PreparedStatement prst = null;
			ResultSet rs = null;
			String sql = "SELECT c.name,cp.cus_pn,p.ht_pn,cp.is_logout,cp.logou_reason "
					+ "FROM t_customer AS c LEFT JOIN t_cust_pdct AS cp "
					+ "ON c.id = cp.customer_id "
					+ "LEFT JOIN t_product AS p ON p.id = cp.product_id where 1 = 1";
			
			if(queryType != "" && queryStr != ""){
				sql += " AND " + queryType + " like %'" + queryStr + "'%";
			}
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
