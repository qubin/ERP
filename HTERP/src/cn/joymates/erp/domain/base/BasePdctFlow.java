package cn.joymates.erp.domain.base;

import java.util.Date;

public class BasePdctFlow extends BaseVO {
	
	public BasePdctFlow(){
		tablename = "t_pdct_flow";
		fieldMap.put("pdctFlowId", "id");
		fieldMap.put("outTime", "out_time");
		fieldMap.put("custPdctId", "cust_pdct_Id");
		fieldMap.put("count", "count");
		fieldMap.put("inOrOut", "in_or_out");
		fieldMap.put("outPerson", "out_person");
		fieldMap.put("isTctId", "istct_id");
		fieldMap.put("boxNo", "box_no");
		fieldMap.put("boxNum", "box_num");
		fieldMap.put("batchCode", "batch_code");
		fieldMap.put("remark", "reamrk");
		fieldMap.put("remark1", "remark1");
		fieldMap.put("isLogOut", "is_logout");
		fieldMap.put("logOutReason", "logout_reason");
	}
	
	private Integer pdctFlowId;
	private Date outTime;
	private Integer custPdctId;
	private Integer count;
	private String inOrOut;
	private String outPerson;
	private Integer isTctId;
	private Integer boxNo;
	private Integer boxNum;
	private String batchCode;
	private String remark;
	private String remark1;
	private String isLogOut;
	private String logOutReason;
	
	
	public Integer getPdctFlowId() {
		return pdctFlowId;
	}
	public void setPdctFlowId(Integer pdctFlowId) {
		this.pdctFlowId = pdctFlowId;
	}
	
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getInOrOut() {
		return inOrOut;
	}
	public void setInOrOut(String inOrOut) {
		this.inOrOut = inOrOut;
	}
	public String getOutPerson() {
		return outPerson;
	}
	public void setOutPerson(String outPerson) {
		this.outPerson = outPerson;
	}
	public Integer getIsTctId() {
		return isTctId;
	}
	public void setIsTctId(Integer isTctId) {
		this.isTctId = isTctId;
	}
	
	public Integer getBoxNo() {
		return boxNo;
	}
	public void setBoxNo(Integer boxNo) {
		this.boxNo = boxNo;
	}
	public Integer getBoxNum() {
		return boxNum;
	}
	public void setBoxNum(Integer boxNum) {
		this.boxNum = boxNum;
	}
	public String getBatchCode() {
		return batchCode;
	}
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
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
	public Integer getCustPdctId() {
		return custPdctId;
	}
	public void setCustPdctId(Integer custPdctId) {
		this.custPdctId = custPdctId;
	}
	
	
}
