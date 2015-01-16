package cn.joymates.erp.action.stock;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.CustPdct;
import cn.joymates.erp.domain.CustPno;
import cn.joymates.erp.domain.Customer;
import cn.joymates.erp.domain.PdctFlow;
import cn.joymates.erp.domain.Product;
import cn.joymates.erp.domain.User;
import cn.joymates.erp.domain.Warehouse;
import cn.joymates.erp.service.CustPdctService;
import cn.joymates.erp.service.CustPnoService;
import cn.joymates.erp.service.CustomerService;
import cn.joymates.erp.service.PdctFlowService;
import cn.joymates.erp.service.ProductService;
import cn.joymates.erp.service.RowFlowService;
import cn.joymates.erp.service.WarehousService;
import cn.joymates.erp.utils.ServiceProxyFactory;
import cn.joymates.erp.utils.db.SessionFactoryUtil;

public class PdctFlowAction extends BaseAction {

	private static final long serialVersionUID = 6287252646839690486L;
	private PdctFlowService pdctService = ServiceProxyFactory.getInstanceNoMybatis(new PdctFlowService());
	private WarehousService wService = ServiceProxyFactory.getInstanceNoMybatis(new WarehousService());
	private CustomerService cService = ServiceProxyFactory.getInstanceNoMybatis(new CustomerService());
	private ProductService pService = ServiceProxyFactory.getInstanceNoMybatis(new ProductService());
	private CustPdctService cpService = ServiceProxyFactory.getInstanceNoMybatis(new CustPdctService());
	private CustPnoService cpnService = ServiceProxyFactory.getInstanceNoMybatis(new CustPnoService());
	private Product product;
	private CustPdct cp;
	private CustPno cpn;
	
	public String showHome(){
		Warehouse w = new Warehouse();
		w.setIsLogout("0");
		List<Warehouse> wList = wService.selectList(w);
		req.setAttribute("wList", wList);
		
		Customer c = new Customer();
		c.setIsLogout("0");
		req.setAttribute("cList", cService.selectList(c));
		return "home";
	}
	
	public String flowHome(){
		List<Map<String, Object>> l = pdctService.findFlow(ec_rd, null, null, req);
		req.setAttribute("pfList", l);
		req.setAttribute("inOrOut", PdctFlow.inOuOut);
		Warehouse w = new Warehouse();
		w.setIsLogout("0");
		List<Warehouse> wList = wService.selectList(w);
		req.setAttribute("wList", wList);
		return "flow";
	}
	
