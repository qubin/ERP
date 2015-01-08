package cn.joymates.erp.action.finance;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.FinancePay;
import cn.joymates.erp.domain.Pdceistct;
import cn.joymates.erp.domain.SellBill;
import cn.joymates.erp.domain.SellDetail;
import cn.joymates.erp.service.FinanceService;
import cn.joymates.erp.service.SellDetailService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class FinanceAction extends BaseAction {

	/*
	 * 财务-录入home
	 */
	public String entering() {
		if (sellBill == null) {
			sellBill = new SellBill();
		}
		List<Map<String, Object>> sellBillList = financeService.findSellBillList01(sellBill,finance_key,finance_name,ec_rd,req);
		req.setAttribute("sellBillList", sellBillList);
		req.setAttribute("STATUS",FinancePay.STATUS);
		return "entering_home";
	}
	
	/*
	 * 财务-录入查询
	 */
	public String findSellBill(){
		if (sellBill == null) {
			sellBill = new SellBill();
		}
		List<Map<String, Object>> sellBillList = financeService.findSellBillList01(sellBill,finance_key,finance_name,ec_rd,req);
		req.setAttribute("sellBillList", sellBillList);
		req.setAttribute("STATUS", FinancePay.STATUS);
		return "entering_home";
	}
	
	/*
	 * 财务-录入add
	 */
	public String showAddFinance(){
		if (sellBill == null) {
			sellBill = new SellBill();
		}
		List<Map<String, Object>> sellBillList = financeService.findSellBillList01(sellBill,"id",sellBill.getSbId().toString(),ec_rd,req);
		req.setAttribute("sellBillList", sellBillList);
		return "entering_add";
	}
	
	/*
	 * 财务-录入-保存已付金额
	 */
	public String saveAddFinance(){
		financeService.save(financePay);
		return entering();
	}
	
	/*
	 * 财务-审核home
	 */
	public String audit(){
		if (sellBill == null) {
			sellBill = new SellBill();
		}
		List<Map<String, Object>> sellBillList = financeService.findSellBillList02(sellBill,"finance","",ec_rd,req);
		req.setAttribute("sellBillList", sellBillList);
		req.setAttribute("STATUS", FinancePay.STATUS);
		return "audit_home";
	}
	
	/*
	 * 财务-审核add
	 */
	public String showAudit(){
		if (sellBill == null) {
			sellBill = new SellBill();
		}
		List<Map<String, Object>> sellBillList = financeService.findSellBillList02(sellBill,"id",sellBill.getSbId().toString(),ec_rd,req);
		req.setAttribute("sellBillList", sellBillList);
		return "audit_add";
	}
	
	/*
	 * 财务-审核save
	 */
	public String saveAudit(){
		financeService.update(financePay);
		return audit();
	}
	
	/*
	 * 财务-查询home
	 */
	public String search(){
		if (sellBill == null) {
			sellBill = new SellBill();
		}
		List<Map<String, Object>> sellBillList = financeService.findSellBillList03(sellBill,"1",finance_key,finance_name,ec_rd,req);
		req.setAttribute("sellBillList", sellBillList);
		req.setAttribute("STATUS", FinancePay.STATUS);
		return "search_home";
	}
	
	/*
	 * 财务-查询find
	 */
	public String findSearch(){
		if (sellBill == null) {
			sellBill = new SellBill();
		}
		List<Map<String, Object>> sellBillList = financeService.findSellBillList03(sellBill,"1",finance_key,finance_name,ec_rd,req);
		req.setAttribute("sellBillList", sellBillList);
		req.setAttribute("STATUS", FinancePay.STATUS);
		return "search_home";
	}
	
	/*
	 * 统计
	 */
	public String statistics(){
		if (sellBill == null) {
			sellBill = new SellBill();
		}
		List<Map<String, Object>> sellBillList = financeService.findSellBillList03(sellBill,"1",finance_key,finance_name,"6",req);
		
		BigDecimal sumAmount = new BigDecimal(0);
		BigDecimal sumPrepaid = new BigDecimal(0);
		
		for(int i=0;i<sellBillList.size();i++){
			BigDecimal amount = new BigDecimal(sellBillList.get(i).get("AMOUNT").toString());
			BigDecimal prepaid = new BigDecimal(sellBillList.get(i).get("PREPAID").toString());
			sumAmount = sumAmount.add(amount);
			sumPrepaid = sumPrepaid.add(prepaid);
		}
		
		req.setAttribute("sellBillList", sellBillList);
		req.setAttribute("STATUS", FinancePay.STATUS);
		req.setAttribute("sumAmount", sumAmount);
		req.setAttribute("sumPrepaid", sumPrepaid);
		req.setAttribute("sumNoPrepaid", sumAmount.subtract(sumPrepaid));
		return "statistics_home";
	}
	
	/*
	 * 统计查询
	 */
	public String findStatistics(){
		if (sellBill == null) {
			sellBill = new SellBill();
		}
		List<Map<String, Object>> sellBillList = financeService.findSellBillList03(sellBill,"3",finance_key,finance_name,"6",req);
		
		BigDecimal sumAmount = new BigDecimal(0);
		BigDecimal sumPrepaid = new BigDecimal(0);
		
		for(int i=0;i<sellBillList.size();i++){
			BigDecimal amount = new BigDecimal(sellBillList.get(i).get("AMOUNT").toString());
			BigDecimal prepaid = new BigDecimal(sellBillList.get(i).get("PREPAID").toString());
			sumAmount = sumAmount.add(amount);
			sumPrepaid = sumPrepaid.add(prepaid);
		}
		
		req.setAttribute("sellBillList", sellBillList);
		req.setAttribute("STATUS", FinancePay.STATUS);
		req.setAttribute("sumAmount", sumAmount);
		req.setAttribute("sumPrepaid", sumPrepaid);
		req.setAttribute("sumNoPrepaid", sumAmount.subtract(sumPrepaid));
		return "statistics_home";
	}
	
	

	private FinanceService financeService = ServiceProxyFactory.getInstanceNoMybatis(new FinanceService());
	private SellDetailService sellDetailService = ServiceProxyFactory.getInstanceNoMybatis(new SellDetailService());
	
	private SellBill sellBill;
	private SellDetail sellDetail;
	private Pdceistct pdceistct;
	private FinancePay financePay;
	
	private String finance_key;
	private String finance_name;

	public Pdceistct getPdceistct() {
		return pdceistct;
	}

	public void setPdceistct(Pdceistct pdceistct) {
		this.pdceistct = pdceistct;
	}

	public SellBill getSellBill() {
		return sellBill;
	}

	public void setSellBill(SellBill sellBill) {
		this.sellBill = sellBill;
	}

	public String getFinance_key() {
		return finance_key;
	}

	public void setFinance_key(String finance_key) {
		this.finance_key = finance_key;
	}

	public String getFinance_name() {
		return finance_name;
	}

	public void setFinance_name(String finance_name) {
		this.finance_name = finance_name;
	}

	public SellDetail getSellDetail() {
		return sellDetail;
	}

	public void setSellDetail(SellDetail sellDetail) {
		this.sellDetail = sellDetail;
	}

	public FinancePay getFinancePay() {
		return financePay;
	}

	public void setFinancePay(FinancePay financePay) {
		this.financePay = financePay;
	}
	
}
