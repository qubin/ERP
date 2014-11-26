package cn.joymates.erp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import cn.joymates.erp.dao.BaseDao;
import cn.joymates.erp.dao.IUserDao;
import cn.joymates.erp.dao.impl.BaseDaoImpl;
import cn.joymates.erp.dao.impl.UserDaoImpl;
import cn.joymates.erp.domain.Resource;
import cn.joymates.erp.domain.Role;
import cn.joymates.erp.domain.User;
import cn.joymates.erp.domain.UserRole;
import cn.joymates.erp.utils.UUIDGenerator;

public class UserService  extends BaseService<User> {
	public UserService() {
		dao = new UserDaoImpl();
	}
	
	public String modifyPassword(String userId, String oldPwd, String newPwd) {
		String msg = "";
		User user = new User();
		user.setUserId(userId);
		user = dao.selectOne(user);
		
		if (!oldPwd.equals(user.getPassword1())) {
			msg = "原始密码输入错误";
			
		} else if (!oldPwd.equals(newPwd)) {
			
			User u1 = new User();
			u1.setUserId(user.getUserId());
			u1.setPassword1(newPwd);
			dao.update(u1);
			
			msg = "密码修改成功！";
		}
		
		return msg;
	}
	
	public void modify(User user) {
		dao.update(user);
		if (User.LOGOUT.equals(user.getIsLogout())) {
			return;
		}
		
		BaseDao<UserRole> dao1 = new BaseDaoImpl<>();
		UserRole ur = new UserRole();
		ur.setUserId(user.getUserId());
		dao1.delete(ur);
		
		ur.setRoleId(user.getRole().getRoleId());
		dao1.save(ur);
	}
	
	public void saveUser(User user) {
		String uuid = UUIDGenerator.getUUID();
		user.setUserId(uuid);
		user.setPassword1("123456"); //default password
		
		user.setCreateTime(new Date());
		user.setIsLogout(User.NOT_LOGOUT);
		dao.save(user);
		
		Map<String, String> urMap = new HashMap<>();
		urMap.put("role_id", user.getRole().getRoleId());
		urMap.put("user_id", uuid);
		IUserDao dao1 = (IUserDao)dao;
		dao1.insertUserRole(urMap);
		
	}
	
	public User getOneUser(User u, HttpServletRequest req) {
		u = dao.selectOne(u);
		
		IUserDao dao1 = (IUserDao)dao;
		String roleId = dao1.findRoleIdByUser(u.getUserId());
		
		Role urole = new Role();
		urole.setRoleId(roleId);
		u.setRole(urole);
		
		BaseDao<Role> rdao = new BaseDaoImpl<Role>();
		Role r = new Role();
		r.setIsLogout(Role.NOT_LOGOUT);
		
		User u1 = (User)req.getSession().getAttribute("loggedUser");
		String lastsql = " and this_.role_qutoa>" + u1.getRole().getRoleQuota();
		List<Role> roleList = rdao.selectList(r, lastsql);
		req.setAttribute("roleList", roleList);
		return u;
	}
	
	/**
	 * 查询当前用户级别以下的用户
	 * @param ecRd
	 * @param user
	 * @param req
	 * @return
	 */
	public List<Map<String, Object>> find(String ecRd, User user, HttpServletRequest req) {
		//获取当前用户级别
		User loggedUser = (User)req.getSession().getAttribute("loggedUser");
		String cond = " and r.role_qutoa>" + loggedUser.getRole().getRoleQuota();
		
		if (StringUtils.isNotEmpty(user.getUserLoginId())) {
			cond += " and u.user_login_id like '%" + user.getUserLoginId() + "%' ";
		}
		
		if (StringUtils.isNotEmpty(user.getRole().getRoleName())) {
			cond += " and r.role_name like '%" + user.getRole().getRoleName() + "%' ";
		}
		
		String countSql = "select count(1) " + 
						  "from tb_user1 u, tb_user_role ur, tb_role r " +
						  " where u.user_id = ur.user_id and ur.role_id = r.role_id" + cond;
		String resultSql = "select u.user_id, u.User_login_id, u.create_time, u.is_logout, " +
					       " u.remark, u.is_student, r.role_id, r.role_name " +
					       " from tb_user1 u, tb_user_role ur, tb_role r " +
					       " where u.user_id = ur.user_id and ur.role_id = r.role_id " + 
					       cond + " limit ?, ?";
		
		return dao.getEcsideList(ecRd, countSql, resultSql, req);
	}
	
	public User login(User user) {
		IUserDao udao = (IUserDao)dao;
		user = udao.findByUserId(user);
		if (user == null) {
			return null;
		}
		
		//加载用户角色的权
		Role r = new Role();
		r.setRoleId(user.getRemark());
		BaseDao<Role> dao1 = new BaseDaoImpl<Role>();
		r = dao1.selectOne(r);
		user.setRole(r);
		
		//加载功能树
		user.setFuncTreeMap(boxing(udao.searchResourceUserId(user)));
		return user;
	}
	
	/**
	 * 查找用户对应功能点
	 * @param user
	 * @return
	 */
	public List<Resource> getFuncResource(User user) {
		IUserDao udao = (IUserDao)dao;
		return udao.searchResourceUserId(user);
	}
	
	private Map<Resource, List<Resource>> boxing(List<Resource> list) {
		if (list == null || list.size() == 0) {
			return null;
		}
		
		//get function root
		List<Resource> rootNodeList = new ArrayList<>();
		for (Iterator<Resource> it1 = list.iterator(); it1.hasNext();) {
			Resource r = it1.next();
			if (r.getParentId() == null) {
				rootNodeList.add(r);
				it1.remove();
			}
		}
		
		Collections.sort(rootNodeList);
		
		//set leaf node to root
		Map<Resource, List<Resource>> rootNodeMap = new TreeMap<>();
		for (Resource key : rootNodeList) {
			List<Resource> leafList = new ArrayList<>();
			
			for (Iterator<Resource> it2 = list.iterator(); it2.hasNext();) {
				Resource r = it2.next();
				if (key.getResourceId().equals(r.getParentId())) {
					leafList.add(r);
					it2.remove();
				}
				
			}
			
			Collections.sort(leafList);
			rootNodeMap.put(key, leafList);
		}
	
		return rootNodeMap;
	}
}
