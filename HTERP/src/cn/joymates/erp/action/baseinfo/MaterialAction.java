package cn.joymates.erp.action.baseinfo;

import java.util.List;
import java.util.Map;
import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Material;
import cn.joymates.erp.service.MaterialService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class MaterialAction extends BaseAction {

	public String showHome() {
		if (material == null) {
			material = new Material();
		}
		List<Map<String, Object>> materialList = service.find(material, ec_rd, req);
		req.setAttribute("materialList", materialList);
		return "home";
	}
	
	public String find() {
		if (material == null) {
			material = new Material();
		}
		List<Map<String, Object>> materialList = service.find(material, ec_rd, req);
		req.setAttribute("materialList", materialList);
		return "home";
	}
	
	public String showAddUI() {
		return "addUI";
	}
	
	public String add() {
		service.save(material);
		return "home";
	}
	
	public String showModifyUI() {
		material = service.selectOne(material);
		return "modifyUI";
	}
	
	public String delete(){
		service.update(material);
		return "home";
	}
	
	public String modify() {
		service.update(material);
		return "home";
	}
	
	
	
	
	
	
	
	
	
	private static final long serialVersionUID = 1L;
	private MaterialService service = ServiceProxyFactory.getInstanceNoMybatis(new MaterialService());
	private Material material;
	
	public Material getSupplier() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	
}
