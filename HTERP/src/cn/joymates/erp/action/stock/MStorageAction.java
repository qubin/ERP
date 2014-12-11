package cn.joymates.erp.action.stock;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Material;
import cn.joymates.erp.domain.RowFlow;
import cn.joymates.erp.domain.Supplier;
import cn.joymates.erp.domain.SupplyMat;
import cn.joymates.erp.service.MaterialService;
import cn.joymates.erp.service.RowFlowService;
import cn.joymates.erp.service.SupplierService;
import cn.joymates.erp.service.SupplyMatService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class MStorageAction extends BaseAction {

	private static final long serialVersionUID = -5974436377646701350L;
	private MaterialService service = ServiceProxyFactory.getInstanceNoMybatis(new MaterialService());
	private SupplyMatService SMservice = ServiceProxyFactory.getInstanceNoMybatis(new SupplyMatService());	
	private SupplierService supplierService = ServiceProxyFactory.getInstanceNoMybatis(new SupplierService());
	private RowFlowService rowFlowService = ServiceProxyFactory.getInstanceNoMybatis(new RowFlowService());
	private Material material;
	private RowFlow rawFlow;
	
	public String showHome(){
		Supplier s = new Supplier();
		s.setIsLogout("0");
		List<Supplier> sList = supplierService.selectList(s);
		req.setAttribute("sList", sList);
		return "home";
	}
	
	public String materialInOrOut(){
		try {
			rawFlow.setMaterialId(material.getUuid());
			rowFlowService.save(rawFlow);
			String inOrOut = rawFlow.getInOrOut();
			BigDecimal inNum = rawFlow.getWeight();
			if(inOrOut != null){
				if("0".equals(inOrOut)){
					Material original = service.selectOne(material);
					original.setWeight(original.getWeight().subtract(inNum));
					service.update(original);
				}else{
					Material original = service.selectOne(material);
					original.setWeight(original.getWeight().add(inNum));
					service.update(original);
				}
			}
			req.setAttribute("msg", "success");
			return showHome();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showHome();
	}
	
	public void checkOutNum(){
		try {
			Material m = new Material();
			m.setUuid(material.getUuid());
			BigDecimal original = service.selectOne(m).getWeight();
			if(original.compareTo(material.getWeight()) == -1){
				resp.getWriter().write("false");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		try {
			String id = req.getParameter("id");
			Material m = new Material();
			m.setSupplymatId(Integer.valueOf(id));
			List<Material> ms = service.selectList(m);
			if(ms.size() >= 0){
				
				JSONArray obj = JSONArray.fromObject(ms);
				resp.getWriter().write(obj.toString());
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
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

	public RowFlow getRawFlow() {
		return rawFlow;
	}

	public void setRawFlow(RowFlow rawFlow) {
		this.rawFlow = rawFlow;
	}
	
	
}
