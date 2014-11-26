package cn.joymates.erp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.joymates.erp.dao.IUserDao1;
import cn.joymates.erp.dao.impl.UserDaoImpl1;
import cn.joymates.erp.domain.Resource;
import cn.joymates.erp.domain.Role;
import cn.joymates.erp.domain.User;
import cn.joymates.erp.utils.ResourceComparator;
/**
 * 登录服务
 * 
 * @author Jackie Hou
 */
public class LoginService {
	private static Log logger = LogFactory.getLog(LoginService.class);
	
	private IUserDao1 dao ;
	
	public LoginService() {
		dao = new UserDaoImpl1();
	}
	
	public User login(User user) {
//		IUserDao udao = (IUserDao)dao;
//		user = udao.findByUserId(user);
//		if (user == null) {
//			return null;
//		}
//		
//		//加载用户角色的权
//		Role r = new Role();
//		r.setRoleId(user.getRemark());
//		BaseDao<Role> dao1 = new BaseDaoImpl<Role>();
//		r = dao1.selectOne(r);
//		user.setRole(r);
//		//加载功能树
//		user.setResourceType(Resource.TREE);
//		user.setFuncTreeMap(boxing(udao.searchResourceUserId(user)));
		return user;
	}
	
	@Deprecated
	public User login(User user, String a) {
		Map<String, String> userMap = dao.findUserById(user);
		
		if (userMap == null) {
			logger.info("用户未找到！user.userId = " + user.getUserId());
			return null;
		}
		
		User u = new User();
		u.setUserLoginId(userMap.get("user_login_id"));
		u.setUserId(userMap.get("user_id"));
		
		List<Role> roleList = dao.findRole(u);
//		u.setRoleList(roleList);
//		u.setMaxRoleQuota(getMaxQuota(roleList));
		
		List<Resource> resourceList = dao.searchResourceByCode(u);
		
		//构造用户权限表
		Set<String> urlSet = new HashSet<String>();
		for (Resource r : resourceList) {
			if (StringUtils.isNotEmpty(r.getResourceUrl())) {
				urlSet.add(r.getResourceUrl());
			}
		}
		
//		u.setUrlSet(urlSet);
		
		//用户功能树
//		u.setResourceMap(boxing(resourceList));
		
		return u;
	}
	
	@Deprecated
	private Map<Resource, List<Resource>> boxing(List<Resource> list) {
		if (list == null || list.size() == 0) {
			logger.debug("未找到该用户的资源");
			return null;
		}
		
		//get function root
		List<Resource> rootKeys = new ArrayList<Resource>();
		for (Iterator<Resource> it1 = list.iterator(); it1.hasNext();) {
			Resource r = it1.next();
			if (r.getParentId() == null) {
				rootKeys.add(r);
				it1.remove();
			}
		}
		
		Collections.sort(rootKeys, new ResourceComparator());
		
		//set leaf node to root
		Map<Resource, List<Resource>> rootMap = new TreeMap<Resource, List<Resource>>(new ResourceComparator());
		for (Resource key : rootKeys) {
			List<Resource> leafList = new ArrayList<Resource>();
			for (Iterator<Resource> it2 = list.iterator(); it2.hasNext();) {
				Resource r = it2.next();
				if (key.getResourceId().equals(r.getParentId())) {
					leafList.add(r);
					it2.remove();
				}
				
			}
			
			//clear if no children
			if (leafList.isEmpty()) {
				continue;
			}
			
			Collections.sort(leafList, new ResourceComparator());
			rootMap.put(key, leafList);
		}
	
		return rootMap;
	}
	
	/**
	 * 获取用户最大权
	 * @param list
	 * @return
	 */
	private int getMaxQuota(List<Role> list) {
//		if (list == null || list.isEmpty()) {
//			return Role.MIN_ROLE_QUOTA;
//		}
//		
//		List<Integer> quotaList = new ArrayList<Integer>(list.size());
//		for (Role r : list) {
//			quotaList.add(r.getRoleQuota());
//		}
//		
//		int quota = Collections.max(quotaList);
//		return quota;
		return 0;
	}
}
