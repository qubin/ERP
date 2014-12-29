package cn.joymates.erp.domain;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import cn.joymates.erp.domain.base.BasePdceistct;

public class Pdceistct extends BasePdceistct {
	public static final String AUDIT = "1";
	public static final String NOT_AUDIT = "0";
	public static Map<String, String> auditMap = ImmutableMap.of(AUDIT, "已审核",NOT_AUDIT, "未审核");
	
	public static final String OVER = "1";
	public static final String NOT_OVER = "0";
	public static Map<String, String> overMap = ImmutableMap.of(OVER, "已结单",NOT_OVER, "未结单");
}

