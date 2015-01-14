package cn.joymates.erp.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.ResultSetMetaData;

import cn.joymates.erp.dao.impl.SellDetailDaoImpl;
import cn.joymates.erp.domain.SellDetail;
import cn.joymates.erp.utils.db.DbUtils;

public class SellDetailService extends BaseService<SellDetail> {
	public SellDetailService(){
		dao = new SellDetailDaoImpl();
	}
	
	public List<Map<String, Object>> findPrint(Integer sdId){
		try {
			Connection conn = null;
			PreparedStatement prst = null;
			ResultSet rs = null;
			String sql = "SELECT c.`name`,"
					+ "sb.`code`,"
					+ "cp.`cus_pn`,"
					+ "p.`ht_pn`,"
					+ "sd.`order_count`,"
					+ "sd.`price`,"
					+ "sd.`amount`,"
					+ "pis.`start_date`,"
					+ "pis.`end_date`,"
					+ "sd.`remark` "
					+ "FROM`t_sell_details` AS sd "
					+ "LEFT JOIN `t_cust_pdct` AS cp "
					+ "ON sd.`cust_pdct_id` = cp.`id` "
					+ "LEFT JOIN `t_sell_bill` AS sb "
					+ "ON sb.`id` = sd.`sell_bill_id` "
					+ "LEFT JOIN `t_product` AS p "
					+ "ON cp.`product_id` = p.`id` "
					+ "LEFT JOIN `t_customer` AS c "
					+ "ON sb.`customer_id` = c.`id` "
					+ "LEFT JOIN `t_pdce_istct` AS pis "
					+ "ON pis.`s_detail_id` = sd.`id` "
					+ "WHERE sd.`id` = " + sdId;
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
