package cn.joymates.erp.action.baseinfo;

import java.util.List;
import java.util.Map;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Material;
import cn.joymates.erp.domain.Supplier;
import cn.joymates.erp.service.MaterialService;
import cn.joymates.erp.service.SupplierService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class MaterialAction extends BaseAction {

	public String showHome() {
		if (material == null) {
			material = new Material();
		}
		List<Map<String, Object>> materialList = service.find(material,material_key,material_name,ec_rd,req);
		req.setAttribute("materialList", materialList);
		req.setAttribute("logoutMap", Material.logoutMap);
		return "home";
	}
	
	public String find() {
		if (material == null) {
			material = new Material();
		}
		List<Map<String, Object>> materialList = service.find(material,material_key,material_name,ec_rd,req);
		req.setAttribute("materialList", materialList);
		req.setAttribute("logoutMap", Material.logoutMap);
		return "home";
	}
	
	public String showAddUI() {
		
		String searchsql = "SELECT COUNT(*) FROM t_warehouse where is_logout='0' ";		
		String resultsql = "SELECT * FROM t_warehouse where is_logout='0'  limit ?, ? ";
		List<Map<String, Object>> warehouseList =  service.getEcsideList("100", searchsql, resultsql, req);
		
		supplier = new Supplier();
		supplier.setIsLogout("0");
		List<Supplier> supplierList = supplierService.selectList(supplier);
		
		req.setAttribute("warehouseList", warehouseList);
		req.setAttribute("supplierList", supplierList);
		return "addUI";
	}
	
	public String add() {
		service.save(material);
		return showHome();
	}
	
	public String showModifyUI() {
		material = service.selectOne(material);
		
		String searchsql = "SELECT COUNT(*) FROM t_warehouse where is_logout='0' ";		
		String resultsql = "SELECT * FROM t_warehouse where is_logout='0'  limit ?, ? ";
		List<Map<String, Object>> warehouseList =  service.getEcsideList("100", searchsql, resultsql, req);
		
		supplier = new Supplier();
		supplier.setIsLogout("0");
		List<Supplier> supplierList = supplierService.selectList(supplier);
		
		req.setAttribute("warehouseList", warehouseList);
		req.setAttribute("supplierList", supplierList);
		return "modifyUI";
	}
	
	public String delete(){
		try {
			if(!"".equals(material.getLogoutReason()) && material.getLogoutReason() != null){
				String lr = new String(material.getLogoutReason().getBytes("ISO-8859-1"),"UTF-8");
				material.setLogoutReason(lr);
			}
			service.update(material);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showHome();
	}
	
	public String modify() {
		service.update(material);
		return showHome();
	}
	
	
	
	
	
	
	
	
	
	private static final long serialVersionUID = 1L;
	private MaterialService service = ServiceProxyFactory.getInstanceNoMybatis(new MaterialService());
	private SupplierService supplierService = ServiceProxyFactory.getInstanceNoMybatis(new SupplierService());
	private Material material;
	private Supplier supplier;
	private String material_key;
	private String material_name;
	
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}

	public String getMaterial_key() {
		return material_key;
	}

	public void setMaterial_key(String material_key) {
		this.material_key = material_key;
	}

	public String getMaterial_name() {
		return material_name;
	}

	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}
}
