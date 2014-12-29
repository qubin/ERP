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

import cn.joymates.erp.dao.BaseDao;
import cn.joymates.erp.dao.impl.CustomerDaoImpl;
import cn.joymates.erp.dao.impl.SupplierDaoImpl;
import cn.joymates.erp.domain.Customer;
import cn.joymates.erp.utils.db.DbUtils;

public class CustomerService extends BaseService<Customer> {
	public CustomerService() {
		dao = new CustomerDaoImpl();
	}
	
	public List<Map<String, Object>> findAll(Customer cust,String ec_rd, HttpServletRequest req){
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		resultsql.append("SELECT * FROM t_customer where 1=1 ORDER BY id DESC limit ?, ? ");
		searchsql.append("SELECT COUNT(*) FROM t_customer where 1=1 ");
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(),req);
	}

	public List<Map<String, Object>> findPage(Customer cust, String ec_rd,HttpServletRequest req) {
		
		return null;
	}

	public List<Map<String, Object>> findQuery(String ec_rd,String queryStr, String serachType,HttpServletRequest req) {
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		resultsql.append("SELECT * FROM t_customer where 1=1 ");
		searchsql.append("SELECT COUNT(*) FROM t_customer where 1=1 ");
		resultsql.append(" AND ").append(serachType).append(" LIKE '%").append(queryStr).append("%'");
		searchsql.append(" AND ").append(serachType).append(" LIKE '%").append(queryStr).append("%'");
		resultsql.append(" ORDER BY id DESC limit ?, ? ");
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(), req);
	}
	
	public List<Map<String, Object>> findModify(Integer custId){
		try {
			Connection conn = null;
			PreparedStatement prst = null;
			ResultSet rs = null;
			String sql = "SELECT p.`ht_pn`,cp.`id`,cp.`cus_pn`,w.sign1 "
					+ "FROM `t_product` AS p LEFT JOIN `t_cust_pdct` AS cp "
					+ "ON cp.`product_id` = p.`id`LEFT JOIN `t_warehouse` AS w ON cp.`area` = w.`id` "
					+ "WHERE cp.`customer_id` = " + custId;
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
