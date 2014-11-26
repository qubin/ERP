package cn.joymates.erp.action.dwr;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;

import cn.joymates.erp.domain.Role;
import cn.joymates.erp.domain.User;
import cn.joymates.erp.utils.db.SessionFactoryUtil;

/**
 * 单一用户
 * 
 * @author Jackie Hou
 *
 */
public class SingleUserRole {
	/**
	 * 查询已经存在的用户Id
	 * @param userId
	 * @param userCode
	 * @return
	 */
	public int getExistUserCount(String userId, String userCode) {
		if (StringUtils.isEmpty(userId) || StringUtils.isBlank(userId)) {
			return 99;
		}
		
		SqlSession sess = SessionFactoryUtil.getSession();
		User u = new User();
//		u.setUserCode(userCode);
		u.setUserId(userId);
		
		int count =  sess.selectOne("basic.findUserByUserId", u);
		SessionFactoryUtil.closeSession();
		return count;
	}
	
	/**
	 * 查询已经存在的角色
	 * @param roleName
	 * @param roleUuid
	 * @return
	 */
	public int getExistRoleCount(String roleName, String roleUuid) {
		if (StringUtils.isEmpty(roleName) || StringUtils.isBlank(roleName)) {
			return 99;
		}
		
		SqlSession sess = SessionFactoryUtil.getSession();
		Role r = new Role();
		r.setRoleName(roleName);
		r.setRoleId(roleUuid);
		
		int count =  sess.selectOne("basic.findRoleByRoleName", r);
		SessionFactoryUtil.closeSession();
		return count;
	}
}
