package cn.joymates.erp.service;

import java.util.Date;
import java.util.List;

import cn.joymates.erp.dao.impl.RoleDaoImpl1;
import cn.joymates.erp.dao.impl.UserDaoImpl1;
import cn.joymates.erp.domain.Role;
import cn.joymates.erp.domain.User;
import cn.joymates.erp.utils.MD5Utils;
import cn.joymates.erp.utils.UUIDGenerator;

public class UserManageService {
	private UserDaoImpl1 dao = new UserDaoImpl1();
	
	public void addUser(User user) {
		//默认密码123456
		user.setPassword1(MD5Utils.GetMD5Code(User.DEFAULT_PASSWORD));
		user.setIsLogout(User.NOT_LOGOUT);
		user.setCreateTime(new Date());
		
		user.setUserId(UUIDGenerator.getUUID());
		dao.insert(user);
		
		List<Role> roles = null;
//		List<Role> roles = user.getRoleList();
		for (Role r : roles) {
			dao.insertUserRole(user.getUserId(), r.getRoleId());
		}
	}
	
//	public List findAll(String ecRd, HttpServletRequest req, User user) {
//		return dao.findAll(ecRd, req, user);
//	}
	
	public List<Role> getQuotaRoles(int quota) {
		RoleDaoImpl1 roledao = new RoleDaoImpl1();
		return roledao.findByQuota(quota);
	}
	
	public User findById(String id) {
		return dao.findById(id);
		 
	}
	
	public void modify(User user) {
		String logout = user.getIsLogout();
		
		if (logout.equals("true")) {
			user.setIsLogout(User.LOGOUT);
		} else {
			user.setIsLogout(User.NOT_LOGOUT);
			user.setLogoutReason("");
		}
		
		dao.update(user);
		List<Role> roles = null;
//		List<Role> roles = user.getRoleList();
		
		if (roles == null) {
			return;
		}
		dao.deleteUserRole(user);
		
		for (Role r : roles) {
			dao.insertUserRole(user.getUserId(), r.getRoleId());
		}
	}
	
	public void modifyPassword(User user) {
		String oldPwd = dao.findPwdByUserId(user.getUserId());
		
		if(!oldPwd.equals(MD5Utils.GetMD5Code(user.getPassword1()))) {
			dao.updatePassword(user);
		}
	}
	
}
