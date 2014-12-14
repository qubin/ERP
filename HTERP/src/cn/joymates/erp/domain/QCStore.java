package cn.joymates.erp.domain;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import cn.joymates.erp.domain.base.BaseQCStore;

public class QCStore extends BaseQCStore {
	public static final String LOGGOUT = "1";
	public static final String NOT_LOGGOUT = "0";
	public static Map<String, String> logoutMap = ImmutableMap.of(LOGGOUT, "注销",NOT_LOGGOUT, "未注销");
}
