package cn.joymates.erp.domain;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import cn.joymates.erp.domain.base.BaseProduct;

public class Product extends BaseProduct {
	
	public static final String LOGGOUT = "1";
	public static final String NOT_LOGGOUT = "0";
	public static Map<String, String> logoutMap = ImmutableMap.of(LOGGOUT, "注销",NOT_LOGGOUT, "未注销");
	
	public static final String FLAT = "1";
	public static final String TAB = "2";
	public static final String DRIFTLOCK = "3";
	public static Map<String, String> propertiesMap = ImmutableMap.of(FLAT, "flat",TAB, "tab",DRIFTLOCK,"drift lock");
	
	public static final String OE = "1";
	public static final String AF = "2";
	public static Map<String, String> marketMap = ImmutableMap.of(OE, "OE",AF, "AF");
	
	public static final String PROG = "1";
	public static final String SINGLEDIE = "2";
	public static final String PROGSINGLE = "3";
	public static Map<String, String> patternTypeMap = ImmutableMap.of(PROG, "prog",SINGLEDIE, "single die",PROGSINGLE,"prog + single");
}
