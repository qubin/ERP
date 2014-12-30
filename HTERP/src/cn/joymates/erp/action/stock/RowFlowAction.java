package cn.joymates.erp.action.stock;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.RowFlow;
import cn.joymates.erp.service.RowFlowService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class RowFlowAction extends BaseAction {

	private static final long serialVersionUID = -2057504376627790126L;
	private RowFlowService service = ServiceProxyFactory.getInstanceNoMybatis(new RowFlowService());
	private RowFlow rowFlow;
	
	public String showHome(){
		List<Map<String, Object>> rfList = service.findQuery(ec_rd,null,null,req);
		req.setAttribute("rfList", rfList);
		req.setAttribute("logoutMap", RowFlow.logoutMap);
		req.setAttribute("inOrOut", RowFlow.inOuOut);
		return "home";
	}

	public String find(){
		String queryStr = req.getParameter("queryStr");
		String serachType = req.getParameter("serachType");
		if(queryStr != null && !"".equals(queryStr)){
			if("all".equals(serachType)){
				return showHome();
			}else{
				List<Map<String, Object>> rowFlowList = service.findQuery(ec_rd,queryStr,serachType,req);
				req.setAttribute("rfList", rowFlowList);
				req.setAttribute("logoutMap", RowFlow.logoutMap);
				req.setAttribute("inOrOut", RowFlow.inOuOut);
				return "home";
			}
		}
		return showHome();
	}
	
	public String modify(){
		try {
			service.update(rowFlow);
			return showHome();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "home";
	}
	
	public void checkPFOut(){
		
	}
	
	public String showModifyUI(){
		try {
			rowFlow = service.selectOne(rowFlow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modifyUI";
	}
	
	public String delete(){
		try {
			if("0".equals(rowFlow.getIsLogout())){
				rowFlow.setLogoutReason(" ");
				service.update(rowFlow);
				return showHome();
			}else{
				String str = URLDecoder.decode(rowFlow.getLogoutReason(), "utf-8");
				rowFlow.setLogoutReason(str);
				service.update(rowFlow);
				return showHome();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "home";
	}
	
	public RowFlow getRowFlow() {
		return rowFlow;
	}

	public void setRowFlow(RowFlow rowFlow) {
		this.rowFlow = rowFlow;
	}
	
	
}
