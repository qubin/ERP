package cn.joymates.erp.domain.base;

public class BaseMaterial extends BaseVO {

	public BaseMaterial() {
		tablename = "t_material";

		fieldMap.put("uuid", "id");
		fieldMap.put("warehouseId", "warehouse_id");
		fieldMap.put("supplyId", "supply_id");
		fieldMap.put("density", "density");
		fieldMap.put("thickness", "thickness");
		fieldMap.put("desc", "desc1");
		fieldMap.put("weight", "weight");
		fieldMap.put("standard", "standard");
		fieldMap.put("scrollCount", "scroll_count");
		fieldMap.put("isAlarm", "is_alarm");
		fieldMap.put("alarmWeight", "alarm_weight");
		fieldMap.put("isLogout", "is_logout");
		fieldMap.put("logoutReason", "logout_reason");
		fieldMap.put("mmatId", "mmat_id");
		fieldMap.put("remark", "remark");
		fieldMap.put("remark1", "remark1");
		
		this.id = "id";
	}

	private Integer uuid;
	private Integer warehouseId;
	private Integer supplyId;
	private Double density;
	private Double thickness;
	private String desc;
	private Double weight;
	private String standard;
	private Double scrollCount;
	private String isAlarm;
	private Double alarmWeight;
	private String isLogout;
	private String logoutReason;
	private Integer mmatId;
	private String remark;
	private String remark1;
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Integer getSupplyId() {
		return supplyId;
	}
	public void setSupplyId(Integer supplyId) {
		this.supplyId = supplyId;
	}
	public Double getDensity() {
		return density;
	}
	public void setDensity(Double density) {
		this.density = density;
	}
	public Double getThickness() {
		return thickness;
	}
	public void setThickness(Double thickness) {
		this.thickness = thickness;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public Double getScrollCount() {
		return scrollCount;
	}
	public void setScrollCount(Double scrollCount) {
		this.scrollCount = scrollCount;
	}
	public String getIsAlarm() {
		return isAlarm;
	}
	public void setIsAlarm(String isAlarm) {
		this.isAlarm = isAlarm;
	}
	public Double getAlarmWeight() {
		return alarmWeight;
	}
	public void setAlarmWeight(Double alarmWeight) {
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
	public Integer getMmatId() {
		return mmatId;
	}
	public void setMmatId(Integer mmatId) {
		this.mmatId = mmatId;
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
