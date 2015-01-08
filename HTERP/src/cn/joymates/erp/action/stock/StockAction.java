package cn.joymates.erp.action.stock;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Material;
import cn.joymates.erp.domain.Product;
import cn.joymates.erp.domain.Supplier;
import cn.joymates.erp.domain.SupplyMat;
import cn.joymates.erp.domain.Warehouse;
import cn.joymates.erp.service.MaterialService;
import cn.joymates.erp.service.ProductService;
import cn.joymates.erp.service.StockService;
import cn.joymates.erp.service.SupplierService;
import cn.joymates.erp.service.SupplyMatService;
import cn.joymates.erp.service.WarehousService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class StockAction extends BaseAction {

	private static final long serialVersionUID = 5587430387777417773L;
	private StockService sService = ServiceProxyFactory.getInstanceNoMybatis(new StockService());
	private MaterialService mService = ServiceProxyFactory.getInstanceNoMybatis(new MaterialService());
	private SupplyMatService smService = ServiceProxyFactory.getInstanceNoMybatis(new SupplyMatService());
	private SupplierService spService = ServiceProxyFactory.getInstanceNoMybatis(new SupplierService());
	private WarehousService wpService = ServiceProxyFactory.getInstanceNoMybatis(new WarehousService());
	private ProductService pService = ServiceProxyFactory.getInstanceNoMybatis(new ProductService());
	private Material m;
	private Product p;
	public String showHome(){
		List l = sService.findAllMaterial("","");
		req.setAttribute("mList", l);
		return "home";
	}
	
	public String choice(){
		return "choice";
	}
	
	public String rowList(){
		List<Map<String, Object>> l = sService.findMateEcside(ec_rd, null, null, req);
		req.setAttribute("rowList", l);
		req.setAttribute("STATUS", Material.logoutMap);
		return "rowList";
	}
	public String showDetail(){
		m = mService.selectOne(m);
		SupplyMat sm = new SupplyMat();
		sm.setSupplyMatId(m.getSupplymatId());
		sm = smService.selectOne(sm);
		Supplier s = new Supplier();
		s.setUuid(sm.getSupplyId());
		s = spService.selectOne(s);
		Warehouse w = new Warehouse();
		w.setWarehouseId(m.getWarehouseId());
		w = wpService.selectOne(w);
		req.setAttribute("sm", sm);
		req.setAttribute("s", s);
		req.setAttribute("w", w);
		return "detail";
	}
	
	public void getWh(){
		try {
			WarehousService wService = new WarehousService();
			JSONArray list = JSONArray.fromObject(wService.selectList(new Warehouse()));
			resp.getWriter().write(list.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String rowFind(){
		String queryType = req.getParameter("queryType");
		String queryStr = req.getParameter("queryStr");
		if(queryType != null && !"".equals(queryType)){
			if("all".equals(queryType)){
				rowList();
			}else{
				if(!"".equals(queryStr) && !"".equals(queryStr)){
					List<Map<String, Object>> l = sService.findMateEcside(ec_rd,queryType,queryStr,req);
					req.setAttribute("rowList", l);
					req.setAttribute("STATUS", Material.logoutMap);
					return "rowList";
				}
			}
		}
		return rowList();
	}
	
	public String prodFind(){
		String secStr = req.getParameter("secStr");
		String queryStr = req.getParameter("queryStr");
		if(secStr != null && !"".equals(secStr)){
			if("all".equals(secStr)){
				prodList();
			}else{
				if(queryStr != null && !"".equals(queryStr)){
					List<Map<String, Object>> l = sService.findProd(ec_rd, secStr, queryStr, req);
					req.setAttribute("prodList", l);
					req.setAttribute("LOG", Product.logoutMap);
					req.setAttribute("PROPERTIES", Product.propertiesMap);
					req.setAttribute("MARKET", Product.marketMap);
					req.setAttribute("PATTERN", Product.patternTypeMap);
					return "prodList";
				}
			}
		}
		return prodList();
	}
	
	public String prodDetail(){
		p = pService.selectOne(p);
		return "prodDetail";
	}
	
	public String prodList(){
		List<Map<String, Object>> l = sService.findProd(ec_rd, null, null, req);
		req.setAttribute("prodList", l);
		req.setAttribute("LOG", Product.logoutMap);
		req.setAttribute("PROPERTIES", Product.propertiesMap);
		req.setAttribute("MARKET", Product.marketMap);
		req.setAttribute("PATTERN", Product.patternTypeMap);
		return "prodList";
	}
	
	public Material getM() {
		return m;
	}

	public void setM(Material m) {
		this.m = m;
	}

	public Product getP() {
		return p;
	}

	public void setP(Product p) {
		this.p = p;
	}
	
}
