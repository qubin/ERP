package cn.joymates.erp.domain;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import cn.joymates.erp.domain.base.BaseCustPdct;

public class CustPdct extends BaseCustPdct {
	public static final String LOGOUT = "1";
	public static final String NOT_LOGOUT = "0";
	public static Map<String, String> logoutMap = ImmutableMap.of(LOGOUT, "注销",
			NOT_LOGOUT, "未注销");
}
