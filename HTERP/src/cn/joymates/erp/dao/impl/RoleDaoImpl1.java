package cn.joymates.erp.dao.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import cn.joymates.erp.dao.IRoleDao1;
import cn.joymates.erp.domain.Role;
import cn.joymates.erp.domain.User;
import cn.joymates.erp.utils.db.SessionFactoryUtil;

public class RoleDaoImpl1 implements IRoleDao1 {
	public void save(Role role) {
		SqlSession sess = SessionFactoryUtil.getSession();
		sess.insert("basic.insertRole", role);
	}
	
	/**
	 * 查询当前登录用户管理权以下的角色。
	 * admin不能查出super、和他自己的角色
	 * @param ecRd
	 * @param req
	 * @param role
	 * @return
	 */
//	public List<Role> findAll(String ecRd, HttpServletRequest req, Role role) {
//		//Get max quota of current loggin user
//		HttpSession session = req.getSession();       
//		User loggedUser = (User)session.getAttribute("loggedUser");
//		
//		int quota = loggedUser.getMaxRoleQuota();
//		
//		DBOperationDAO bdao = DBOperationDAO.getInstance();
//		
//		StringBuilder cond = new StringBuilder(" and (role_quota >" + quota + 
//				" or role_quota is null ) or create_person='" + loggedUser.getUserId() + "' ");
//		if (role.getRoleId() != null  && !"".equals(role.getRoleId().trim())) {
//			cond.append(" and role_id='" + role.getRoleId() +"' ");
//		}
//		
//		if (role.getRoleName() != null && !"".equals(role.getRoleName().trim())) {
//			cond.append(" and role_name='" + role.getRoleName() + "' ");
//		}
//		
//		String searchsql = "select count(1) from role_tb where 1=1" + cond.toString();
//		String resultsql = "select * from role_tb where 1=1 " + 
//							cond.toString() + " limit ?, ? ";
//		return bdao.getEcsideList(ecRd, searchsql, resultsql, req);
//	}
	
	public Role findById(String id) {
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectOne("basic.findRoleById", id);
	}
	
	public void update(Role role) {
		SqlSession sess = SessionFactoryUtil.getSession();
		sess.update("basic.updateRole", role);
	}
	
	public List<Role> findByQuota(Integer quota) {
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectList("findByQuota", quota);
	}
}
