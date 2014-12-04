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
	
	public List<Map<String, Object>> find(Material material,String material_key,String material_name,String ec_rd,HttpServletRequest req) {
		String searchsql = "",resultsql = "";
		searchsql = "SELECT * FROM(SELECT count(*) FROM t_material AS m LEFT JOIN t_supplier AS s ON m.supply_id=s.id LEFT JOIN t_warehouse AS w ON m.warehouse_id=w.id) AS mtl where 1=1 ";
		resultsql = "SELECT * FROM(SELECT m.*,s.name AS supplierName,w.sign1 AS warehouseSign FROM t_material AS m LEFT JOIN t_supplier AS s ON m.supply_id=s.id LEFT JOIN t_warehouse AS w ON m.warehouse_id=w.id) as mtl where 1=1  ";
		if(!"ALL".equals(material_key) && material_key != null && !"".equals(material_key)){
			searchsql += " AND " + material_key + " LIKE '%" + material_name + "%'";
			resultsql += " AND " + material_key + " LIKE '%" + material_name + "%'";
		}
		resultsql += " ORDER BY id DESC limit ?, ? ";
		return dao.getEcsideList(ec_rd, searchsql, resultsql, req);
	}
	
}
