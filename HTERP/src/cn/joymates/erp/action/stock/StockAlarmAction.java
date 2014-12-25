package cn.joymates.erp.action.stock;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Material;
import cn.joymates.erp.domain.Product;
import cn.joymates.erp.domain.Supplier;
import cn.joymates.erp.domain.Warehouse;
import cn.joymates.erp.service.MaterialService;
import cn.joymates.erp.service.ProductService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class StockAlarmAction extends BaseAction {

	public String showHome() {
		return "home";
	}
	
	/*
	 * 根据原料或者产品编号获取华天编号
	 */
	public void getHtContentByAlarmType(){
		try {
			String t = req.getParameter("type");
			List<Map<String,Object>> htContent = null;
			String searchsql,resultsql;
			if(t.equals("1")){
				searchsql = "SELECT COUNT(*) FROM t_material AS m LEFT JOIN t_supply_mat AS sm ON m.supplymat_id=sm.id WHERE m.is_logout='0' ";
				resultsql = "SELECT m.id,sm.ht_mat_no AS htNo FROM t_material AS m LEFT JOIN t_supply_mat AS sm ON m.supplymat_id=sm.id WHERE m.is_logout='0' limit ?, ?";
			    htContent = materialService.getEcsideList("1000", searchsql, resultsql, req);
			}else{
				searchsql = "SELECT COUNT(*) FROM t_product WHERE is_logout='0'" ;
				resultsql = "SELECT id,ht_pn AS htNo FROM t_product WHERE is_logout='0' limit ?, ?";
			    htContent = materialService.getEcsideList("1000", searchsql, resultsql, req);
			}
			JSONArray jo = JSONArray.fromObject(htContent);
			
			System.out.println(jo);
			
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(jo.toString());
			resp.getWriter().flush();
			resp.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 保存警示重量
	 */
	public String saveAlarm(){
		String strAlarmType = req.getParameter("txtAlarmType");
		String hc = req.getParameter("txtHtContent");
		Integer strHtContent = Integer.parseInt(hc);
		String strAlarmWeight = req.getParameter("txtAlarmWeight");
		BigDecimal bd = new BigDecimal(strAlarmWeight);
		
		if(strAlarmType.equals("1")){	//材料
			if(material == null){
				material = new Material();
			}
			material.setUuid(strHtContent);
			material.setAlarmWeight(bd);
			materialService.update(material);
		}else{							//产品
			if(product == null){
				product = new Product();
			}
			product.setUuid(strHtContent);
			product.setAlarmWeight(bd);
			productService.update(product);
		}
		return "home";
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	private static final long serialVersionUID = 1L;
	private MaterialService materialService = ServiceProxyFactory.getInstanceNoMybatis(new MaterialService());
	private ProductService productService = ServiceProxyFactory.getInstanceNoMybatis(new ProductService());
	private Product product;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
