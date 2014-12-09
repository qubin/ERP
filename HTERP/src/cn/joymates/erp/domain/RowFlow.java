package cn.joymates.erp.domain;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import cn.joymates.erp.domain.base.BaseRowFlow;

public class RowFlow extends BaseRowFlow {
	public static final String LOGGOUT = "1";
	public static final String NOT_LOGGOUT = "0";
	public static Map<String, String> logoutMap = ImmutableMap.of(LOGGOUT, "注销",NOT_LOGGOUT, "未注销");
}
