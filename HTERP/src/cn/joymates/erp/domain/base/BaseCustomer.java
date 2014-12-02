package cn.joymates.erp.domain.base;

public class BaseCustomer extends BaseVO {
	

	public BaseCustomer() {
		tablename = "t_customer";
		fieldMap.put("custId", "id");
		fieldMap.put("custName", "name");
		fieldMap.put("telephone", "telephone");
		fieldMap.put("fax", "fax");
		fieldMap.put("custConPerson", "con_person");
		fieldMap.put("custPhone", "con_phone");
		fieldMap.put("isLogout", "is_logout");
		fieldMap.put("logOutReason", "logout_reason");
		fieldMap.put("remark", "remark");
		fieldMap.put("remark1", "remark1");
		this.id = "id";
	}
	private Integer custId;
	private String custName;
	private String telephone;
	private String fax;
	private String custConPerson;
	private String custPhone;
	private String isLogout;
	private String logOutReason;
	private String remark;
	private String remark1;
	
	
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getCustConPerson() {
		return custConPerson;
	}
	public void setCustConPerson(String custConPerson) {
		this.custConPerson = custConPerson;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
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
