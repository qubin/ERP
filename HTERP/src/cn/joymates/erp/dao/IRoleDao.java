package cn.joymates.erp.dao;

import java.util.List;
import java.util.Map;

import cn.joymates.erp.domain.Resource;
import cn.joymates.erp.domain.Role;

public interface IRoleDao extends BaseDao<Role> {
	/**
	 * 根据角色查找已分配的资源
	 * @param roleId
	 * @return
	 */
	List<Resource> findResouceByRoleId(String roleId);
	
	void deleteById(Role r);
	
	void saveResPrivilege(Map<String, String> map);
	
	List findRoleByName(Map<String, Object> map);
}
