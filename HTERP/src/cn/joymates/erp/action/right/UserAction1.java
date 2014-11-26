package cn.joymates.erp.action.right;

import java.util.ArrayList;
import java.util.List;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Role;
import cn.joymates.erp.domain.User;
import cn.joymates.erp.service.UserManageService;
import cn.joymates.erp.utils.MD5Utils;
import cn.joymates.erp.utils.ServiceProxyFactory;

/**
 * 用户管理action
 * 
 * @author Jackie Hou
 *
 */
@Deprecated
public class UserAction1 extends BaseAction {
//	public String find() {
//		userList = userManageService.findAll(ec_rd, req, user);
//		req.setAttribute("logout", User.LOGOUT_MAP);
//		return "find";
//	}
	
	public String add() {
		User u = (User)session.getAttribute("loggedUser");
		user.setCreatePerson(u.getUserId());
		
		//construct user data struct.
		Role r = new Role();
		for (String s :roleUuids) {
			r.setRoleId(s);
		}
		
		List<Role> roleList = new ArrayList<Role>();
		roleList.add(r);
//		user.setRoleList(roleList);
		userManageService.addUser(user);
		return "find";
	}
	
	public String showModifyPage() {
		user = userManageService.findById(user.getUserId());
		List<Role> rlist = null;
//		List<Role> rlist = user.getRoleList();
		List<String> rslist = new ArrayList<String>(rlist.size());
		
		for (Role r : rlist) {
			rslist.add(r.getRoleId());
		}
		
		roleUuids = rslist.toArray(new String[rslist.size()]);
		
		//回显注销
		String logout = user.getIsLogout();
		user.setIsLogout("true");
		if (logout.equals(User.NOT_LOGOUT)) {
			user.setIsLogout("false");
		}
		return "modifyUI";
	}
	
	public String modify() {
		//自己不能给自己分别角�?
		if (roleUuids != null) {
			Role r = new Role();

			for (String s :roleUuids) {
				r.setRoleId(s);
			}
			
			List<Role> roleList = new ArrayList<Role>();
			roleList.add(r);
//			user.setRoleList(roleList);
		}
		
		userManageService.modify(user);
		return "find";
	}
	
	public String pwdModify() {
		User sessU = (User)session.getAttribute("loggedUser");
		String[] pwds = user.getPassword1().split(",");
		
		if (!pwds[0].trim().equals(pwds[1].trim())) {
			user = new User();
			user.setUserId(sessU.getUserId());
			user.setPassword1(MD5Utils.GetMD5Code(pwds[1].trim()));
			userManageService.modifyPassword(user);
		}
		
		req.setAttribute("flag", "1");
		return "pwdOver";
	}
	
	UserManageService userManageService = ServiceProxyFactory.getInstance(new UserManageService());
	
	private User user;
	
	private String[] roleUuids;
	
	private List userList;
	
	public String[] getRoleUuids() {
		return roleUuids;
	}

	public void setRoleUuids(String[] roleUuids) {
		this.roleUuids = roleUuids;
	}

	public List getUserList() {
		return userList;
	}

	public void setUserList(List userList) {
		this.userList = userList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
