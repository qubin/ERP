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
import cn.joymates.erp.domain.Warehouse;
import cn.joymates.erp.service.MaterialService;
import cn.joymates.erp.service.RowFlowService;
import cn.joymates.erp.service.SupplierService;
import cn.joymates.erp.service.SupplyMatService;
import cn.joymates.erp.service.WarehousService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class MStorageAction extends BaseAction {

	private static final long serialVersionUID = -5974436377646701350L;
	private MaterialService service = ServiceProxyFactory.getInstanceNoMybatis(new MaterialService());
	private SupplyMatService SMservice = ServiceProxyFactory.getInstanceNoMybatis(new SupplyMatService());	
	private SupplierService supplierService = ServiceProxyFactory.getInstanceNoMybatis(new SupplierService());
	private RowFlowService rowFlowService = ServiceProxyFactory.getInstanceNoMybatis(new RowFlowService());
	private WarehousService wService = ServiceProxyFactory.getInstanceNoMybatis(new WarehousService());
	private Material material;
	private RowFlow rawFlow;
	
	public String showHome(){
		Supplier s = new Supplier();
		s.setIsLogout("0");
		List<Supplier> sList = supplierService.selectList(s);
		Warehouse wh = new Warehouse();
		wh.setIsLogout("0");
		req.setAttribute("wList", wService.selectList(wh));
		req.setAttribute("sList", sList);
		return "home";
	}
	
	public String materialOut(){
		try {
			BigDecimal inNum = rawFlow.getWeight();
			Material original = service.selectOne(material);
			if(original.getWeight() != null){
				
			}
			original.setWeight(original.getWeight().subtract(inNum));
			rawFlow.setMaterialId(original.getUuid());
			rawFlow.setInOrOut("1");
			rowFlowService.save(rawFlow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return showHome();
	}
	
	public String materialIn(){
		try {
			BigDecimal inNum = rawFlow.getWeight();
			Material sc = material;
			Material original = service.selectOne(material);
			int newId = 0;
			Material wM = new Material();
			wM.setWarehouseId(sc.getWarehouseId());
			wM.setSupplymatId(original.getSupplymatId());
			List<Material> wL = service.selectList(wM);
			if(wL.size() > 0){
				//原入库
				if(sc.getMmatId() != null && !"".equals(sc.getMmatId())){
					original.setMmatId(sc.getMmatId());
				}
				if(sc.getScrollId() != null && !"".equals(sc.getScrollId())){
					original.setScrollId(sc.getScrollId());
				}
				original.setWeight(original.getWeight().add(inNum));
				newId = original.getUuid();
				service.update(original);
			}else{
				//新入库
				if(sc.getMmatId() != null && !"".equals(sc.getMmatId())){
					original.setMmatId(sc.getMmatId());
				}
				if(sc.getScrollId() != null && !"".equals(sc.getScrollId())){
					original.setScrollId(sc.getScrollId());
				}
				service.update(original);
				original.setUuid(null);
				original.setWeight(new BigDecimal(0));
				original.setWeight(inNum);
				original.setWarehouseId(sc.getWarehouseId());
				newId = service.save(original);
			}
			rawFlow.setMaterialId(newId);
			rawFlow.setInOrOut("2");
			rowFlowService.save(rawFlow);
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
   				JSONObject obj = JSONObject.fromObject(ms.get(0));
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
