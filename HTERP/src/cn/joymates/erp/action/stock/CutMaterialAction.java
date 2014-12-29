package cn.joymates.erp.action.stock;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Material;
import cn.joymates.erp.domain.Supplier;
import cn.joymates.erp.domain.Warehouse;
import cn.joymates.erp.service.MaterialService;
import cn.joymates.erp.service.SupplierService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class CutMaterialAction extends BaseAction {

	public String showHome() {
		if(material == null){
			material = new Material();
		}
		List<Material> materialList = materialService.selectList(material, " AND mmat_id IS NOT NULL");
		
		req.setAttribute("materialList", materialList);
		
		for(int i=0;i<materialList.size();i++){
			System.out.println("uuid:"+materialList.get(i).getUuid());
			System.out.println("mmatId:"+materialList.get(i).getMmatId());
		}
		
		return "home";
	}

	public void getMmatDesc(){
		try {
			if(material == null){
				material = new Material();
			}
			Integer materialId = Integer.parseInt(req.getParameter("mmatId"));
			material.setUuid(materialId);
			Material materialInfo = materialService.selectOne(material);
			String materialDesc = materialInfo.getDesc();
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(materialDesc);
			resp.getWriter().flush();
			resp.getWriter().close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 保存分卷信息
	 */
	public void cutmaterial(){
		Integer materialUuid = material.getMmatId();
		
		int intFjRow = Integer.parseInt(req.getParameter("txtFjRow")); 
		
		for(int i=1;i <= intFjRow;i++){
			String strGysjh = req.getParameter("txtGysjh"+i);
			String strClxh = req.getParameter("txtClxh"+i);
			String strCjjs = req.getParameter("txtCjjs"+i);
			BigDecimal bd = new BigDecimal(strCjjs);
			String strCjcc = req.getParameter("txtCjcc"+i);
			
			
			material = new Material();
			material.setUuid(materialUuid);
			Material m = materialService.selectOne(material);
			
			m.setUuid(null);
			m.setScrollId(strGysjh);
			m.setScrollCount(bd);
			m.setStandard(strCjcc);
			materialService.save(m);
		}
		
		
	}
	
	
	
	
	

	private static final long serialVersionUID = 1L;
	private SupplierService supplierService = ServiceProxyFactory.getInstanceNoMybatis(new SupplierService());
	private MaterialService materialService = ServiceProxyFactory.getInstanceNoMybatis(new MaterialService());
	private Supplier supplier;
	private Warehouse warehous;
	private Material material;
	private String strMaterialId;
	private String updatestore;
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Warehouse getWarehous() {
		return warehous;
	}
	public void setWarehous(Warehouse warehous) {
		this.warehous = warehous;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public String getStrMaterialId() {
		return strMaterialId;
	}
	public void setStrMaterialId(String strMaterialId) {
		this.strMaterialId = strMaterialId;
	}
	public String getUpdatestore() {
		return updatestore;
	}
	public void setUpdatestore(String updatestore) {
		this.updatestore = updatestore;
	}
	
}
