package cn.joymates.erp.domain.base;

import java.math.BigDecimal;

public class BaseCustPdct extends BaseVO {
	public BaseCustPdct(){
		tablename = "t_cust_pdct";
		fieldMap.put("cpId", "id");
		fieldMap.put("custId", "customer_id");
		fieldMap.put("cus_pn", "cus_pn");
		fieldMap.put("prodId", "product_id");
		fieldMap.put("isLogOut", "is_logout");
		fieldMap.put("logOutReason", "logout_reason");
		fieldMap.put("picCount", "pic_count");
		fieldMap.put("remark", "remark");
		fieldMap.put("weight", "weight");
		fieldMap.put("area", "area");
		fieldMap.put("picNum", "pic_num");
		this.id = "id";
	}
	
	private Integer cpId;
	private Integer custId;
	private String cus_pn;
	private Integer prodId;
	private String isLogOut;
	private String logOutReason;
	private Integer picCount;
	private String remark;
	private BigDecimal weight;
	private Integer area;
	private Integer picNum;
	
	public Integer getPicNum() {
		return picNum;
	}
	public void setPicNum(Integer picNum) {
		this.picNum = picNum;
	}
	public Integer getPicCount() {
		return picCount;
	}
	public void setPicCount(Integer picCount) {
		this.picCount = picCount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public String getIsLogOut() {
		return isLogOut;
	}
	public void setIsLogOut(String isLogOut) {
		this.isLogOut = isLogOut;
	}
	public String getLogOutReason() {
		return logOutReason;
	}
	public void setLogOutReason(String logOutReason) {
		this.logOutReason = logOutReason;
	}
	public Integer getCpId() {
		return cpId;
	}
	public void setCpId(Integer cpId) {
		this.cpId = cpId;
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public String getCus_pn() {
		return cus_pn;
	}
	public void setCus_pn(String cus_pn) {
		this.cus_pn = cus_pn;
	}
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	
	
}
