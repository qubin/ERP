package cn.joymates.erp.domain;

import java.util.List;

import com.google.common.collect.ImmutableList;

import cn.joymates.erp.domain.base.BaseRole;

public class Role extends BaseRole {
	//共10级用户，0为最高级
	public static List<Integer> quotas = ImmutableList.of(
		10, 20, 30, 40, 50, 60, 70, 80, 90);
}
