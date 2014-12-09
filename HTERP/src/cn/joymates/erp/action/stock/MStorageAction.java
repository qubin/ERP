package cn.joymates.erp.action.stock;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;
import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Material;
import cn.joymates.erp.domain.Supplier;
import cn.joymates.erp.domain.SupplyMat;
import cn.joymates.erp.service.MaterialService;
import cn.joymates.erp.service.SupplierService;
import cn.joymates.erp.service.SupplyMatService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class MStorageAction extends BaseAction {

	private static final long serialVersionUID = -5974436377646701350L;
	private MaterialService service = ServiceProxyFactory.getInstanceNoMybatis(new MaterialService());
	private SupplyMatService SMservice = ServiceProxyFactory.getInstanceNoMybatis(new SupplyMatService());	
	private SupplierService supplierService = ServiceProxyFactory.getInstanceNoMybatis(new SupplierService());
	private Material material;
	
	public String showHome(){
		Supplier s = new Supplier();
		s.setIsLogout("0");
		List<Supplier> sList = supplierService.selectList(s);
		req.setAttribute("sList", sList);
		return "home";
	}
	
	public void findMaterial(){
		try {
			String uuid = req.getParameter("uuid");
			if(uuid != null){
				SupplyMat sm = new SupplyMat();
				sm.setSupplyId(Integer.valueOf(uuid));
				List<SupplyMat> smList = SMservice.selectList(sm);
				JSONArray list = JSONArray.fromObject(smList);
				resp.getWriter().write(list.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void findDetail(){
		String id = req.getParameter("id");
		Material m = new Material();
		
	}
	
	public String add(){
		service.save(material);
		return "";
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	
}
