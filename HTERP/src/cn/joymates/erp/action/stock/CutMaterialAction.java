package cn.joymates.erp.action.stock;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import net.sf.json.JSONArray;
import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Material;
import cn.joymates.erp.domain.Supplier;
import cn.joymates.erp.domain.SupplyMat;
import cn.joymates.erp.domain.Warehouse;
import cn.joymates.erp.service.MaterialService;
import cn.joymates.erp.service.SupplierService;
import cn.joymates.erp.service.SupplyMatService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class CutMaterialAction extends BaseAction {

	public String showHome() {
		try {
			if (material == null) {
				material = new Material();
			}
			List<Material> materialList = materialService.selectList(material, " AND mmat_id IS NULL");
			List<SupplyMat> supplyMatList = supplyMatService.selectList(new SupplyMat());

			req.setAttribute("materialList", materialList);
			req.setAttribute("supplyMatList", supplyMatList);

			JSONArray jo = JSONArray.fromObject(supplyMatList);
			
			System.out.println("--------"+jo.toString());
			
			session.setAttribute("JsonSupplyMatList", jo.toString());
			
			req.setAttribute("JsonSupplyMatList", jo.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "home";
	}

	/*
	 * 获得母卷描述
	 */
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
	 * 获得材料型号
	 */
	public void getMaterialModel(){
		try {
			if(supplyMat == null){
				supplyMat = new SupplyMat();
			}
			Integer supMatId = Integer.parseInt(req.getParameter("supMatId"));
			
			supplyMat.setSupplyMatId(supMatId);
			SupplyMat supplyMatInfo = supplyMatService.selectOne(supplyMat);
			
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(supplyMatInfo.getMaterialModel());
			resp.getWriter().flush();
			resp.getWriter().close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cutmaterial(){
		Integer materUuid = material.getUuid();	//材料表ID（为母卷）
		
		int intFjRow = Integer.parseInt(req.getParameter("txtFjRow")); 
		
		for(int i=1;i <= intFjRow;i++){
			String strGysjh = req.getParameter("txtGysjh"+i);
			String strClxh = req.getParameter("txtClxh"+i);
			String strCjjs = req.getParameter("txtCjjs"+i);
			BigDecimal cjjs = new BigDecimal(strCjjs);
			String strCjcc = req.getParameter("txtCjcc"+i);
			
			
			material = new Material();
			material.setUuid(materUuid);
			Material m = materialService.selectOne(material);	//查出该母卷的信息
			
			//根据供应商卷号查询供应材料表id
			SupplyMat sm = new SupplyMat();
			sm.setMatSupplierScrollId(strGysjh);
			List<SupplyMat> smlist = supplyMatService.selectList(sm);
			
			m.setUuid(null);
			m.setSupplymatId(smlist.get(0).getSupplyMatId());
			m.setScrollCount(cjjs);
			//m.setScrollId(strGysjh);
			m.setStandard(strCjcc);
			materialService.save(m);
		}
		
		
	}
	
	
	
	
	

	private static final long serialVersionUID = 1L;
	private SupplierService supplierService = ServiceProxyFactory.getInstanceNoMybatis(new SupplierService());
	private MaterialService materialService = ServiceProxyFactory.getInstanceNoMybatis(new MaterialService());
	private SupplyMatService supplyMatService = ServiceProxyFactory.getInstanceNoMybatis(new SupplyMatService());
	private Supplier supplier;
	private Warehouse warehous;
	private Material material;
	private SupplyMat supplyMat;
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

	public SupplyMat getSupplyMat() {
		return supplyMat;
	}

	public void setSupplyMat(SupplyMat supplyMat) {
		this.supplyMat = supplyMat;
	}
	
}
