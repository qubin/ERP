package cn.joymates.erp.domain.base;

public class BaseWarehouse extends BaseVO {
	
	public BaseWarehouse() {
		tablename = "t_warehouse";
		fieldMap.put("WarehouseId", "id");
		fieldMap.put("sign", "sign");
		fieldMap.put("area", "area");
		fieldMap.put("desc1", "desc1");
		this.id = "id";
	}
	
	private String WarehouseId;
	private String sign;
	private String area;
	private String desc1;

	
	public String getWarehouseId() {
		return WarehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		WarehouseId = warehouseId;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
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
