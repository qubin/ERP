package cn.joymates.erp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.erp.dao.impl.MaterialDaoImpl;
import cn.joymates.erp.dao.impl.RowFlowImpl;
import cn.joymates.erp.domain.Material;
import cn.joymates.erp.domain.RowFlow;

public class RowFlowService extends BaseService<RowFlow> {
	
	public RowFlowService(){
		dao = new RowFlowImpl();
	}
	
	public List<Map<String, Object>> findAll(String ec_rd, HttpServletRequest req){
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		resultsql.append("SELECT * FROM t_raw_flow where 1=1 ORDER BY id DESC limit ?, ? ");
		searchsql.append("SELECT COUNT(*) FROM t_raw_flow where 1=1 ");
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(),req);
	}
	
	public List<Map<String, Object>> findQuery(String ec_rd,String queryStr, String serachType,HttpServletRequest req) {
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		resultsql.append("SELECT * FROM t_raw_flow where 1=1 ");
		searchsql.append("SELECT COUNT(*) FROM t_raw_flow where 1=1 ");
		resultsql.append(" AND ").append(serachType).append(" LIKE '%").append(queryStr).append("%'");
		searchsql.append(" AND ").append(serachType).append(" LIKE '%").append(queryStr).append("%'");
		resultsql.append(" ORDER BY id DESC limit ?, ? ");
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(), req);
	}
	public List<Map<String, Object>> findQueryInOut(String ec_rd,String queryStr, String serachType,HttpServletRequest req) {
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		resultsql.append("SELECT * FROM t_raw_flow where 1=1 ");
		searchsql.append("SELECT COUNT(*) FROM t_raw_flow where 1=1 ");
		if("in".equals(serachType)){
			resultsql.append(" AND in_or_out=1");
			searchsql.append(" AND in_or_out=1");
		}else if("out".equals(serachType)){
			resultsql.append(" AND in_or_out=0");
			searchsql.append(" AND in_or_out=0");
		}
		resultsql.append(" ORDER BY id DESC limit ?, ? ");
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(), req);
	}
	
	public Material getWeight(Integer id){
		MaterialDaoImpl materialDao = new MaterialDaoImpl();
		Material m = new Material();
		m.setUuid(id);
		m = materialDao.selectOne(m);
		return m;
	}
}
