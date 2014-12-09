package cn.joymates.erp.action.stock;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.dao.IWarehouse;
import cn.joymates.erp.dao.impl.WarehouseDaoImpl;
import cn.joymates.erp.domain.Supplier;
import cn.joymates.erp.domain.Warehouse;
import cn.joymates.erp.service.SupplierService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class ChangeStoresAction extends BaseAction {

	public String showHome() {
		
		supplier = new Supplier();
		supplier.setIsLogout("0");
		List<Supplier> supplierList = supplierService.selectList(supplier);
		
		IWarehouse warehouse = new WarehouseDaoImpl();
		
		warehous = new Warehouse();
		warehous.setIsLogout("0");
		List<Warehouse> warehouseList = warehouse.selectList(warehous);
		
		req.setAttribute("supplierList", supplierList);
		req.setAttribute("warehouseList", warehouseList);
		return "home";
	}
	
	public void getSupplierInfoById(){
		try {
			if(supplier == null){
				supplier = new Supplier();
			}
			Integer strSupId = Integer.parseInt(req.getParameter("supid"));
			supplier.setUuid(strSupId);
			Supplier supInfo = supplierService.selectOne(supplier);
			
			JSONObject jo = JSONObject.fromObject(supInfo);
			
			System.out.println(jo.toString());
			
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(jo.toString());
			resp.getWriter().flush();
			resp.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	private static final long serialVersionUID = 1L;
	private SupplierService supplierService = ServiceProxyFactory.getInstanceNoMybatis(new SupplierService());
	
	private Supplier supplier;
	private Warehouse warehous;
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
	
}
