package cn.joymates.erp.domain.base;

public class BaseWarehouse extends BaseVO {
	
	public BaseWarehouse() {
		tablename = "t_warehouse";
		fieldMap.put("warehouseId", "id");
		fieldMap.put("sign1", "sign1");
		fieldMap.put("area", "area");
		fieldMap.put("desc1", "desc1");
		fieldMap.put("isLogout", "is_logout");
		fieldMap.put("logOutReason", "logout_reason");
		this.id = "id";
	}
	
	private Integer warehouseId;
	private String sign1;
	private String area;
	private String desc1;
	private String isLogout;
	private String logOutReason;
	
	
	
	public String getIsLogout() {
		return isLogout;
	}
	public void setIsLogout(String isLogout) {
		this.isLogout = isLogout;
	}
	public String getLogOutReason() {
		return logOutReason;
	}
	public void setLogOutReason(String logOutReason) {
		this.logOutReason = logOutReason;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getSign1() {
		return sign1;
	}
	public void setSign1(String sign1) {
		this.sign1 = sign1;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDesc1() {
		return desc1;
	}
	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}
	
	
}
