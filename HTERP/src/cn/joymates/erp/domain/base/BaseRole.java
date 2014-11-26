package cn.joymates.erp.domain.base;

import java.util.Date;

public class BaseRole extends BaseVO {
	public BaseRole() {
		tablename = "tb_role";
		fieldMap.put("roleId", "role_id");
        fieldMap.put("roleName", "role_name");

        fieldMap.put("roleQuota", "role_qutoa");
        fieldMap.put("isLogout", "is_logout");
        fieldMap.put("logoutReason", "logout_reason");
        
        fieldMap.put("createPerson", "create_person");
        fieldMap.put("createTime", "create_time");
        fieldMap.put("remark", "remark");

		this.id = "role_id"; 
	}
	
	private String roleId;
	private String roleName;
	private Integer roleQuota;
	
	private String isLogout;
	private String logoutReason;
	private String createPerson;
	
	private Date createTime;
	private String remark;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getRoleQuota() {
		return roleQuota;
	}
	public void setRoleQuota(Integer roleQuota) {
		this.roleQuota = roleQuota;
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
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
