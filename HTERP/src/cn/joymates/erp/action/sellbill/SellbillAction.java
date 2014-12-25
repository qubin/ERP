package cn.joymates.erp.action.sellbill;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Customer;
import cn.joymates.erp.domain.SellBill;
import cn.joymates.erp.domain.SellDetail;
import cn.joymates.erp.service.CustomerService;
import cn.joymates.erp.service.SellBillService;
import cn.joymates.erp.service.SellDetailService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class SellbillAction extends BaseAction {

	private static final long serialVersionUID = -6745099278143466759L;
	private CustomerService cService = ServiceProxyFactory.getInstanceNoMybatis(new CustomerService());
	private SellBillService sService = ServiceProxyFactory.getInstanceNoMybatis(new SellBillService());
	private SellDetailService sdService = ServiceProxyFactory.getInstanceNoMybatis(new SellDetailService());
	private Customer cust;
	private SellBill sb;
	
	public String showHome(){
		req.setAttribute("list", sService.findAll(ec_rd, req,"showHome"));
		req.setAttribute("STATUS", SellBill.STATUS);
		return "home";
	}
	
	public String queryhome(){
		req.setAttribute("list", sService.findAll(ec_rd, req,"foo"));
		req.setAttribute("STATUS", SellBill.STATUS);
		return "queryhome";
	}
	
	public String refuse(){
		sb = sService.selectOne(sb);
		String verifyRemark = req.getParameter("reason");
		if(verifyRemark != null){
			sb.setVerifyRemark(verifyRemark);
			sb.setVerifyStatus("2");
			sService.update(sb);
		}
		return examine();
	}
	
	public String pass(){
		sb = sService.selectOne(sb);
		sb.setVerifyStatus("1");
		sService.update(sb);
		return examine();
	}
	
	public String queryfind(){
		String queryStr = req.getParameter("queryStr");
		String serachType = req.getParameter("serachType");
		if(serachType != null){
			if("all".equals(serachType)){
				return queryhome();
			}else{
				if(queryStr != null){
					List<Map<String, Object>> s = sService.findQuery(ec_rd,queryStr,serachType,req,"foo");
					req.setAttribute("list", s);
					req.setAttribute("STATUS", SellBill.STATUS);
					return "queryhome";
				}
				return queryhome();
			}
		}
		return queryhome();
	}
	
	public String find(){
		String queryStr = req.getParameter("queryStr");
		String serachType = req.getParameter("serachType");
		if(serachType != null){
			if("all".equals(serachType)){
				return showHome();
			}else{
				if(queryStr != null){
					List<Map<String, Object>> s = sService.findQuery(ec_rd,queryStr,serachType,req,"showHome");
					req.setAttribute("list", s);
					return "home";
				}
				return showHome();
			}
		}
		return showHome();
	}
	public String findExam(){
		String queryStr = req.getParameter("queryStr");
		String serachType = req.getParameter("serachType");
		if(serachType != null){
			if("all".equals(serachType)){
				return examine();
			}else{
				if(queryStr != null){
					List<Map<String, Object>> s = sService.findQuery(ec_rd,queryStr,serachType,req,"showHome");
					req.setAttribute("list", s);
					return "examine";
				}
				return examine();
			}
		}
		return examine();
	}
	public String modifyUI(){
		sb = sService.selectOne(sb);
		SellDetail sd = new SellDetail();
		sd.setSbId(sb.getSbId());
		Customer c = new Customer();
		c.setCustId(sb.getCustId());
		req.setAttribute("cList", cService.selectList(new Customer()));
		req.setAttribute("sb", sb);
		JSONArray list = JSONArray.fromObject(sdService.selectList(sd));
		req.setAttribute("sdList", list.toString());
		req.setAttribute("cust", cService.selectOne(c));
		return "modifyUI";
	}
	
	public String examineUI(){
		sb = sService.selectOne(sb);
		SellDetail sd = new SellDetail();
		sd.setSbId(sb.getSbId());
		Customer c = new Customer();
		c.setCustId(sb.getCustId());
		req.setAttribute("sb", sb);
		JSONArray list = JSONArray.fromObject(sdService.selectList(sd));
		req.setAttribute("sdList", list.toString());
		req.setAttribute("cust", cService.selectOne(c));
		String detail = req.getParameter("detail");
		if("true".equals(detail)){
			return "detail";
		}else{
			return "examineUI";
		}
	
	}
	
	public String addUI(){
		req.setAttribute("cList", cService.selectList(new Customer()));
		return "addUI";
	}
	
	public String examine(){
		req.setAttribute("STATUS", SellBill.STATUS);
		req.setAttribute("list", sService.findAll(ec_rd, req,"showHome"));
		return "examine";
	}
	
	public String modify(){
		try {
			int sbId = sb.getSbId();
			sService.update(sb);
			String sdNum = req.getParameter("sdNum");
			int num = Integer.valueOf(sdNum);
			String[] sdCode = req.getParameterValues("sdCode");
			String[] cpn = req.getParameterValues("cpn");
			String[] orderCount = req.getParameterValues("orderCount");
			String[] price = req.getParameterValues("price");
			String[] amount = req.getParameterValues("amount");
			String[] remark = req.getParameterValues("remark");
			String[] sdId = req.getParameterValues("sdId");
			for(int i = 0 ; i < num; i ++){
				SellDetail sd = new SellDetail();
				sd.setSellDetailId(Integer.valueOf(sdId[i]));
				sd.setSbId(sbId);
				sd.setSdCode(sdCode[i]);
				sd.setCpn(cpn[i]);
				sd.setOrderCount(Integer.valueOf(orderCount[i]));
				sd.setPrice(new BigDecimal(price[i]));
				sd.setAmount(new BigDecimal(amount[i]));
				sd.setRemark(remark[i]);
				sdService.update(sd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showHome();
	}
	
	public void findCust(){
		try {
			cust = cService.selectOne(cust);
			JSONObject obj = JSONObject.fromObject(cust);
			resp.getWriter().write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String add(){
		try {
			sb.setVerifyStatus("0");
			int sbId = sService.save(sb);
			String sdNum = req.getParameter("sdNum");
			int num = Integer.valueOf(sdNum);
			String[] sdCode = req.getParameterValues("sdCode");
			String[] cpn = req.getParameterValues("cpn");
			String[] orderCount = req.getParameterValues("orderCount");
			String[] price = req.getParameterValues("price");
			String[] amount = req.getParameterValues("amount");
			String[] remark = req.getParameterValues("remark");
			for(int i = 0 ; i < num; i ++){
				SellDetail sd = new SellDetail();
				sd.setSbId(sbId);
				sd.setSdCode(sdCode[i]);
				sd.setCpn(cpn[i]);
				sd.setOrderCount(Integer.valueOf(orderCount[i]));
				sd.setPrice(new BigDecimal(price[i]));
				sd.setAmount(new BigDecimal(amount[i]));
				sd.setRemark(remark[i]);
				sdService.save(sd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return addUI();
	}

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	public SellBill getSb() {
		return sb;
	}

	public void setSb(SellBill sb) {
		this.sb = sb;
	}
	
	
}
