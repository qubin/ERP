package cn.joymates.erp.action.right;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.annotations.ErpUsing;
import cn.joymates.erp.domain.Resource;
import cn.joymates.erp.domain.Role;
import cn.joymates.erp.domain.User;
import cn.joymates.erp.service.RoleManagerService;
import cn.joymates.erp.service.RoleService;
import cn.joymates.erp.utils.ResponseWriteUtil;
import cn.joymates.erp.utils.ServiceProxyFactory;
import cn.joymates.erp.utils.UUIDGenerator;
/**
 * 角色管理action
 * 
 * @author Jackie Hou
 *
 */
public class RoleManagerAction extends BaseAction {
	
	@ErpUsing
	public String addRole() {
		req.setAttribute("quotaList", Role.quotas);
		return "addUI";
	}
	
	/**
	 * 角色添加
	 * @return
	 */
	@ErpUsing
	public String add() {
		role.setRoleId(UUIDGenerator.getUUID());
		role.setIsLogout(Role.NOT_LOGOUT);
		role.setCreateTime(new Date());
		
		User user = (User)session.getAttribute("loggedUser");
		role.setCreatePerson(user.getUserLoginId());
		roleService.save(role);
		return "find";
	}
	
	@ErpUsing
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
	
	public void findRole(){
		String roleName=req.getParameter("role_name");
		Boolean	 result =roleService.findRoleByName(roleName);
		try {
			ResponseWriteUtil.responseWrite(resp, result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//根据编号查找
	public String showModifyPage() {
		role = roleService.findById(role.getRoleId());
		String logout = role.getIsLogout();
		
		role.setIsLogout("true");
		if (logout.equals(Role.NOT_LOGOUT)) {
			role.setIsLogout("false");
		}
		req.setAttribute("quotaList", Role.quotas);
		return "modifyUI";
	}
	
	public String modify() {
		roleService.modifyRole(role);
		return "find";
	}
	
	/**
	 * 授权ui
	 * @return
	 */
	public String authUI() {
//		Map<String, List<Resource>> rmap = roleManagerService.getAuthData(role.getRoleId());
//		req.setAttribute("resourceList", rmap.get("all"));
//		
//		List<Resource> mineResList = rmap.get("mine");
//		resourceIds = new ArrayList<String>();
//		for (Resource r : mineResList) {
//			resourceIds.add(r.getResourceId());
//		}
//		return "resourceList";
		RoleService service = ServiceProxyFactory.getInstance(new RoleService());
		service.showResources(role, resource, "20", req);
		
//		req.setAttribute("resourceTypeMap", Resource.resourceTypeMap);
		return "authUI";
	}
	
	/**
	 * 授权aciton
	 * @return
	 */
	public String auth() {
		String resource_ids=req.getParameter("resource_Ids");
		roleManagerService.saveAuth(role.getRoleId(), resource_ids);
		return "find";
	}
	
	private RoleManagerService roleManagerService = ServiceProxyFactory.getInstance(new RoleManagerService());
	private RoleService roleService = ServiceProxyFactory.getInstance(new RoleService());
	
	private Role role;
	
	private List roleList;
	
	private List<String> resourceIds; 
	
	private Resource resource;

	public List<String> getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(List<String> resourceIds) {
		this.resourceIds = resourceIds;
	}

	public List getRoleList() {
		return roleList;
	}

	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	
}
