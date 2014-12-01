package cn.joymates.erp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.erp.dao.impl.MaterialDaoImpl;
import cn.joymates.erp.domain.Material;


public class MaterialService  extends BaseService<Material> {
	
	public MaterialService() {
		dao = new MaterialDaoImpl();
	}
	
	public List<Map<String, Object>> find(Material material, String ec_rd, HttpServletRequest req) {
//		String suppName = material.getName();
//		String suppConPerson = material.getConPerson();
		StringBuffer resultsql = new StringBuffer();
		StringBuffer searchsql = new StringBuffer();
		resultsql.append("SELECT * FROM t_supplier where 1=1 AND (is_logout='0' OR is_logout IS NULL) ");
		searchsql.append("SELECT COUNT(*) FROM t_supplier where 1=1 AND (is_logout='0' OR is_logout IS NULL) ");
//		if(!"".equals(suppName) && suppName != null){
//			resultsql.append(" AND name LIKE '%" + suppName + "%' ");
//			searchsql.append(" AND name LIKE '%" + suppName + "%' ");
//		}
//		if(!"".equals(suppConPerson) && suppConPerson != null){
//			resultsql.append(" AND con_person LIKE '%" + suppConPerson + "%' ");
//			searchsql.append(" AND con_person LIKE '%" + suppConPerson + "%' ");
//		}
		resultsql.append(" ORDER BY id DESC limit ?, ? ");
		return dao.getEcsideList(ec_rd, searchsql.toString(), resultsql.toString(), req);
	}
	
}
