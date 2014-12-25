package cn.joymates.erp.domain;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import cn.joymates.erp.domain.base.BaseQCStore;

public class QCStore extends BaseQCStore {
	public static final String MATERIAL = "1";
	public static final String PRODUCT = "2";
	public static Map<String, String> qcTypeMap = ImmutableMap.of(MATERIAL, "材料",PRODUCT, "成品");
}