	public String flowFind(){
		try {
			String serachType = req.getParameter("serachType");
			String queryStr = req.getParameter("queryStr");
			if(serachType != null && !"".equals(serachType)){
				if("all".equals(serachType)){
					flowHome();
				}else if("boxNo".equals(serachType)){
					//盒号搜索
					String sign = req.getParameter("sign1");
					String batchCode = req.getParameter("batchCode");
					String no = queryStr;
					if(sign != null && !"".equals(sign) && batchCode != null && !"".equals(batchCode) && no != null && !"".equals(no)){
						//当前盒号范围
						List<Map<String, Object>> data = pdctService.findBoxNum(sign + batchCode);
						Map boundary = data.get(0);	
						Integer No = Integer.valueOf(no);
						Long prefix = (Long) boundary.get("prefix");
						Long suffix = (Long) boundary.get("suffix");
						if(No > prefix && No < suffix){
							req.setAttribute("flag", "未出库");
							//在范围，未出库
						}else{
							//不在范围，已经出库
							req.setAttribute("flag", "已出库");
						}
						req.setAttribute("sign", sign);
						req.setAttribute("batchCode", batchCode);
						req.setAttribute("no", no);
						req.setAttribute("boxNo", sign + batchCode + no);
						req.setAttribute("data", data);
						return "boxDetail";
					}
				}else{
					if(queryStr != null && !"".equals(queryStr)){
						List<Map<String, Object>> l = pdctService.findFlow(ec_rd, serachType, queryStr, req);
						req.setAttribute("pfList", l);
						req.setAttribute("inOrOut", PdctFlow.inOuOut);
						
						Warehouse w = new Warehouse();
						w.setIsLogout("0");
						List<Warehouse> wList = wService.selectList(w);
						req.setAttribute("wList", wList);
						return "flow";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flowHome();
	}
	
	public String inWarehouse(){
		try {
			String choice = req.getParameter("choice");
			String num = req.getParameter("inOutNum");
			String whflag = req.getParameter("whflag");
			String nowTime = req.getParameter("nowTime");
			String remark = req.getParameter("remark");
			String boxNum = req.getParameter("boxNum");
			String nowflag = req.getParameter("nowflag");
			String picNum = req.getParameter("picNum");
			if(num != null && nowTime != null && choice != null && boxNum != null && nowTime != null){
				User u = (User) req.getSession().getAttribute("loggedUser");
				BigDecimal inOutNum = new BigDecimal(num);
					//入库
				if("in".equals(choice)){
						//新入库
					int pdId = 0;
					if(whflag != null && !whflag.equals("-1")){
						//客户产品
						String flowBoxNo = "";
						int flowBoxNum = 0;
						if("same".equals(nowflag)){
							cp = cpService.selectOne(cp);
							CustPdct newCp = new CustPdct();
							newCp.setCustId(cp.getCustId());
							newCp.setProdId(cp.getProdId());
							newCp.setCus_pn(cp.getCus_pn());
							newCp.setPicCount(Integer.valueOf(num));
							newCp.setWeight(cp.getWeight());
							newCp.setPicNum(cp.getPicNum());
							newCp.setArea(Integer.valueOf(whflag));
							pdId = cpService.save(newCp);
						}else{
							cp = cpService.selectOne(cp);
							cp.setPicCount(Integer.valueOf(num));
							cp.setArea(Integer.valueOf(whflag));
							cp.setPicNum(Integer.valueOf(picNum));
							pdId = cp.getCpId();
							cpService.update(cp);
						}
						//批号
						cpn.setcPdctId(pdId);
						cpn.setArea(Integer.valueOf(whflag));
						cpn.setBoxNo(Integer.valueOf(boxNum));
						cpn.setBoxNum(Integer.valueOf(boxNum));
						cpnService.save(cpn);
					}else{
						//原入库	
						cp = cpService.selectOne(cp);
						//客户产品
						cp.setPicCount(cp.getPicCount() + Integer.valueOf(num));
						cpService.update(cp);
						pdId = cp.getCpId();
						//批号
						CustPno oldCpn = new CustPno();
						oldCpn.setcPdctId(cp.getCpId());
						cpn = cpnService.selectList(oldCpn).get(0);
						cpn.setArea(cp.getArea());
						cpn.setBoxNo(cpn.getBoxNo() + Integer.valueOf(boxNum));
						cpn.setBoxNum(cpn.getBoxNum() + Integer.valueOf(boxNum));
						cpnService.update(cpn);
					}
					//成品重量修改
					Product p = new Product();
					p.setUuid(cp.getProdId());
					p = pService.selectOne(p);
					p.setTotalWeight(p.getTotalWeight().add(cp.getWeight().multiply(inOutNum)));
					pService.update(p);
					//出入库记录
					PdctFlow pf = new PdctFlow();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					pf.setOutTime(sdf.parse(nowTime));
					pf.setCount(Integer.valueOf(num));
					pf.setInOrOut("2");
					pf.setBatchCode(cpn.getBatchCode());
					pf.setBoxNo(cpn.getBoxNo());
					pf.setBoxNum(cpn.getBoxNum());
					pf.setOutPerson(u.getUserLoginId());
					pf.setCustPdctId(pdId);
					pf.setRemark(remark);
					pdctService.save(pf);
				}else{
					//出库
					//客户产品
					cp = cpService.selectOne(cp);
					cp.setPicCount(cp.getPicCount() - Integer.valueOf(num));
					
					//成品重量
					Product p = new Product();
					p.setUuid(cp.getProdId());
					p = pService.selectOne(p);
					BigDecimal tempNum = cp.getWeight().multiply(inOutNum);
					if(p.getTotalWeight().compareTo(tempNum) != 1){
						//成品总重量出库数量大于库存
						return showHome();
					}
					p.setTotalWeight(p.getTotalWeight().subtract(tempNum));
				
					//批号
					CustPno newCpn = new CustPno();
					newCpn.setcPdctId(cp.getCpId());
					cpn = cpnService.selectList(newCpn).get(0);
					Integer tempNum2 = cpn.getBoxNo() - Integer.valueOf(boxNum);
					if(tempNum2 < 0){
						//出库盒数大于库存盒数
						return showHome();
					}
					cpn.setBoxNo(tempNum2);
					
					cpService.update(cp);
					cpnService.update(cpn);
					pService.update(p);
					
					//出入流水
					PdctFlow pf = new PdctFlow();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					pf.setOutTime(sdf.parse(nowTime));
					pf.setCount(Integer.valueOf(num));
					pf.setInOrOut("1");
					pf.setOutPerson(u.getUserLoginId());
					pf.setBoxNo(cpn.getBoxNo());
					pf.setBoxNum(cpn.getBoxNum());
					pf.setCustPdctId(cp.getCpId());
					pf.setRemark(remark);
					pdctService.save(pf);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showHome();
	}
	
	public void findProd(){
		try {
			String cpId = req.getParameter("cpId");
			CustPdct cp = new CustPdct();
			if(cpId != null && !"null".equals(cpId)){
				cp.setCpId(Integer.valueOf(cpId));
				cp = cpService.selectOne(cp);
				Product p = new Product();
				p.setUuid(cp.getProdId());
				p = pService.selectOne(p);
				CustPno cpn = new CustPno();
				cpn.setcPdctId(cp.getCpId());
				cpn = cpnService.selectList(cpn).get(0);
				List list = new ArrayList();
				list.add(p);
				list.add(cp);
				if(cp.getArea() != null){
					list.add(true);
				}else{
					list.add(false);
				}
				list.add(cpn);
				Warehouse wh = new Warehouse();
				wh.setWarehouseId(cp.getArea());
				wh = wService.selectOne(wh);
				list.add(wh);
				JSONArray obj = JSONArray.fromObject(list);
				resp.getWriter().write(obj.toString());
			}else{
				resp.getWriter().write("");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void findCusPn(){
		try {
			String custId = req.getParameter("custId");
			if(custId != null){
				CustPdct cp = new CustPdct();
				cp.setCustId(Integer.valueOf(custId));
				JSONArray list = JSONArray.fromObject(cpService.selectList(cp));
				resp.getWriter().write(list.toString());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void checkPic(){
		try {
			String cpId = req.getParameter("cpId");
			String picNum = req.getParameter("picNum");
			if((cpId != null && !"null".equals(cpId)) && (picNum != null && !"null".equals(picNum))){
				CustPdct custPdct = new CustPdct();
				custPdct.setCpId(Integer.valueOf(cpId));
				custPdct = cpService.selectOne(custPdct);
				if(custPdct.getPicCount() >= Integer.valueOf(picNum)){
					resp.getWriter().write("true");
				}else{
					resp.getWriter().write("false");
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void checkBoxNum(){
		try {
			String boxNum = req.getParameter("boxNum");
			String cpId = req.getParameter("cpId");
			if((cpId != null && !"null".equals(cpId)) && (boxNum != null && !"null".equals(boxNum))){
				CustPdct custPdct = new CustPdct();
				custPdct.setCpId(Integer.valueOf(cpId));
				custPdct = cpService.selectOne(custPdct);
				CustPno cpn = new CustPno();
				cpn.setcPdctId(custPdct.getCpId());
				cpn = cpnService.selectList(cpn).get(0);
				if((cpn.getBoxNo() >= Integer.valueOf(boxNum))){
					resp.getWriter().write("true");
				}else{
					resp.getWriter().write("false");
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String add(){
		return "home";
	}
	
	public void findDetail(){
		try {
			product = pService.selectOne(product);
			JSON obj = JSONObject.fromObject(product);
			resp.getWriter().write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public CustPdct getCp() {
		return cp;
	}

	public void setCp(CustPdct cp) {
		this.cp = cp;
	}

	public CustPno getCpn() {
		return cpn;
	}

	public void setCpn(CustPno cpn) {
		this.cpn = cpn;
	}
	
}
