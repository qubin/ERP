package cn.joymates.erp.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import com.mysql.jdbc.ResultSetMetaData;

import cn.joymates.erp.dao.impl.MaterialDaoImpl;
import cn.joymates.erp.dao.impl.ProductDaoImpl;
import cn.joymates.erp.domain.Material;
import cn.joymates.erp.domain.Product;
import cn.joymates.erp.utils.db.DbUtils;

public class StockService extends BaseService<Material>{
	
	public StockService(){
		dao = new MaterialDaoImpl();
	}
	
	public List<Map<String, Object>> findAllMaterial(String queryType,String queryStr){
		try {
			Connection conn = null;
			PreparedStatement prst = null;
			ResultSet rs = null;
			String sql = "SELECT w.sign1,sm.mat_supplier_name,sm.ht_mat_no,s.code,s.name,m.weight,m.desc1"
					+ " FROM t_material AS m"
					+ " LEFT JOIN t_supply_mat AS sm"
					+ " ON m.supplymat_id = sm.id"
					+ " LEFT JOIN t_supplier AS s"
					+ " ON sm.supply_id = s.id"
					+ " LEFT JOIN t_warehouse AS w"
					+ " ON m.warehouse_id = w.id where 1 = 1";
			
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
	
	public List<Map<String, Object>> findMateEcside(String ec_rd,String queryType,String queryStr,HttpServletRequest req){
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		resultsql.append("SELECT sm.`ht_mat_no`,m.`id`,w.`sign1`,m.`weight`,sm.`mat_supplier_name`,s.`name`,m.`desc1`,m.`is_logout`,m.`logout_reason`,m.`remark` "
				+ "FROM `t_material` AS m LEFT JOIN `t_supply_mat` AS sm ON m.`supplymat_id` = sm.`id` "
				+ "LEFT JOIN `t_supplier` AS s ON sm.`supply_id` = s.`id` LEFT JOIN `t_warehouse` AS w ON m.`warehouse_id` = w.`id` WHERE 1 = 1 ");
		if(queryType != null && queryStr != null){
			resultsql.append(" AND ").append(queryType).append(" LIKE '%").append(queryStr).append("%'");
		}
		resultsql.append(" ORDER BY m.`id` DESC limit ?, ? ");
		searchsql.append("SELECT COUNT(*) "
				+ "FROM `t_material` AS m LEFT JOIN `t_supply_mat` AS sm ON m.`supplymat_id` = sm.`id` "
				+ "LEFT JOIN `t_supplier` AS s ON sm.`supply_id` = s.`id` LEFT JOIN `t_warehouse` AS w ON m.`warehouse_id` = w.`id` WHERE 1 = 1 " );
		if(queryType != null && queryStr != null){
			searchsql.append(" AND ").append(queryType).append(" LIKE '%").append(queryStr).append("%'");
		}
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(),req);
	}
	
	public List<Map<String, Object>> findProd(String ec_rd,String queryType,String queryStr,HttpServletRequest req){
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		resultsql.append("select * from t_product where 1 = 1 ");
		if(queryType != null && queryStr != null){
			resultsql.append(" AND ").append(queryType).append(" LIKE '%").append(queryStr).append("%'");
		}
		resultsql.append(" ORDER BY id ASC limit ?, ?");
		searchsql.append("select count(*) from t_product where 1 = 1");
		if(queryType != null && queryStr != null){
			searchsql.append(" AND ").append(queryType).append(" LIKE '%").append(queryStr).append("%'");
		}
		ProductDaoImpl pdDao = new ProductDaoImpl();
		return pdDao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(),req);
	}
}
