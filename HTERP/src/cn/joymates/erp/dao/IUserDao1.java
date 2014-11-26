package cn.joymates.erp.dao;

import java.util.List;
import java.util.Map;

import cn.joymates.erp.domain.Resource;
import cn.joymates.erp.domain.Role;
import cn.joymates.erp.domain.User;

public interface IUserDao1 {
	public Map<String, String> findUserById(User user);
	
	public List<Resource> searchResourceByCode(User user);
	
	public List<Role> findRole(User user);
}
