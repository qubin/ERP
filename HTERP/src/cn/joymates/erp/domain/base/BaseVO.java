package cn.joymates.erp.domain.base;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseVO {
	/**
	 * base
	 */
	protected String tablename = "";
	protected Map<String, String> fieldMap = new HashMap<>();
	protected String id;
	
	public String getTablename() {
		return tablename;
	}
	public Map<String, String> getFieldMap() {
		return fieldMap;
	}
	public String getId() {
		return id;
	}
	
	/**
	 * 注销
	 */
	public static final String LOGOUT = "1";
	public static final String NOT_LOGOUT = "0";
	
	public static final Map<String, String> LOGOUT_MAP = new HashMap<String, String>() {
		{
			put(LOGOUT, "注销");
			put(NOT_LOGOUT, "未注销");
		}
	};
}
