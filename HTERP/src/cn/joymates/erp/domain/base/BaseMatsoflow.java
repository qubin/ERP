package cn.joymates.erp.domain.base;

import java.math.BigDecimal;
import java.util.Date;

public class BaseMatsoflow extends BaseVO {

	public BaseMatsoflow() {
		tablename = "t_so_flow";

		fieldMap.put("uuid", "id");
		fieldMap.put("prcTime", "prc_time");
		fieldMap.put("prcPerson", "prc_person");
		fieldMap.put("prcReason", "prc_reason");
		fieldMap.put("bWeight", "b_weight");
		fieldMap.put("aWeight", "a_weight");
		fieldMap.put("matD", "mat_d");
		fieldMap.put("isLogout", "is_logout");
		fieldMap.put("logoutReason", "logout_reason");
		fieldMap.put("remark", "remark");
		
		this.id = "id";
	}

	private Integer uuid;
	private Date prcTime;
	private String prcPerson;
	private String prcReason;
	private BigDecimal bWeight;
	private BigDecimal aWeight;
	private Integer matD;
	private String isLogout;
	private String logoutReason;
	private String remark;
	
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public Date getPrcTime() {
		return prcTime;
	}
	public void setPrcTime(Date prcTime) {
		this.prcTime = prcTime;
	}
	public String getPrcPerson() {
		return prcPerson;
	}
	public void setPrcPerson(String prcPerson) {
		this.prcPerson = prcPerson;
	}
	public String getPrcReason() {
		return prcReason;
	}
	public void setPrcReason(String prcReason) {
		this.prcReason = prcReason;
	}
	public BigDecimal getbWeight() {
		return bWeight;
	}
	public void setbWeight(BigDecimal bWeight) {
		this.bWeight = bWeight;
	}
	public BigDecimal getaWeight() {
		return aWeight;
	}
	public void setaWeight(BigDecimal aWeight) {
		this.aWeight = aWeight;
	}
	public Integer getMatD() {
		return matD;
	}
	public void setMatD(Integer matD) {
		this.matD = matD;
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
