package cn.joymates.erp.utils;

import java.util.UUID;

/**
 * UUID utils
 * 
 * @author Jackie Hou
 *
 */
public class UUIDGenerator {
	
	/**
	 * 
	 * @return 32 character 's long
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-", "").toUpperCase();
	}
}
