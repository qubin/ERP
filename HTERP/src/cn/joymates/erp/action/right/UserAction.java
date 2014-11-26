package cn.joymates.erp.action.right;

import java.util.List;
import java.util.Map;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.annotations.ErpUsing;
import cn.joymates.erp.domain.Role;
import cn.joymates.erp.domain.User;
import cn.joymates.erp.service.RoleService;
import cn.joymates.erp.service.UserService;
import cn.joymates.erp.utils.ServiceProxyFactory;

/**
 * 用户action
 * 
 * @author Jackie Hou
 *
 */
public class UserAction extends BaseAction {
	
	@ErpUsing
	public String login() {
		User u = userService.login(user);
		
		if (u == null) {
			return "fail";
		}
		
		session.setAttribute("loggedUser", u);
		return SUCCESS;
	}
	
	@ErpUsing
	public String logout() {
		session.removeAttribute("loggedUser");
		return SUCCESS;
	}
	
	public String showHome() {
		return "showHomeUI";
	}
	
	public String find() {
		if (user == null) {
			user = new User();
		}
		
//		user.setIsLogout(User.NOT_LOGOUT);
		UserService service = ServiceProxyFactory.getInstanceNoMybatis(new UserService());
		List<Map<String, Object>> userList =  service.find(ec_rd, user, req);
		
		req.setAttribute("userList", userList);
		req.setAttribute("logoutMap", User.LOGOUT_MAP);
//		req.setAttribute("userTypeMap", User.userTypeMap);
		return "showHomeUI";
	}
	
	public String showAddUI() {
		RoleService rsrv = ServiceProxyFactory.getInstance(new RoleService());
		Role r = new Role();
		r.setIsLogout(Role.NOT_LOGOUT);
		
		User u1 = (User)session.getAttribute("loggedUser");
		String lastsql = " and this_.role_qutoa>" + u1.getRole().getRoleQuota();
		List<Role> roleList = rsrv.selectList(r, lastsql);
		
		req.setAttribute("roleList", roleList);
//		req.setAttribute("userTypeMap", User.userTypeMap);
		return "showAddUI";
	}
	
	public String add() {
		user.setCreatePerson(((User)session.getAttribute("loggedUser")).getUserLoginId());
		userService.saveUser(user);
		return "showHomeUI";
	}
	
	public String modifyUI() {
		user = userService.getOneUser(user, req);
		return "modifyUI";
	}
	
	public String modify() {
		userService.modify(user);
		return "showHomeUI";
	}
	
	private UserService userService = ServiceProxyFactory.getInstance(new UserService());
	
	private User user;
	
	private String validateCode;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	
}
