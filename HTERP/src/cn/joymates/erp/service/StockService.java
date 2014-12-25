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

import net.sf.json.JSONArray;

import com.mysql.jdbc.ResultSetMetaData;

import cn.joymates.erp.domain.Product;
import cn.joymates.erp.utils.db.DbUtils;

public class StockService extends BaseService<Product>{
	
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
}
