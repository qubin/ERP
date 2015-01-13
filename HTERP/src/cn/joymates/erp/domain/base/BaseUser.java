package cn.joymates.erp.domain.base;

import java.util.Date;

public abstract class BaseUser extends BaseVO {

	public BaseUser() {
		tablename = "tb_user";

		fieldMap.put("userId", "user_id");
		fieldMap.put("password1", "password1");
		fieldMap.put("userLoginId", "user_login_id");
		
		fieldMap.put("createTime", "create_time");
		fieldMap.put("createPerson", "create_person");
		fieldMap.put("isLogout", "is_logout");
		
		fieldMap.put("logoutReason", "logout_reason");
		fieldMap.put("remark", "remark");
		fieldMap.put("custId", "cust_id");
		this.id = "user_id";
	}

	private String userId;
	private String userLoginId;
	private String password1;
	
	private Date createTime;
	private String createPerson;
	
	private String isLogout;
	private String logoutReason;
	
	private String remark;
	
	private Integer custId;
	
	
	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
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


}
