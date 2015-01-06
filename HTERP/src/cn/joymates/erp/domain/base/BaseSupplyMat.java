package cn.joymates.erp.domain.base;

public class BaseSupplyMat extends BaseVO{

	public BaseSupplyMat() {
		tablename = "t_supply_mat";
		fieldMap.put("supplyMatId", "id");
		fieldMap.put("supplyId", "supply_id");
		fieldMap.put("matSupplierName", "mat_supplier_name");
		fieldMap.put("htMatNo", "ht_mat_no");
		fieldMap.put("isLogout", "is_logout");
		fieldMap.put("logoutReason", "logout_reason");
		this.id = "id";
	}

	private Integer supplyMatId;
	private String matSupplierName;
	private String htMatNo;
	private Integer supplyId;
	private String isLogout;
	private String logoutReason;
	
	public Integer getSupplyMatId() {
		return supplyMatId;
	}
	public void setSupplyMatId(Integer supplyMatId) {
		this.supplyMatId = supplyMatId;
	}
	public String getMatSupplierName() {
		return matSupplierName;
	}
	public void setMatSupplierName(String matSupplierName) {
		this.matSupplierName = matSupplierName;
	}
	public String getHtMatNo() {
		return htMatNo;
	}
	public void setHtMatNo(String htMatNo) {
		this.htMatNo = htMatNo;
	}
	public Integer getSupplyId() {
		return supplyId;
	}
	public void setSupplyId(Integer supplyId) {
		this.supplyId = supplyId;
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
