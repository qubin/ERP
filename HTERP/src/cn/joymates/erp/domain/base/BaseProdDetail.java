package cn.joymates.erp.domain.base;

import java.math.BigDecimal;

public class BaseProdDetail extends BaseVO {
	
	public BaseProdDetail() {
		tablename = "t_pdct_detail";
		fieldMap.put("customerId", "customer_id");
		fieldMap.put("batchCode", "batch_code");
		fieldMap.put("boxNo", "box_no");
		fieldMap.put("pdctId", "pdct_id");
		fieldMap.put("picCount", "pic_count");
		fieldMap.put("remark", "remark");
		fieldMap.put("weight", "weight");
	}
	
	
	private String batchCode;
	private String boxNo;
	private Integer pdctId;
	private Integer picCount;
	private String remark;
	private BigDecimal weight;
	private Integer customerId;
	
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getBatchCode() {
		return batchCode;
	}
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
	public String getBoxNo() {
		return boxNo;
	}
	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}
	public Integer getPdctId() {
		return pdctId;
	}
	public void setPdctId(Integer pdctId) {
		this.pdctId = pdctId;
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
	
}
