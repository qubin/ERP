package cn.joymates.erp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import cn.joymates.erp.dao.impl.WarehouseDaoImpl;
import cn.joymates.erp.domain.Customer;
import cn.joymates.erp.domain.Warehouse;
import cn.joymates.erp.utils.MD5Utils;
import cn.joymates.erp.utils.db.SessionFactoryUtil;

public class WarehousService extends BaseService<Warehouse>{
	
	private WarehouseDaoImpl dao = new WarehouseDaoImpl();
	
	public int save(Warehouse ob){
		return dao.save(ob);
	}
	
	public void update(Warehouse ob){
		dao.update(ob);
	}
	public List<Warehouse> selectList(Warehouse w){
		return dao.selectList(w);
	}
	public List<Map<String, Object>> findAll(Warehouse wh,String ec_rd, HttpServletRequest req) {
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		resultsql.append("SELECT * FROM t_warehouse where 1=1 ORDER BY id DESC limit ?, ? ");
		searchsql.append("SELECT COUNT(*) FROM t_warehouse where 1=1 ");
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(),req);
	}
	
	public Warehouse selectOne(Warehouse wh){
		return dao.selectOne(wh);
	}

	public List<Map<String, Object>> findQuery(String ec_rd, String queryStr,
			String serachType, HttpServletRequest req) {
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		resultsql.append("SELECT * FROM t_warehouse where 1=1 ");
		searchsql.append("SELECT COUNT(*) FROM t_warehouse where 1=1 ");
		resultsql.append(" AND ").append(serachType).append(" LIKE '%").append(queryStr).append("%'");
		searchsql.append(" AND ").append(serachType).append(" LIKE '%").append(queryStr).append("%'");
		resultsql.append(" ORDER BY id DESC limit ?, ? ");
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(), req);
	}
	
	public int checkDeleteWarehouse(Warehouse wh){
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectOne("right.checkDeleteWarehouse", wh.getWarehouseId());
	}
}
