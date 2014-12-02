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
		//int warehouseId = material.getWarehouseId();
		int supplyId;
		String standard;
		try {
			supplyId = material.getSupplyId();
			standard = material.getStandard();
		} catch (Exception e) {
			supplyId = -1;
			standard = "";
		}
		
		String searchsql = "",resultsql = "";
		searchsql = "SELECT count(*) FROM t_material AS m LEFT JOIN t_supplier AS s ON m.supply_id=s.id LEFT JOIN t_warehouse AS w ON m.warehouse_id=w.id where 1=1 AND (m.is_logout='0' OR m.is_logout IS NULL) ";
		resultsql = "SELECT m.*,s.name AS supplierName,w.sign1 AS warehouseSign FROM t_material AS m LEFT JOIN t_supplier AS s ON m.supply_id=s.id LEFT JOIN t_warehouse AS w ON m.warehouse_id=w.id where 1=1 AND (m.is_logout='0' OR m.is_logout IS NULL) ";
//		if(warehouseId != -1){
//			resultsql = " AND m.warehouse_id=" + warehouseId + " ";
//			searchsql = " AND m.warehouse_id=" + warehouseId + " ";
//		}
		if(supplyId != -1){
			resultsql += " AND m.supply_id=" + supplyId + " ";
			searchsql += " AND m.supply_id=" + supplyId + " ";
		}
		if(!"".equals(standard) && standard != null){
			resultsql += " AND m.standard LIKE '%" + standard + "%' ";
			searchsql += " AND m.standard LIKE '%" + standard + "%' ";
		}
		resultsql += " ORDER BY m.id DESC limit ?, ? ";
		return dao.getEcsideList(ec_rd, searchsql, resultsql, req);
	}
	
}
