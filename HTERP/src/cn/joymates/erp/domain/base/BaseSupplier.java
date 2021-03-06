package cn.joymates.erp.domain.base;

public class BaseSupplier extends BaseVO {

	public BaseSupplier() {
		tablename = "t_supplier";

		fieldMap.put("uuid", "id");
		fieldMap.put("code", "code");
		fieldMap.put("name", "name");
		fieldMap.put("desc", "desc1");
		fieldMap.put("conPerson", "con_person");
		fieldMap.put("conPhone", "con_phone");
		fieldMap.put("fax", "fax");
		fieldMap.put("address", "address");
		fieldMap.put("email", "email");
		fieldMap.put("isLogout", "is_logout");
		fieldMap.put("logoutReason", "logout_reason");
		fieldMap.put("remark", "remark");
		fieldMap.put("bank", "bank");
		fieldMap.put("account", "account");
		
		this.id = "id";
	}

	private Integer uuid;
	private String code;
	private String name;
	private String desc;
	private String conPerson;
	private String conPhone;
	private String fax;
	private String address;
	private String email;
	private String isLogout;
	private String logoutReason;
	private String remark;
	private String bank;
	private String account;
	
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getConPerson() {
		return conPerson;
	}
	public void setConPerson(String conPerson) {
		this.conPerson = conPerson;
	}
	public String getConPhone() {
		return conPhone;
	}
	public void setConPhone(String conPhone) {
		this.conPhone = conPhone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}

}
