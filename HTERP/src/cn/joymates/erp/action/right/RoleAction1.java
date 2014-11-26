package cn.joymates.erp.action.right;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Resource;
import cn.joymates.erp.domain.Role;
import cn.joymates.erp.domain.User;
import cn.joymates.erp.service.RoleService;
import cn.joymates.erp.utils.ServiceProxyFactory;
import cn.joymates.erp.utils.UUIDGenerator;
@Deprecated
public class RoleAction1 extends BaseAction {
	public String showHome() {
		return "showHomeUI";
	}
	
	public String find() {
		if (role == null) {
			role = new Role();
		}
		
		User u1 = (User)session.getAttribute("loggedUser");
		int quota = u1.getRole().getRoleQuota();
		
		//只能查询自己及其以下权的角色
		String lastsql = " and this_.role_qutoa>" + quota;
		req.setAttribute("roleList", roleService.getEcsideList(ec_rd, role, lastsql, req)); 
		req.setAttribute("logoutMap", Role.LOGOUT_MAP);
		return "find";
	}
	
	public String showAddUI() {
//		req.setAttribute("quotaList", Role.quotas);
		return "showAddUI";
	}
	
	public String add() {
		role.setRoleId(UUIDGenerator.getUUID());
		role.setIsLogout(Role.NOT_LOGOUT);
		
		roleService.save(role);
		return "showHomeUI";
	}
	
	public String modifyUI() {
//		req.setAttribute("quotaList", Role.quotas);
		role = roleService.selectOne(role);
		return "modifyUI";
	}
	
	public String modify() {
		roleService.update(role);
		return "showHomeUI";
	}
	
	public String authUI() {
		RoleService service = ServiceProxyFactory.getInstance(new RoleService());
		service.showResources(role, resource, "20", req);
		
//		req.setAttribute("resourceTypeMap", Resource.resourceTypeMap);
		return "authUI";
	}
	
	public String auth() {
		RoleService service = ServiceProxyFactory.getInstance(new RoleService());
		service.auth(role, resourceIds);
		return "showHomeUI";
	}
	
	private RoleService roleService = ServiceProxyFactory.getInstanceNoMybatis(new RoleService());
	
	private Role role;
	
	private String resourceIds;

	private Resource resource;
	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

}
