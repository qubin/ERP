package cn.joymates.erp.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cn.joymates.erp.dao.IRoleDao;
import cn.joymates.erp.domain.Resource;
import cn.joymates.erp.domain.Role;
import cn.joymates.erp.utils.db.SessionFactoryUtil;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements IRoleDao {

	@Override
	public List<Resource> findResouceByRoleId(String roleId) {
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectList("right.findResouceByRoleId", roleId);
	}

	@Override
	public void deleteById(Role r) {
		SqlSession sess = SessionFactoryUtil.getSession();
		sess.delete("deleteByRoleId", r.getRoleId());
		
	}

	@Override
	public void saveResPrivilege(Map<String, String> map) {
		SqlSession sess = SessionFactoryUtil.getSession();
		sess.insert("insertRolePrivilege", map);
	}
	
	public Role findById(String id) {
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectOne("basic.findRoleById", id);
	}

	@Override
	public List findRoleByName(Map<String, Object> map) {
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectList("findRoleByName", map);
	}
	
	
	
}
