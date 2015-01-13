package cn.joymates.erp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import cn.joymates.erp.dao.BaseDao;
import cn.joymates.erp.dao.IResourceDao;
import cn.joymates.erp.dao.IRoleDao;
import cn.joymates.erp.dao.impl.BaseDaoImpl;
import cn.joymates.erp.dao.impl.ResourceDaoImpl1;
import cn.joymates.erp.dao.impl.RoleDaoImpl;
import cn.joymates.erp.domain.Resource;
import cn.joymates.erp.domain.Role;

public class RoleService extends BaseService<Role> {
	public RoleService() {
		dao = new RoleDaoImpl();
	}
	
	public void showResources(Role role, Resource resource, String ec_rd, HttpServletRequest req) {
		//已授权数据
		IRoleDao roleDao = new RoleDaoImpl();
		List<Resource> authedList = roleDao.findResouceByRoleId(role.getRoleId());
		
		List<String> authedStringList = new ArrayList<>(authedList.size());
		for (Resource re : authedList) {
			authedStringList.add(re.getResourceId());
		}
		req.setAttribute("authedResList", authedStringList);
		
		//全部数据
		BaseDao<Resource> resdao = new BaseDaoImpl<>();
		List<Map<String, Object>> allReses = null;
		
		//用户选择过滤
		if (resource != null && StringUtils.isNotEmpty(resource.getParentId())) {
			String lastsql = " and this_.parent_id like'" + resource.getParentId() + "%' " +
							 " or  this_.resource_id='" + resource.getParentId() + "' ";
			allReses = resdao.getEcsideList(ec_rd, new Resource(), lastsql, req);
			
		} else {
			allReses = resdao.getEcsideList(ec_rd, new Resource(), req);
		}
		
		req.setAttribute("resourceList", allReses);
		
		//过滤树
		Resource r1 = new Resource();
//		r1.setResourceType(Resource.TREE);
		String filterSql = " and this_.parent_id is null";
		List<Resource> allRoots = resdao.selectList(r1, filterSql);
		Map<String, String> filterMap = new HashMap<>(allRoots.size());
		
		for (Resource re : allRoots) {
			filterMap.put(re.getResourceId(), re.getResourceTitle());
		}
		req.setAttribute("filterMap", filterMap);
		
	}
	
	public void auth(Role r, String resourceIds) {
		IRoleDao dao1 = (IRoleDao)dao;
		dao1.deleteById(r);
		Map<String, String> map = new HashMap<>();
		
		if (StringUtils.isEmpty(resourceIds)) {
			return;
		}
		
		for (String s : resourceIds.split(",")) {
			map.put("role_id", r.getRoleId());
			map.put("resource_id", s);
			dao1.saveResPrivilege(map);
		}
	
	}
	
	public Role findById(String id) {
		Role r = new Role();
		r.setRoleId(id);
		return dao.selectOne(r);
	}
	
	public Boolean findRoleByName(String name){
		Boolean result=false;
		IRoleDao dao1 =  new RoleDaoImpl();
		Map <String,Object> map=new HashMap<String,Object>();
		map.put("roleName", name);
		List list=dao1.findRoleByName(map);
		if(list!=null && list.size()>0){
			result=true;
		}
		return result;
	}
	
	public void modifyRole(Role role) {
		String logout = role.getIsLogout(); 
		if ("false".equals(logout)) {
			role.setIsLogout(Role.NOT_LOGOUT);
			role.setLogoutReason("");
		} else {
			role.setIsLogout(Role.LOGOUT);
		}
		dao.update(role);
	}
	
	public Map<String, List<Resource>> getAuthData(String roleid) {
		Map<String, List<Resource>> resultMap = new HashMap<String, List<Resource>>();
		ResourceDaoImpl1 resourceDao =  new ResourceDaoImpl1();
		resultMap.put("all", resourceDao.selectAll());
		resultMap.put("mine", resourceDao.selectResourceByRoleId(roleid));
		
		return resultMap;
	}
}
