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
		List<Supplier> supplierList = supplierService.selectList(new Supplier());
		List<Map<String, Object>> materialList = service.find(material, ec_rd, req);
		
		req.setAttribute("materialList", materialList);
		req.setAttribute("supplierList", supplierList);
		return "home";
	}
	
	public String find() {
		if (material == null) {
			material = new Material();
		}
		List<Supplier> supplierList = supplierService.selectList(new Supplier());
		List<Map<String, Object>> materialList = service.find(material, ec_rd, req);
		
		req.setAttribute("supplierList", supplierList);
		req.setAttribute("materialList", materialList);
		return "home";
	}
	
	public String showAddUI() {
		
		String searchsql = "SELECT COUNT(*) FROM t_warehouse ";		
		String resultsql = "SELECT * FROM t_warehouse limit ?, ? ";
		List<Map<String, Object>> warehouseList =  service.getEcsideList("100", searchsql, resultsql, req);
		List<Supplier> supplierList = supplierService.selectList(new Supplier());
		
		req.setAttribute("warehouseList", warehouseList);
		req.setAttribute("supplierList", supplierList);
		return "addUI";
	}
	
	public String add() {
		service.save(material);
		
		List<Supplier> supplierList = supplierService.selectList(new Supplier());
		req.setAttribute("supplierList", supplierList);
		return "home";
	}
	
	public String showModifyUI() {
		material = service.selectOne(material);
		
		String searchsql = "SELECT COUNT(*) FROM t_warehouse ";		
		String resultsql = "SELECT * FROM t_warehouse limit ?, ? ";
		List<Map<String, Object>> warehouseList =  service.getEcsideList("100", searchsql, resultsql, req);
		List<Supplier> supplierList = supplierService.selectList(new Supplier());
		
		req.setAttribute("warehouseList", warehouseList);
		req.setAttribute("supplierList", supplierList);
		return "modifyUI";
	}
	
	public String delete(){
		service.update(material);
		
		List<Supplier> supplierList = supplierService.selectList(new Supplier());
		req.setAttribute("supplierList", supplierList);
		return "home";
	}
	
	public String modify() {
		service.update(material);
		
		List<Supplier> supplierList = supplierService.selectList(new Supplier());
		req.setAttribute("supplierList", supplierList);
		return "home";
	}
	
	
	
	
	
	
	
	
	
	private static final long serialVersionUID = 1L;
	private MaterialService service = ServiceProxyFactory.getInstanceNoMybatis(new MaterialService());
	private SupplierService supplierService = ServiceProxyFactory.getInstanceNoMybatis(new SupplierService());
	private Material material;
	private Supplier supplier;
	
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
	
	
}
