package cn.joymates.erp.domain;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import cn.joymates.erp.domain.base.BasePdctFlow;

public class PdctFlow extends BasePdctFlow {
	public static final String LOGOUT = "1";
	public static final String NOT_LOGOUT = "0";
	public static final String IN = "2";
	public static final String OUT = "1";
	public static Map<String, String> logoutMap = ImmutableMap.of(LOGOUT, "注销",
			NOT_LOGOUT, "未注销");
	public static Map<String, String> inOuOut = ImmutableMap.of(IN, "入库",OUT, "出库");
}
