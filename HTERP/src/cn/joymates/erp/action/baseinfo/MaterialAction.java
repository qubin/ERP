package cn.joymates.erp.action.baseinfo;

import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Material;
import cn.joymates.erp.domain.Supplier;
import cn.joymates.erp.domain.SupplyMat;
import cn.joymates.erp.service.MaterialService;
import cn.joymates.erp.service.SupplierService;
import cn.joymates.erp.service.SupplyMatService;
import cn.joymates.erp.utils.JxlUtil;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class MaterialAction extends BaseAction {

	public String showHome() {
		if (material == null) {
			material = new Material();
		}
		List<Map<String, Object>> materialList = service.find(material,material_key,material_name,ec_rd,req);
		req.setAttribute("materialList", materialList);
		req.setAttribute("logoutMap", Material.logoutMap);
		req.getSession().setAttribute("mexcel", materialList);
		return "home";
	}
	
	public String find() {
		if (material == null) {
			material = new Material();
		}
		List<Map<String, Object>> materialList = service.find(material,material_key,material_name,ec_rd,req);
		req.setAttribute("materialList", materialList);
		req.setAttribute("logoutMap", Material.logoutMap);
		req.getSession().setAttribute("mexcel", materialList);
		return "home";
	}
	public void getExcel(){
		try {
			Map<String, String> map = new LinkedHashMap<String,String>();
			map.put("SUPPLIERNAME", "供应商");
			map.put("WEIGHT", "重量");
			map.put("SCROLL_ID", "卷号");
			map.put("MATERIAL_MODEL", "材料型号");
			map.put("IS_LOGOUT", "是否注销");
			map.put("LOGOUT_REASON", "注销原因");
			map.put("REMARK", "备注");
			String str = URLDecoder.decode(req.getParameter("excelName"), "utf-8");
			List<Map<String, Object>> data = (List<Map<String, Object>>) req.getSession().getAttribute("mexcel");
			boolean flag = JxlUtil.getExcel(map,data, req,resp,str);
			resp.getWriter().write(flag + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String showAddUI() {
		supplier = new Supplier();
		supplier.setIsLogout("0");
		List<Supplier> supplierList = supplierService.selectList(supplier);
		req.setAttribute("supplierList", supplierList);
		return "addUI";
	}
	
	public String add() {
		try {
			int supplyMatId = supplyMatService.save(supplyMat);
			material.setSupplymatId(supplyMatId);
			service.save(material);
			return showHome();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showHome();
	}
	
	public String showModifyUI() {
		try {
			Supplier s = new Supplier();
			s.setIsLogout("0");
			req.setAttribute("supplierList", supplierService.selectList(s));
			
			material = service.selectOne(material);
			
			SupplyMat sm = new SupplyMat();
			sm.setSupplyMatId(material.getSupplymatId());
			supplyMat = supplyMatService.selectOne(sm);
			
			return "modifyUI";
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		try {
			supplyMatService.update(supplyMat);
			service.update(material);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showHome();
	}
	
	
	
	
	
	
	
	
	
	private static final long serialVersionUID = 1L;
	private MaterialService service = ServiceProxyFactory.getInstanceNoMybatis(new MaterialService());
	private SupplierService supplierService = ServiceProxyFactory.getInstanceNoMybatis(new SupplierService());
	private SupplyMatService supplyMatService = ServiceProxyFactory.getInstanceNoMybatis(new SupplyMatService());
	private Material material;
	private Supplier supplier;
	private SupplyMat supplyMat;
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

	public SupplyMat getSupplyMat() {
		return supplyMat;
	}

	public void setSupplyMat(SupplyMat supplyMat) {
		this.supplyMat = supplyMat;
	}
	
}
