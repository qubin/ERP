package cn.joymates.erp.domain;

import java.util.List;
import java.util.Map;

import cn.joymates.erp.domain.base.BaseUser;

public class User extends BaseUser {
	
	public static final String DEFAULT_PASSWORD = "123456";
	
	private String loginType;
	
	/**
	 * 当前使用模块
	 */
	private String currentModule;
	
	/**
	 * 资源类型
	 */
	private String resourceType;
	
	/**
	 * 功能树map
	 */
	private Map<Resource, List<Resource>> funcTreeMap;
	
	private Role role;

	public Map<Resource, List<Resource>> getFuncTreeMap() {
		return funcTreeMap;
	}

	public void setFuncTreeMap(Map<Resource, List<Resource>> funcTreeMap) {
		this.funcTreeMap = funcTreeMap;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	
	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getCurrentModule() {
		return currentModule;
	}

	public void setCurrentModule(String currentModule) {
		this.currentModule = currentModule;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
