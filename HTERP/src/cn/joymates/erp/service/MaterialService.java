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
		searchsql = "SELECT count(*) FROM t_material AS m LEFT JOIN t_supply_mat AS sm ON m.supplymat_id=sm.id LEFT JOIN t_supplier AS s ON sm.supply_id = s.id WHERE 1 = 1 ";
		resultsql = "SELECT m.*,sm.mat_supplier_name,sm.ht_mat_no,s.name AS supplierName FROM t_material AS m LEFT JOIN t_supply_mat AS sm ON m.supplymat_id=sm.id LEFT JOIN t_supplier AS s ON sm.supply_id = s.id WHERE 1 = 1 ";
		if(!"ALL".equals(material_key) && material_key != null && !"".equals(material_key)){
			if("MAT_SUPPLIER_NAME".equals(material_key) || "HT_MAT_NO".equals(material_key)){
				searchsql += " AND sm." + material_key + " LIKE '%" + material_name + "%'";
				resultsql += " AND sm." + material_key + " LIKE '%" + material_name + "%'";
			}else if("NAME".equals(material_key)){
				searchsql += " AND s." + material_key + " LIKE '%" + material_name + "%'";
				resultsql += " AND s." + material_key + " LIKE '%" + material_name + "%'";
			}else{
				searchsql += " AND m." + material_key + " LIKE '%" + material_name + "%'";
				resultsql += " AND m." + material_key + " LIKE '%" + material_name + "%'";
			}
		}
		resultsql += " ORDER BY id DESC limit ?, ? ";
		System.out.println(resultsql);
		return dao.getEcsideList(ec_rd, searchsql, resultsql, req);
	}
	
}
