package cn.joymates.erp.domain.base;

import java.math.BigDecimal;

public class BaseCustPno extends BaseVO {
	
	public BaseCustPno(){
		tablename = "t_cus_pno";
		fieldMap.put("cPnoId", "id");
		fieldMap.put("cPdctId", "cust_pdct_id");
		fieldMap.put("batchCode", "batch_code");
		fieldMap.put("boxNo", "box_no");
		fieldMap.put("remark", "remark");
		fieldMap.put("boxNum", "box_num");
		fieldMap.put("area", "area");
		this.id = "id";
	}
	
	private Integer cPnoId;
	private Integer cPdctId;
	private Integer boxNum;
	private String batchCode;
	private Integer boxNo;
	private String remark;
	private Integer area;
	
	
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public Integer getBoxNum() {
		return boxNum;
	}
	public void setBoxNum(Integer boxNum) {
		this.boxNum = boxNum;
	}
	public Integer getcPnoId() {
		return cPnoId;
	}
	public void setcPnoId(Integer cPnoId) {
		this.cPnoId = cPnoId;
	}
	public Integer getcPdctId() {
		return cPdctId;
	}
	public void setcPdctId(Integer cPdctId) {
		this.cPdctId = cPdctId;
	}
	public String getBatchCode() {
		return batchCode;
	}
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
	
	public Integer getBoxNo() {
		return boxNo;
	}
	public void setBoxNo(Integer boxNo) {
		this.boxNo = boxNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
