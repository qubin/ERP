package cn.joymates.erp.domain.base;

import java.math.BigDecimal;

public class BaseQCStore extends BaseVO {

	public BaseQCStore() {
		tablename = "t_qc";

		fieldMap.put("uuid", "id");
		fieldMap.put("tPId", "t_p_id");
		fieldMap.put("matOrPdct", "mat_or_pdct");
		fieldMap.put("matPdctId", "mat_pdct_id");
		fieldMap.put("weight", "weight");
		fieldMap.put("picCount", "pic_count");
		fieldMap.put("isolateReason", "isolate_reason");
		fieldMap.put("remark", "remark");
		fieldMap.put("remark1", "remark1");
		
		this.id = "id";
	}

	private Integer uuid;
	private String tPId;
	private String matOrPdct;
	private Integer matPdctId;
	private BigDecimal weight;
	private String picCount;
	private String isolateReason;
	private String remark;
	private String remark1;
	
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public String gettPId() {
		return tPId;
	}
	public void settPId(String tPId) {
		this.tPId = tPId;
	}
	public String getMatOrPdct() {
		return matOrPdct;
	}
	public void setMatOrPdct(String matOrPdct) {
		this.matOrPdct = matOrPdct;
	}
	public Integer getMatPdctId() {
		return matPdctId;
	}
	public void setMatPdctId(Integer matPdctId) {
		this.matPdctId = matPdctId;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public String getPicCount() {
		return picCount;
	}
	public void setPicCount(String picCount) {
		this.picCount = picCount;
	}
	public String getIsolateReason() {
		return isolateReason;
	}
	public void setIsolateReason(String isolateReason) {
		this.isolateReason = isolateReason;
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
