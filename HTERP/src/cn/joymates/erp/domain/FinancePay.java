package cn.joymates.erp.domain;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import cn.joymates.erp.domain.base.BasetFinancePay;

public class FinancePay extends BasetFinancePay {
	public static final String AUDIT = "0";
	public static final String PASS = "1";
	public static final String NOTPASS = "2";
	public static Map<String, String> STATUS = ImmutableMap.of(AUDIT,"未审核",PASS, "已审核", NOTPASS, "审核未通过");
}
