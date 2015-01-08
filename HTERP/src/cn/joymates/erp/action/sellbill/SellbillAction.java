package cn.joymates.erp.action.sellbill;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.CustPdct;
import cn.joymates.erp.domain.Customer;
import cn.joymates.erp.domain.Pdceistct;
import cn.joymates.erp.domain.SellBill;
import cn.joymates.erp.domain.SellDetail;
import cn.joymates.erp.domain.User;
import cn.joymates.erp.service.CustPdctService;
import cn.joymates.erp.service.CustomerService;
import cn.joymates.erp.service.PdceistctService;
import cn.joymates.erp.service.SellBillService;
import cn.joymates.erp.service.SellDetailService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class SellbillAction extends BaseAction {

	private static final long serialVersionUID = -6745099278143466759L;
	private CustomerService cService = ServiceProxyFactory.getInstanceNoMybatis(new CustomerService());
	private SellBillService sService = ServiceProxyFactory.getInstanceNoMybatis(new SellBillService());
	private SellDetailService sdService = ServiceProxyFactory.getInstanceNoMybatis(new SellDetailService());
	private CustPdctService cpService = ServiceProxyFactory.getInstanceNoMybatis(new CustPdctService());
	private Customer cust;
	private SellBill sb;
	
	public String finish(){
		List<Map<String, Object>> l = sService.findFinish(ec_rd, null, null, req);
		req.setAttribute("fList", l);
		req.setAttribute("STATUS", SellBill.STATUS);
		return "finish";
	}

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
		if(sb.getSbId() != null){
			sb.setVerifyStatus("1");
			User u = (User) req.getSession().getAttribute("loggedUser");
			sb.setVerifyPerson(u.getUserLoginId());
			sService.update(sb);
		}
		return examine();
	}
	
	public String queryfind(){
		String queryStr = req.getParameter("queryStr");
		String serachType = req.getParameter("serachType");
		if(serachType != null && !"".equals(serachType)){
			if("all".equals(serachType)){
				return queryhome();
			}else{
				if(queryStr != null && !"".equals(queryStr)){
					List<Map<String, Object>> s = sService.findQuery(ec_rd,queryStr,serachType,req,"foo");
					req.setAttribute("list", s);
					req.setAttribute("STATUS", SellBill.STATUS);
					return "queryhome";
				}
			}
		}
		return queryhome();
	}
	
	public String find(){
		String queryStr = req.getParameter("queryStr");
		String serachType = req.getParameter("serachType");
		if(serachType != null && !"".equals(serachType)){
			if("all".equals(serachType)){
				return showHome();
			}else{
				if(queryStr != null && !"".equals(queryStr)){
					List<Map<String, Object>> s = sService.findQuery(ec_rd,queryStr,serachType,req,"showHome");
					req.setAttribute("list", s);
					return "home";
				}
			}
		}
		return showHome();
	}
	public String findExam(){
		String queryStr = req.getParameter("queryStr");
		String serachType = req.getParameter("serachType");
		if(serachType != null && !"".equals(serachType)){
			if("all".equals(serachType)){
				return examine();
			}else{
				if(queryStr != null && !"".equals(queryStr)){
					List<Map<String, Object>> s = sService.findQuery(ec_rd,queryStr,serachType,req,"showHome");
					req.setAttribute("list", s);
					return "examine";
				}
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
		CustPdct cp = new CustPdct();
		cp.setCustId(sb.getCustId());
		List c1 = cpService.selectList(cp);
		req.setAttribute("list", c1);
		JSONArray jsonList = JSONArray.fromObject(c1);
		System.out.println(jsonList.toString());
		req.setAttribute("jsonList", jsonList.toString());
		return "modifyUI";
	}
	
	public String examineUI(){
		sb = sService.selectOne(sb);
		SellDetail sd = new SellDetail();
		sd.setSbId(sb.getSbId());
		Customer c = new Customer();
		c.setCustId(sb.getCustId());
		req.setAttribute("sb", sb);
		if(sdService.selectList(sd).size() == 0){
			req.setAttribute("sdList", "");
		}else{
			List<SellDetail> sdList = sdService.selectList(sd);
			JSONArray list = JSONArray.fromObject(sdList);
			List<CustPdct> cp2List = new ArrayList<CustPdct>();
			for(int i = 0; i < sdList.size(); i ++){
				CustPdct cp2 = new CustPdct();
				cp2.setCpId(sdList.get(i).getCpId());
				cp2List.add(cpService.selectOne(cp2));
			}
			JSONArray jsonList = JSONArray.fromObject(cp2List);
			req.setAttribute("cp2List", jsonList);
			req.setAttribute("sdList", list.toString());
		}
		req.setAttribute("cust", cService.selectOne(c));
		
		String detail = req.getParameter("detail");
		if("true".equals(detail)){
			return "detail";
		}else if("finish".equals(detail)){
			return "finishUI";
		}else{
			return "examineUI";
		}
	
	}
	
	public String addUI(){
		req.setAttribute("cList", cService.selectList(new Customer()));
		return "addUI";
	}
	
	public void findCP(){
		try {
			CustPdct cp = new CustPdct();
			cp.setCustId(cust.getCustId());
			JSONArray list = JSONArray.fromObject(cpService.selectList(cp));
			resp.getWriter().write(list.toString());
		}catch (IOException e) {
			e.printStackTrace();
		}
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
			String[] orderCount = req.getParameterValues("orderCount");
			String[] price = req.getParameterValues("price");
			String[] amount = req.getParameterValues("amount");
			String[] remark = req.getParameterValues("remark");
			String[] sdId = req.getParameterValues("sdId");
			String[] cpList = req.getParameterValues("cpList");
			for(int i = 0 ; i < num; i ++){
				SellDetail sd = new SellDetail();
				sd.setSellDetailId(Integer.valueOf(sdId[i]));
				sd.setSbId(sbId);
				sd.setCpId(Integer.valueOf(cpList[i]));
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
	
	public void checkFinish(){
		try {
			sb = sService.selectOne(sb);
			if(sb.getSbId() != null){
				SellDetail sd = new SellDetail();
				sd.setSbId(sb.getSbId());
				List<SellDetail> sdList = sdService.selectList(sd);
				PdceistctService piService = new PdceistctService();
				List<Pdceistct> piList = new ArrayList<Pdceistct>();
				for(int i = 0 ; i < sdList.size(); i ++){
					Pdceistct pi = new Pdceistct();
					pi.setsDetailId(sdList.get(i).getSellDetailId());
					pi = piService.selectList(pi).get(0);
					piList.add(pi);
				}
				String flag = "true";
				for(Pdceistct pi : piList){
					if(pi.getIsOver().equals("2")){
						flag = "false";
					}
				}
				resp.getWriter().write(flag);
			}else{
				resp.getWriter().write("");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String finishSellBill(){
		sb = sService.selectOne(sb);
		if(sb.getSbId() != null){
			sb.setIsOrderOver("1");
			User u = (User) req.getSession().getAttribute("loggedUser");
			sb.setOverPerson(u.getUserLoginId());
			sService.update(sb);
			return finish();
		}
		return finish();
	}
	
	public String finishFind(){
		String queryStr = req.getParameter("queryStr");
		String serachType = req.getParameter("serachType");
		if(serachType != null && !"".equals(serachType)){
			if("all".equals(serachType)){
				return examine();
			}else{
				if(queryStr != null && !"".equals(queryStr)){
					List<Map<String, Object>> s = sService.findFinish(ec_rd,queryStr,serachType,req);
					req.setAttribute("list", s);
					req.setAttribute("STATUS", SellBill.STATUS);
					return "finish";
				}
			}
		}
		return finish();
	}
	
	public String add(){
		try {
			sb.setVerifyStatus("0");
			int sbId = sService.save(sb);
			String sdNum = req.getParameter("sdNum");
			int num = Integer.valueOf(sdNum);
			String[] orderCount = req.getParameterValues("orderCount");
			String[] price = req.getParameterValues("price");
			String[] amount = req.getParameterValues("amount");
			String[] remark = req.getParameterValues("remark");
			String[] cpList = req.getParameterValues("cpList");
			for(int i = 0 ; i < num; i ++){
				SellDetail sd = new SellDetail();
				sd.setSbId(sbId);
				sd.setCpId(Integer.valueOf(cpList[i]));
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
	
	public void checkModify(){
		try {
			sb = sService.selectOne(sb);
			if("0".equals(sb.getVerifyStatus())){
				resp.getWriter().write("true");
			}else{
				resp.getWriter().write("false");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void checkExam(){
		try {
			sb = sService.selectOne(sb);
			if("1".equals(sb.getVerifyStatus())){
				resp.getWriter().write("false");
			}else{
				resp.getWriter().write("true");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void checkFinishBill(){
		try {
			sb = sService.selectOne(sb);
			if(!"1".equals(sb.getVerifyStatus())){
				resp.getWriter().write("false");
			}else{
				resp.getWriter().write("true");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
