package cn.joymates.erp.dao;

import java.util.List;
import java.util.Map;

import cn.joymates.erp.domain.Resource;
import cn.joymates.erp.domain.User;

public interface IUserDao extends BaseDao<User> {
	
	User findByUserId(User user);
	
	List<Resource> searchResourceUserId(User u);
	
	void insertUserRole(Map<String, String> uMap);
	
	String findRoleIdByUser(String userId);
	
	List findUserByName(Map<String, Object> map);
}
