package cn.joymates.erp.action.baseinfo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Supplier;
import cn.joymates.erp.service.SupplierService;
import cn.joymates.erp.utils.JxlUtil;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class SupplierAction extends BaseAction {

	public String showHome() {
		if (supplier == null) {
			supplier = new Supplier();
		}
		List<Map<String, Object>> supplierList = service.find(supplier,product_key,product_name,ec_rd,req);
		req.setAttribute("supplierList", supplierList);
		req.getSession().setAttribute("sexcel", supplierList);
		req.setAttribute("logoutMap", Supplier.logoutMap);
		return "home";
	}
	public void getExcel(){
		try {
			Map<String, String> map = new LinkedHashMap<String,String>();
			map.put("CODE", "编号");
			map.put("NAME", "名称");
			map.put("DESC1", "描述");
			map.put("CON_PERSON", "联系人");
			map.put("CON_PHONE", "联系电话");
			map.put("FAX", "传真");
			map.put("BANK", "开户银行");
			map.put("ACCOUNT", "银行账号");
			map.put("ADDRESS", "单位地址");
			map.put("EMAIL", "邮箱");
			map.put("IS_LOGOUT", "是否注销");
			map.put("LOGOUT_REASON", "注销原因");
			map.put("REMARK", "备注");
			String str = URLDecoder.decode(req.getParameter("excelName"), "utf-8");
			List<Map<String, Object>> data = (List<Map<String, Object>>) req.getSession().getAttribute("sexcel");
			boolean flag = JxlUtil.getExcel(map,data, req,resp,str);
			resp.getWriter().write(flag + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String find() {
		if (supplier == null) {
			supplier = new Supplier();
		}
		List<Map<String, Object>> supplierList = service.find(supplier,product_key,product_name,ec_rd,req);
		req.getSession().setAttribute("sexcel", supplierList);
		req.setAttribute("supplierList", supplierList);
		req.setAttribute("logoutMap", Supplier.logoutMap);
		return "home";
	}
	
	public String showAddUI() {
		return "addUI";
	}
	
	public String add() {
		service.save(supplier);	//添加供应商信息
		return showHome();
	}
	
	public String showModifyUI() {
		supplier = service.selectOne(supplier);	//获取供应商 
		return "modifyUI";
	}
	
	public String delete(){
		try {
			if(!"".equals(supplier.getLogoutReason()) && supplier.getLogoutReason() != null){
				String lr = new String(supplier.getLogoutReason().getBytes("ISO-8859-1"),"UTF-8");
				supplier.setLogoutReason(lr);
			}
			service.update(supplier);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return showHome();
	}
	
	public String modify() {
		service.update(supplier);
		return showHome();
	}
	
	
	public void checkCodeIsNull(){
		String strSupCode = req.getParameter("supcode");
		try {
			supplier = new Supplier();
			supplier.setCode(strSupCode);
			List<Supplier> sl = service.selectList(supplier);
			String msg;
			if(sl.size() > 0){
				msg = "false";
			}else{
				msg = "true";
			}
			resp.getWriter().write(msg);
			resp.getWriter().flush();
			resp.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	private static final long serialVersionUID = 1L;
	private SupplierService service = ServiceProxyFactory.getInstanceNoMybatis(new SupplierService());
	private Supplier supplier;
	private String product_key;
	private String product_name;
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getProduct_key() {
		return product_key;
	}

	public void setProduct_key(String product_key) {
		this.product_key = product_key;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
}
