package cn.joymates.erp.action.stock;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
			List<Material> materialList = materialService.selectList(material, " AND mmat_id IS NOT NULL"); //查询所有母卷
			List<Material> sonList = materialService.selectList(material, " AND mmat_id IS NULL ");		//查询所有子卷

			req.setAttribute("materialList", materialList);
			req.setAttribute("sonList", sonList);
			
			JSONArray jo = JSONArray.fromObject(sonList);
			
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
			String materialDesc = materialInfo.getRemark();
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
			if(material == null){
				material = new Material();
			}
			Integer materId = Integer.parseInt(req.getParameter("materId"));
			
			material.setUuid(materId);
			material = materialService.selectOne(material);
			
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(material.getMaterialModel().toString());
			resp.getWriter().flush();
			resp.getWriter().close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void cutmaterial(){
		Integer materUuid = material.getUuid();	//材料表ID
		
		int intFjRow = Integer.parseInt(req.getParameter("txtFjRow")); 
		
		//根据材料表id查出该母卷的信息
		material = new Material();
		material.setUuid(materUuid);
		Material minfo = materialService.selectOne(material);
		
		int js = 0;	//获取裁剪卷数之和
		for(int i=1;i <= intFjRow;i++){
			int cjjs = Integer.parseInt(req.getParameter("txtCjjs"+i));
			js += cjjs;
		}
		
		for(int i=1;i <= intFjRow;i++){
			String strGysjh = req.getParameter("txtGysjh"+i);	//材料表id(卷号)
			int cjjs = Integer.parseInt(req.getParameter("txtCjjs"+i));//裁剪卷数
			String strCjcc = req.getParameter("txtCjcc"+i);		//裁剪尺寸及公差
			
			BigDecimal mjz = minfo.getWeight().divide(new BigDecimal(js));		//获取小卷的重量
			
			//根据材料id查询卷号
			Material ma = new Material();
			ma.setUuid(Integer.parseInt(strGysjh));
			Material ml = materialService.selectOne(ma);
			
			minfo.setUuid(null);
			minfo.setWeight(mjz);
			minfo.setStandard(strCjcc);
			minfo.setScrollCount(cjjs);
			minfo.setMmatId(null);
			minfo.setScrollId(ml.getScrollId());
			minfo.setMaterialModel(ml.getMaterialModel());
			minfo.setRemark("分卷而来");
			materialService.save(minfo);
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
