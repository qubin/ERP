package cn.joymates.erp.domain.base;

import java.util.Date;

public class BaseSellBill extends BaseVO {
	
	public BaseSellBill(){
		tablename = "t_sell_bill";
		fieldMap.put("sbId", "id");
		fieldMap.put("code", "code");
		fieldMap.put("orderData", "order_date");
		fieldMap.put("custId", "customer_id");
		fieldMap.put("leadDate", "lead_date");
		fieldMap.put("checkMothod", "check_mothod");
		fieldMap.put("supplyArea", "supply_area");
		fieldMap.put("transFee", "trans_fee");
		fieldMap.put("argeeMent", "argeement");
		fieldMap.put("payWay", "pay_way");
		fieldMap.put("payType", "pay_type");
		fieldMap.put("matPay", "mat_pay");
		fieldMap.put("pdctPrice", "pdct_price");
		fieldMap.put("qttCtfc", "qtt_ctfc");
		fieldMap.put("verifyRemark", "verify_remark");
		fieldMap.put("verifyStatus", "verify_status");
		fieldMap.put("verifyPerson", "verify_person");
		fieldMap.put("isOrderOver", "is_order_over");
		fieldMap.put("overRemark", "over_remark");
		fieldMap.put("overPerson", "over_person");
		fieldMap.put("remark", "remark");
		fieldMap.put("remark1", "remark1");
		this.id = "id";
	}
	
	private Integer sbId;
	private String code;
	private Date orderData;
	private Integer custId;
	private Date leadDate;
	private String checkMothod;
	private String supplyArea;
	private String transFee;
	private String argeeMent;
	private String payWay;
	private String payType;
	private String matPay;
	private String pdctPrice;
	private String qttCtfc;
	private String verifyRemark;
	private String verifyStatus;
	private String verifyPerson;
	private String isOrderOver;
	private String overRemark;
	private String overPerson;
	private String remark;
	private String remark1;
	
	
	public String getMatPay() {
		return matPay;
	}
	public void setMatPay(String matPay) {
		this.matPay = matPay;
	}
	public Integer getSbId() {
		return sbId;
	}
	public void setSbId(Integer sbId) {
		this.sbId = sbId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getOrderData() {
		return orderData;
	}
	public void setOrderData(Date orderData) {
		this.orderData = orderData;
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public Date getLeadDate() {
		return leadDate;
	}
	public void setLeadDate(Date leadDate) {
		this.leadDate = leadDate;
	}
	public String getCheckMothod() {
		return checkMothod;
	}
	public void setCheckMothod(String checkMothod) {
		this.checkMothod = checkMothod;
	}
	public String getSupplyArea() {
		return supplyArea;
	}
	public void setSupplyArea(String supplyArea) {
		this.supplyArea = supplyArea;
	}
	public String getTransFee() {
		return transFee;
	}
	public void setTransFee(String transFee) {
		this.transFee = transFee;
	}
	public String getArgeeMent() {
		return argeeMent;
	}
	public void setArgeeMent(String argeeMent) {
		this.argeeMent = argeeMent;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPdctPrice() {
		return pdctPrice;
	}
	public void setPdctPrice(String pdctPrice) {
		this.pdctPrice = pdctPrice;
	}
	public String getQttCtfc() {
		return qttCtfc;
	}
	public void setQttCtfc(String qttCtfc) {
		this.qttCtfc = qttCtfc;
	}
	public String getVerifyRemark() {
		return verifyRemark;
	}
	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}
	public String getVerifyStatus() {
		return verifyStatus;
	}
	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus;
	}
	public String getVerifyPerson() {
		return verifyPerson;
	}
	public void setVerifyPerson(String verifyPerson) {
		this.verifyPerson = verifyPerson;
	}
	public String getIsOrderOver() {
		return isOrderOver;
	}
	public void setIsOrderOver(String isOrderOver) {
		this.isOrderOver = isOrderOver;
	}
	public String getOverRemark() {
		return overRemark;
	}
	public void setOverRemark(String overRemark) {
		this.overRemark = overRemark;
	}
	public String getOverPerson() {
		return overPerson;
	}
	public void setOverPerson(String overPerson) {
		this.overPerson = overPerson;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	
	
}

