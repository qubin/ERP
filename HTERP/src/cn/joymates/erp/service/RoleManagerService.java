package cn.joymates.erp.service;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.erp.dao.impl.ResourceDaoImpl1;
import cn.joymates.erp.dao.impl.RoleDaoImpl1;
import cn.joymates.erp.domain.Resource;
import cn.joymates.erp.domain.Role;
import cn.joymates.erp.utils.UUIDGenerator;

/**
 * 角色管理服务类
 * 
 * @author Jackie Hou
 *
 */
@Deprecated
public class RoleManagerService {
	
	/**
	 * add role
	 * @param role
	 */
	public void addRole(Role role) {
		role.setRoleId(UUIDGenerator.getUUID());
		role.setCreateTime(new Date());
		role.setIsLogout(Role.NOT_LOGOUT);
		
		//默认有最小权
//		role.setRoleQuota(Role.MIN_ROLE_QUOTA);
		dao.save(role);
	}
	
	/**
	 * find by condition
	 * @param ecRd
	 * @param req
	 * @return
	 */
//	public List<Role> findAll(String ecRd, HttpServletRequest req, Role role) {
//		return dao.findAll(ecRd, req, role);
//	}
	
	public Role findById(String id) {
		return dao.findById(id);
	}
	
	public void modifyRole(Role role) {
		String logout = role.getIsLogout(); 
		if (logout == "false") {
			role.setIsLogout(Role.NOT_LOGOUT);
			role.setLogoutReason("");
		} else {
			role.setIsLogout(Role.LOGOUT);
		}
		dao.update(role);
	}
	
	public Map<String, List<Resource>> getAuthData(String roleid) {
		Map<String, List<Resource>> resultMap = new HashMap<String, List<Resource>>();
		resourceDao =  new ResourceDaoImpl1();
		resultMap.put("all", resourceDao.selectAll());
		resultMap.put("mine", resourceDao.selectResourceByRoleId(roleid));
		
		return resultMap;
	}
	
	/**
	 * 授权保存
	 * @param roleId
	 * @param resList
	 */
	public void saveAuth(String roleId, String resList) {
		resourceDao =  new ResourceDaoImpl1();
		resourceDao.deleteRolePrivilege(roleId);
		
		//get parentId
//		Set<String> parentIdSet = new HashSet<String>();
//		for (String res : resList) {
//			String parentId = res.substring(0, (res.length() - 3));
//			parentIdSet.add(parentId);
//		}
//		
//		resList.addAll(parentIdSet);
		String []strValue=resList.split(",");
	//	for (String res : resList) {
		for (int i=0;i<strValue.length;i++) {
			resourceDao.insertRolePrivilege(roleId, strValue[i]);
		}
	}
	
	private RoleDaoImpl1 dao ;
	
	private ResourceDaoImpl1 resourceDao;
	
	public RoleManagerService() {
		setDao(new RoleDaoImpl1());
	}

	public void setDao(RoleDaoImpl1 dao) {
		this.dao = dao;
	}
	
	
}
