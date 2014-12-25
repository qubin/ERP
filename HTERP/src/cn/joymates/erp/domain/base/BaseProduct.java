package cn.joymates.erp.domain.base;

import java.math.BigDecimal;

public class BaseProduct extends BaseVO {

	public BaseProduct() {
		tablename = "t_product";

		fieldMap.put("uuid", "id");
		fieldMap.put("htPn", "ht_pn");
		fieldMap.put("cusPn", "cus_pn");
		fieldMap.put("area", "area");
		fieldMap.put("customerId", "customer_id");
		fieldMap.put("appFor", "app_for");
		fieldMap.put("properties", "properties");
		fieldMap.put("market", "market");
		fieldMap.put("patternType", "pattern_type");
		fieldMap.put("proStep", "pro_step");
		fieldMap.put("stepPitch", "step_pitch");
		fieldMap.put("matWidth", "mat_width");
		fieldMap.put("areca", "areca");
		fieldMap.put("singleWeight", "single_weight");
		fieldMap.put("packSize", "pack_size");
		fieldMap.put("ppapStatus", "ppap_status");
		fieldMap.put("picCount", "pic_count");
		fieldMap.put("isAlarm", "is_alarm");
		fieldMap.put("alarmWeight", "alarm_weight");
		fieldMap.put("isLogout", "is_logout");
		fieldMap.put("logoutReason", "logout_reason");
		fieldMap.put("remark", "remark");
		fieldMap.put("remark1", "remark1");
		
		this.id = "id";
	}
	
	   private Integer uuid;
	   private String htPn;
	   private String cusPn;
	   private Integer area;
	   private Integer customerId;
	   private String appFor;
	   private String properties;
	   private String market;
	   private String patternType;
	   private String proStep;
	   private BigDecimal stepPitch;
	   private BigDecimal matWidth;
	   private BigDecimal areca;
	   private BigDecimal singleWeight;
	   private String packSize;
	   private String ppapStatus;
	   private Integer picCount;
	   private String isAlarm;
	   private BigDecimal alarmWeight;
	   private String isLogout;
	   private String logoutReason;
	   private String remark;
	   private String remark1;
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public String getHtPn() {
		return htPn;
	}
	public void setHtPn(String htPn) {
		this.htPn = htPn;
	}
	public String getCusPn() {
		return cusPn;
	}
	public void setCusPn(String cusPn) {
		this.cusPn = cusPn;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getAppFor() {
		return appFor;
	}
	public void setAppFor(String appFor) {
		this.appFor = appFor;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getPatternType() {
		return patternType;
	}
	public void setPatternType(String patternType) {
		this.patternType = patternType;
	}
	public String getProStep() {
		return proStep;
	}
	public void setProStep(String proStep) {
		this.proStep = proStep;
	}
	public BigDecimal getStepPitch() {
		return stepPitch;
	}
	public void setStepPitch(BigDecimal stepPitch) {
		this.stepPitch = stepPitch;
	}
	public BigDecimal getMatWidth() {
		return matWidth;
	}
	public void setMatWidth(BigDecimal matWidth) {
		this.matWidth = matWidth;
	}
	public BigDecimal getAreca() {
		return areca;
	}
	public void setAreca(BigDecimal areca) {
		this.areca = areca;
	}
	public BigDecimal getSingleWeight() {
		return singleWeight;
	}
	public void setSingleWeight(BigDecimal singleWeight) {
		this.singleWeight = singleWeight;
	}
	public String getPackSize() {
		return packSize;
	}
	public void setPackSize(String packSize) {
		this.packSize = packSize;
	}
	public String getPpapStatus() {
		return ppapStatus;
	}
	public void setPpapStatus(String ppapStatus) {
		this.ppapStatus = ppapStatus;
	}
	public Integer getPicCount() {
		return picCount;
	}
	public void setPicCount(Integer picCount) {
		this.picCount = picCount;
	}
	public String getIsAlarm() {
		return isAlarm;
	}
	public void setIsAlarm(String isAlarm) {
		this.isAlarm = isAlarm;
	}
	public BigDecimal getAlarmWeight() {
		return alarmWeight;
	}
	public void setAlarmWeight(BigDecimal alarmWeight) {
		this.alarmWeight = alarmWeight;
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
