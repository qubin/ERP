package cn.joymates.erp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cn.joymates.erp.dao.IResourceDao;
import cn.joymates.erp.domain.Resource;
import cn.joymates.erp.utils.db.SessionFactoryUtil;

/**
 * 资源dao
 * @author Jackie Hou
 *
 */
public class ResourceDaoImpl1 implements IResourceDao {
	private SqlSession sess = SessionFactoryUtil.getSession();
	
	public List<Resource> selectAll() {
		return sess.selectList("basic.selectAllResource");
	}
	
	public List<Resource> selectResourceByRoleId(String roleid) {
		return sess.selectList("basic.selectResourceByRoleid", roleid);
	}
	
	public void insertRolePrivilege(String roleId, String resourceId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("roleId", roleId);
		map.put("resourceId", resourceId);
		sess.insert("insertRolePrivilege", map);
	}
	
	public void deleteRolePrivilege(String roleId) {
		sess.delete("deleteRolePrivilege", roleId);
	}
}
