package cn.joymates.erp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.erp.dao.BaseDao;
import cn.joymates.erp.domain.base.BaseVO;

public class BaseService<T extends BaseVO> {
	protected BaseDao<T> dao;

	public void delete(T ob) {
		dao.delete(ob);
	}

	public T selectOne(T ob) {
		return dao.selectOne(ob);
	}

	public List<T> selectList(T ob) {
		return dao.selectList(ob);
	}

	public List<T> selectList(T ob, String lastsql) {
		return dao.selectList(ob, lastsql);
	}

	public int save(T ob) {
		return dao.save(ob);
	}

	public void update(T ob) {
		dao.update(ob);
	}

	public List<Map<String, Object>> getEcsideList(String ec_rd, T ob, HttpServletRequest req) {
		return dao.getEcsideList(ec_rd, ob, req);
	}

	public List<Map<String, Object>> getEcsideList(String ec_rd, T ob, String lastsql,
			HttpServletRequest req) {
		return dao.getEcsideList(ec_rd, ob, lastsql, req);
	}

	public List<Map<String, Object>> getEcsideList(String ec_rd, String searchsql, String resultsql,
			HttpServletRequest req) {
		return dao.getEcsideList(ec_rd, searchsql, resultsql, req);
	}

	@Deprecated
	public List getComplexEcsideList(String ec_rd, String selectItem,
			String orderItem, String fromItem, HttpServletRequest req) {
		return dao.getComplexEcsideList(ec_rd, selectItem, orderItem,
				fromItem, req);
	}
	
	@Deprecated
	public List getComplexEcsideList(String ec_rd, String selectItem,
			String orderItem, String fromItem, String whereItem,
			HttpServletRequest req) {

		return dao.getComplexEcsideList(ec_rd, selectItem, orderItem,
				fromItem, whereItem, req);
	}

}
