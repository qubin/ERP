package cn.joymates.erp.domain.base;

public class BaseUserRole extends BaseVO {

	public BaseUserRole() {
		tablename = "tb_user_role";

		fieldMap.put("roleId", "role_id");
		fieldMap.put("userId", "user_id");
		
		this.id = "user_id";
	}

	private String roleId;
	private String userId;
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
