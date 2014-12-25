package cn.joymates.erp.domain;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import cn.joymates.erp.domain.base.BaseSellBill;

public class SellBill extends BaseSellBill {
	public static final String AUDIT = "0";
	public static final String PASS = "1";
	public static final String NOTPASS = "2";
	public static Map<String, String> STATUS = ImmutableMap.of(AUDIT,"未审核",PASS, "审核通过",
			NOTPASS, "审核未通过");
}
