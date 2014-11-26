package cn.joymates.erp.utils;

import java.util.Comparator;

import cn.joymates.erp.domain.Resource;

/**
 * 资源比较器
 * 
 * @author Jackie Hou
 *
 */
public class ResourceComparator implements Comparator<Resource> {

	@Override
	public int compare(Resource r1, Resource r2) {
		String id1 = r1.getResourceId();
		String id2 = r2.getResourceId();
		return id1.compareTo(id2);
	}
	
}
