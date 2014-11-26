package cn.joymates.erp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * action基础�?
 * 
 * @author Jackie Hou
 *
 */
public class BaseAction extends ActionSupport {
	
	protected HttpServletRequest req = ServletActionContext.getRequest();
	protected HttpSession session = req.getSession();
	protected HttpServletResponse resp = ServletActionContext.getResponse();
	
	protected String ec_rd;

	public String getEc_rd() {
		return ec_rd;
	}

	public void setEc_rd(String ecRd) {
		ec_rd = ecRd;
	}

	
}
