package cn.joymates.erp.domain.base;

import java.math.BigDecimal;
import java.util.Date;


public class BaseRowFlow extends BaseVO {
	
	public BaseRowFlow(){
		tablename = "t_raw_flow";
		fieldMap.put("rfId", "id");
		fieldMap.put("outTime", "out_time");
		fieldMap.put("outPerson", "out_person");
		fieldMap.put("inOrOut", "in_or_out");
		fieldMap.put("weight", "weight");
		fieldMap.put("materialId", "material_id");
		fieldMap.put("remark", "reamrk");
		fieldMap.put("isLogout", "is_logout");
		fieldMap.put("logoutReason", "logout_reason");
		this.id = "id";
	}
	private Integer rfId;
	private Date outTime;
	private String outPerson;
	private String inOrOut;
	private BigDecimal weight;
	private Integer materialId;
	private String remark;
	private String isLogout;
	private String logoutReason;
	public Integer getRfId() {
		return rfId;
	}
	public void setRfId(Integer rfId) {
		this.rfId = rfId;
	}
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	public String getOutPerson() {
		return outPerson;
	}
	public void setOutPerson(String outPerson) {
		this.outPerson = outPerson;
	}
	public String getInOrOut() {
		return inOrOut;
	}
	public void setInOrOut(String inOrOut) {
		this.inOrOut = inOrOut;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public Integer getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsLogout() {
		return isLogout;
	}
	public void setIsLogout(String isLogout) {
		this.isLogout = isLogout;
	}
	public String getLogoutReason() {
		return logoutReason;
	}
	public void setLogoutReason(String logoutReason) {
		this.logoutReason = logoutReason;
	}
	
	
	
}
