package cn.joymates.erp.action.right;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.User;
import cn.joymates.erp.service.UserService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class PasswordAction extends BaseAction {
	public String modifyUI() {
		return "home";
	}
	
	public String modify() {
		User u = (User)session.getAttribute("loggedUser");
		UserService srv = ServiceProxyFactory.getInstanceNoMybatis(new UserService());
		String msg = srv.modifyPassword(u.getUserId(), password[0], password[1]);
		req.setAttribute("msg", msg);
		return "home";
	}
	
	private String[] password;

	public String[] getPassword() {
		return password;
	}

	public void setPassword(String[] password) {
		this.password = password;
	}
	
	
}

