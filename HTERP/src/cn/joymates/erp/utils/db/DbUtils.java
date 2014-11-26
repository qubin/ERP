package cn.joymates.erp.utils.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.ProxoolFacade;
import org.logicalcobwebs.proxool.admin.SnapshotIF;

/**
 * 获取数据库连接工具类
 * 
 * @author Jackie Hou
 *
 */
public class DbUtils {
	public static Log logger = LogFactory.getLog(DbUtils.class);
	
	private DbUtils() {
		throw new UnsupportedOperationException();
	}
	
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	
	/**
	 * 获取连接池连接数信息
	 */
	public static String getConnMsg(){
		SnapshotIF snapshot = null;
		StringBuffer str = new StringBuffer();
		
		try {
			snapshot = ProxoolFacade.getSnapshot("MySql", true);
			int curActiveCount=snapshot.getActiveConnectionCount();			
			int availableCount=snapshot.getAvailableConnectionCount();		
			int maxCount=snapshot.getMaximumConnectionCount() ;				
			str.append("活动连接数")
			.append(curActiveCount)
			.append("	可得到的连接数")
			.append(availableCount)
			.append("	允许最大连接数")
			.append(maxCount)
			.append("(max)");
		} catch (ProxoolException e) {
			//e.printStackTrace();
		} 
		
		return str.toString();
	}
	
	/**
	 * 手动获取数据库连
	 * 此方法用于手动获取数据库连接，没有与第三方的工具集成!
	 * 此方法没获取一次连接连接池就重新加载
	 * @return Connection
	 */
	public static Connection getConnectionWithoutServlet() throws SQLException {
		Connection conn = threadLocal.get();
		
		if (conn == null) {
			try {
				Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
				conn = DriverManager.getConnection("proxool.MySql5:com.mysql.jdbc.Driver:jdbc:mysql://localhost:3309/Blog", "root", "root");
				threadLocal.set(conn);
			} catch (ClassNotFoundException e) {
				logger.error("数据库连接池驱动未找到！");
			} catch (SQLException e) {
				logger.error("数据库连接获取失败");
				throw e;
			}
		}
		
		return conn;
	}
	
	/**
	 * 获取proxool连接池连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = threadLocal.get();
		
		if (conn == null) {
			try {
				Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
				conn = DriverManager.getConnection("proxool.MySql5");
				threadLocal.set(conn);
			} catch (ClassNotFoundException e) {
				logger.error("数据库连接池驱动未找到！");
			} catch (SQLException e) {
				logger.error("数据库连接获取失败");
				throw e;
			}
		}
		return conn;
	}
	
	public static void closeConnection() {
		Connection conn  = threadLocal.get();
		threadLocal.remove();
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeResultset(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void rollback() {
		Connection conn  = threadLocal.get();
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 其他方式获取Connection, 直接设置
	 * @param conn
	 */
	public static void setConnection(Connection conn) {
		threadLocal.set(conn);
	}
	
	public static void removeConnection() {
		threadLocal.remove();
	}
	
}
