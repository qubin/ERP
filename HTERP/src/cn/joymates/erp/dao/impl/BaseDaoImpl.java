package cn.joymates.erp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.joymates.erp.dao.BaseDao;
import cn.joymates.erp.domain.base.BaseVO;
import cn.joymates.erp.exceptions.DaoException;
import cn.joymates.erp.utils.db.Bean2SqlUtils;
import cn.joymates.erp.utils.db.DbUtils;
import cn.joymates.erp.utils.db.EcsideDataFinder;

public class BaseDaoImpl<T extends BaseVO> implements BaseDao<T> {
	public static Log logger = LogFactory.getLog(DbUtils.class);

	public T selectOne(T ob) {
		T t = null;
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		
		String sql = Bean2SqlUtils.getSelectOneSql(ob);
		logger.debug(sql);
		try {
			conn = DbUtils.getConnection();
			prst = conn.prepareStatement(sql);
			
			rs = prst.executeQuery();
			List<T> tlist = Bean2SqlUtils.reverse(ob, rs);
			t = tlist.isEmpty() ? null : tlist.get(0);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			DbUtils.closeStatement(prst);
		}

		return t;
	}

	public List<T> selectList(T ob) {
		return this.selectList(ob, "");
	}

	public List<T> selectList(T ob, String lastsql) {
		List<T> list = null;
		
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		
		String sql = Bean2SqlUtils.getSelectSql(ob, lastsql);
		logger.debug(sql);
		try {
			conn = DbUtils.getConnection();
			prst = conn.prepareStatement(sql);
			rs = prst.executeQuery(); 
			
			list = Bean2SqlUtils.reverse(ob, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeResultset(rs);
			DbUtils.closeStatement(prst);
		}
		return list;
	}
	
	public void delete(T ob) {
		executeSql(Bean2SqlUtils.getDeleteSql(ob));
	}

	public int save(T ob) {
		return executeSql(Bean2SqlUtils.getInsertSql(ob));
	}

	public void update(T ob) {
		executeSql(Bean2SqlUtils.getUpdateSql(ob));
	}

	public List<Map<String, Object>> getEcsideList(String ec_rd, T ob, HttpServletRequest req) {
		return getEcsideList(ec_rd, ob, "", req);
	}
	
	public List<Map<String, Object>> getEcsideList(String ec_rd, T ob, String lastsql,
			HttpServletRequest req) {
		String sql = Bean2SqlUtils.getSelectSql(ob, lastsql);
		
		//按id排序
		String orderBy = " ORDER BY this_." +
				ob.getId() + 
			    " asc ";
		
		String countSql = sql.replace("*", " count(1) "); // 查询条数
		String resultsql = sql + orderBy + " limit ?, ? ";// 查询语句
		
		logger.debug(countSql);
		logger.debug(resultsql);
		return getEcsideList(ec_rd, countSql, resultsql, req);
	
		
	}
	
	@Deprecated
	public List<Map<String, Object>> getEcsideList(String ec_rd, T ob, String lastsql,
			HttpServletRequest req, String sortField) {
		
		String fieldsql = "this_.*";
		String sql = Bean2SqlUtils.getSelectSql(ob, lastsql);
		
		String orderby = "";
		if (sortField==null || "".equals(sortField)) {	
			orderby = " ORDER BY this_." + ob.getFieldMap().get(ob.getId())
			+ " DESC";
		} else {
			orderby = " ORDER BY this_." + sortField
			+ " DESC";
		}
		
		String countSql = "select count(*) from (select " + fieldsql + " "
				+ sql + ") as count"; // 查询条数
		String resultsql = "select * from(select " + fieldsql
				+ ",rownumber() over(" + orderby + ") as curr " + sql
				+ ")as t where t.curr>? and t.curr<=?"; // 查询语句
		return getEcsideList(ec_rd, countSql, resultsql, req);
		
	}

	public List<Map<String, Object>> getEcsideList(String ec_rd, String countSql, String resultsql,
			HttpServletRequest req) {
		List<Map<String, Object>> result = null;
		try {
			result = EcsideDataFinder.getEcsideList(ec_rd, countSql, resultsql, req);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
		return result;
	}
	
	
	public List getEcsideListForObj(String ec_rd, List list, HttpServletRequest req) {
//		int ecRd = 12;
//		if (ec_rd != null && !ec_rd.equals("0")) {
//			ecRd = Integer.parseInt(ec_rd);
//		}
////		System.out.println(searchsql);
////		System.out.println(resultsql);
//		
//		Limit limit = RequestUtil.getLimit(req, list.size(), ecRd);
//		int offset = 0;
//		int[] rowStartEnd = new int[] { limit.getRowStart() + offset,
//				limit.getRowEnd() + offset };
//		Sort sort = limit.getSort();
//		Map sortValueMap = sort.getSortValueMap();
//		FilterSet filterSet = limit.getFilterSet();
//		Map filterPropertyMap = filterSet.getPropertyValueMap();
//		
		return null;
	}

	
	
	/**
	 * 联合查询
	 * 
	 * @param ec_rd
	 * @param selectItem
	 *            select语句除select本身
	 * @param orderItem
	 *            按照哪个字段排序，字段名
	 * @param fromItem
	 *            from语句 除from本身
	 * @param whereItem
	 *            where语句 除where本身
	 * @param req
	 *            HttpServletRequest
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	public List getComplexEcsideList(String ec_rd, String selectItem,
			String orderItem, String fromItem, String whereItem,
			HttpServletRequest req) {
		String orderby = " ORDER BY " + orderItem + " DESC";
		String searchsql = "select count(*) from (select " + selectItem
				+ " from " + fromItem + " where" + whereItem + " ) as count";

		String resultsql = "select * from(select " + selectItem
				+ ",rownumber() over(" + orderby + ") as curr " + " from "
				+ fromItem + " where " + whereItem
				+ ")as t where t.curr>? and t.curr<=?";
		return getEcsideList(ec_rd, searchsql, resultsql, req);
	}

	/**
	 * ecside分页：不管你的sql语句怎样，将其分成2块，select + from，
	 *    有where时，要么用嵌套子查询 要么请使用另一重载方法
	 * 
	 * @param ec_rd
	 * @param selectItem
	 * @param orderItem
	 *            列是动态时，可以指定数字来排序，1就是第一列
	 * @param fromItem
	 * @param req
	 * @return
	 * 
	 *  2010-12-27侯帆江修改：由于ecside分页问题，去掉了t.curr>=中的'='
	 */
	@SuppressWarnings("unchecked")
	public List getComplexEcsideList(String ec_rd, String selectItem,
			String orderItem, String fromItem, HttpServletRequest req) {
		String orderby = " ORDER BY " + orderItem + " DESC";
		String searchsql = "select count(*) from (select " + selectItem
				+ " from " + fromItem + " ) as count";

		String resultsql = "select * from(select " + selectItem
				+ ",rownumber() over(" + orderby + ") as curr " + " from "
				+ fromItem + ")as t where t.curr>? and t.curr<=?";
		return getEcsideList(ec_rd, searchsql, resultsql, req);
	}

	/**
	 * 执行非查询sql
	 * @param sql
	 * @return 数据库生产的pk值
	 */
	private int executeSql(String sql) {
		logger.debug(sql);
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		
		int pk = 0;
		try {
			conn = DbUtils.getConnection();
			prst = conn.prepareStatement(sql);
			prst.executeUpdate();
			
			rs = prst.getGeneratedKeys();
			if (rs.next()) {
				pk = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeResultset(rs);
			DbUtils.closeStatement(prst);
		}
		return pk;
	}
	
}
