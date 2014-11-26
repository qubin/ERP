package cn.joymates.erp.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cn.joymates.erp.dao.IUserDao;
import cn.joymates.erp.domain.Resource;
import cn.joymates.erp.domain.User;
import cn.joymates.erp.utils.MD5Utils;
import cn.joymates.erp.utils.db.SessionFactoryUtil;

public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {
	
	public User findByUserId(User user) {
		SqlSession sess = SessionFactoryUtil.getSession();
		String md5Psw = MD5Utils.GetMD5Code(user.getPassword1());
		user.setPassword1(md5Psw);
		return sess.selectOne("right.findByIdAndPassword", user);
	}
	
	public List<Resource> searchResourceUserId(User u) {
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectList("right.searchResourceByUserId", u);
	}
	
	public void insertUserRole(Map<String, String> uMap) {
		SqlSession sess = SessionFactoryUtil.getSession();
		sess.insert("insertUserRole", uMap);
	}

	@Override
	public String findRoleIdByUser(String userId) {
		SqlSession sess = SessionFactoryUtil.getSession();
		return sess.selectOne("selectRoleByUserId", userId);
	}
	
	
}
