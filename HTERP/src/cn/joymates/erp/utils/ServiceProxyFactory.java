package cn.joymates.erp.utils;

import java.lang.reflect.Method;
import java.sql.Connection;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import cn.joymates.erp.utils.db.DbUtils;
import cn.joymates.erp.utils.db.SessionFactoryUtil;

/**
 * Blog服务代理工厂
 * 
 * @author Jackie Hou
 *
 */
public class ServiceProxyFactory {
	
	/**
	 * 由mybatis获取数据库连接的代理
	 * @param target
	 * @return
	 */
    public static <T> T getInstance(T target) {  
    	Enhancer enhancer = preCondition(target);  
        
        enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] args,
					MethodProxy mProxy) throws Throwable {
				Method[] methods = Object.class.getDeclaredMethods();
				
				for (Method m : methods) {
					if (m.getName().equals(method.getName())) {
						return mProxy.invokeSuper(obj, args);
					}
				}
				
				Object retObj = null;
				SqlSession sess = SessionFactoryUtil.getSession();
				
				try {
					System.out.println(obj.getClass().getSimpleName() + "." + method.getName());
					DbUtils.setConnection(sess.getConnection());
					retObj = mProxy.invokeSuper(obj, args);
					sess.commit();
					
				} catch (Exception e) {
					e.printStackTrace();
					sess.rollback();
				} finally {
					DbUtils.removeConnection();
					SessionFactoryUtil.closeSession();
				}
				
				return retObj;
			}
        	
        });  
        return (T)enhancer.create();  
    }
    
    /**
     * 直接从连接池获取连接的代理
     * @param target
     * @param noMybatis
     * @return
     */
    public static <T> T getInstanceNoMybatis(T target) {  
    	Enhancer enhancer = preCondition(target);  
        
        enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] args,
					MethodProxy mProxy) throws Throwable {
				Object retObj = null;
				Connection conn = null;
				Method[] methods = Object.class.getDeclaredMethods();
				
				for (Method m : methods) {
					if (m.getName().equals(method.getName())) {
						return mProxy.invokeSuper(obj, args);
					}
				}
				
				try {
					conn = DbUtils.getConnection();
					conn.setAutoCommit(false);
					
					retObj = mProxy.invokeSuper(obj, args);
					conn.commit();
				} catch (Exception e) {
					e.printStackTrace();
					conn.rollback();
				} finally {
					DbUtils.closeConnection();
				}
				
				return retObj;
			}
        	
        });  
        return (T)enhancer.create();  
    }
    
    private static <T> Enhancer preCondition(T target) {
		String packageName = target.getClass().getPackage().getName();
    	
    	if (StringUtils.isEmpty(packageName) || 
    			!"service".equals(StringUtils.substringAfterLast(packageName, "."))) {
    		return null;
    	}
    	
        Enhancer enhancer = new Enhancer();  
        enhancer.setSuperclass(target.getClass());
		return enhancer;
	}
}
