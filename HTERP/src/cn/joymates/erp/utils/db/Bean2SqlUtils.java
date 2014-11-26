package cn.joymates.erp.utils.db;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.joymates.erp.domain.base.BaseVO;

public class Bean2SqlUtils {
	
	public static Log logger = LogFactory.getLog(Bean2SqlUtils.class);
	
	public static <T extends BaseVO> String getSelectSql(T ob) {
		StringBuilder sqlb = new StringBuilder("select *");
		
		sqlb.append(" from ")
			.append(ob.getTablename())
			.append(" as this_ where 1=1");
		
		Map<String, String> vmap = getValueMap(ob);
		for (Map.Entry<String, String> entry : vmap.entrySet()) {
			sqlb.append(" and ")
				.append(entry.getKey())
				.append("=")
				.append(entry.getValue());
		}
		
		return sqlb.toString();
	}
	
	public static <T extends BaseVO> String getSelectSql(T ob, String lastsql) {
		return getSelectSql(ob) + lastsql;
	}
	
	public static <T extends BaseVO> String getSelectOneSql(T ob) {
		StringBuilder sqlb = new StringBuilder("select *");
		
		sqlb.append(" from ")
			.append(ob.getTablename())
			.append(" as this_ where 1=1");
		
		Map<String, String> vmap = getValueMap(ob);
		sqlb.append(" and ")
			.append("this_." + ob.getId())
			.append("=")
			.append(vmap.get(ob.getId()));
		
		return sqlb.toString();
	}
	
	public static <T extends BaseVO> String getInsertSql(T ob) {
		StringBuilder sqlb = new StringBuilder();
		sqlb.append("insert into ")
			.append(ob.getTablename());
		
		StringBuilder part1 = new StringBuilder();
		StringBuilder part2 = new StringBuilder();
		Map<String, String> insertMap = getValueMap(ob);
		
		for (Map.Entry<String, String> entry : insertMap.entrySet()) {
			part1.append(entry.getKey())
				 .append(",");
			part2.append(entry.getValue())
				 .append(",");
			
		}
		
		part1.delete(part1.length() -1, part1.length());
		part2.delete(part2.length() -1, part2.length());
		
		sqlb.append("(" + part1 + ") values(" + part2 + ")");
		return sqlb.toString();
	}
	
	public static <T extends BaseVO> String getUpdateSql(T ob) {
		String sql = " update " + ob.getTablename();
		StringBuilder wherePart = new StringBuilder(" where ");
		
		Map<String, String> vmap = getValueMap(ob);
		wherePart.append(ob.getId())
			.append("=")
			.append(vmap.get(ob.getId()));	
		vmap.remove(ob.getId());
		
		StringBuilder setPart = new StringBuilder(" set ");
		for (Map.Entry<String, String> entry : vmap.entrySet()) {
			setPart.append(entry.getKey())
				   .append("=")
				   .append(entry.getValue())
				   .append(",");
			
		}
		
		setPart.delete(setPart.length() -1, setPart.length());
		
		sql += setPart.append(wherePart);
		return sql;
	}
	
	public static <T extends BaseVO> String getDeleteSql(T ob) {
		String sql = "delete from " + ob.getTablename();
		
		StringBuilder wherePart = new StringBuilder(" where ");
		Map<String, String> vmap = getValueMap(ob);
		wherePart.append(ob.getId())
				 .append("=")
				 .append(vmap.get(ob.getId()));	
		
		return sql + wherePart.toString();
	}
	
	private static <T extends BaseVO> Map<String, String> getValueMap(T t) {
		Map<String, String> valueMap = new HashMap<>();
		
		try {
			Class<?> superClass = t.getClass().getSuperclass();
			
			BeanInfo bi = Introspector.getBeanInfo(superClass, superClass.getSuperclass());
			PropertyDescriptor[] pds = bi.getPropertyDescriptors();   //bean的属性
			
			Map<String, String> fieldMap = t.getFieldMap();		  //bean中定义的映射
			for (PropertyDescriptor pd : pds) {
				Method method = pd.getReadMethod();
				
				Object retVal = method.invoke(t); //obj.getXXXX();
				String columnName = "";
				
				String methodName = method.getName();
				String firstLetter = methodName.substring(3, 4).toLowerCase();
				columnName = firstLetter + methodName.substring(4);
				columnName = fieldMap.get(columnName);
				
				if (retVal != null && !retVal.equals("")) {
					String returnTypeName = method.getReturnType().getName();
					
					switch(returnTypeName) {
						case "java.util.Date" :
							valueMap.put(columnName, "'" + ((Date)retVal).toLocaleString() + "'");
							break;
						case "java.lang.Integer" :
						case "java.math.BigDecimal" :
							valueMap.put(columnName, retVal.toString());
							break;
						default :
							valueMap.put(columnName, "'" + retVal.toString() + "'");
							break;
					}
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return valueMap;
	}
	
	public static <T extends BaseVO> List<T> reverse(T t, ResultSet rs) {
		List<T> list = new ArrayList<>();
		
		try {
			while (rs.next()) {
				t = (T)t.getClass().newInstance();
				Map<String, String> fieldMap = t.getFieldMap();
			
				Class<?> superClass = t.getClass().getSuperclass();
				BeanInfo bi = Introspector.getBeanInfo(superClass, superClass.getSuperclass());
				PropertyDescriptor[] pds = bi.getPropertyDescriptors();

				for (PropertyDescriptor pd : pds) {
					Method method = pd.getWriteMethod();
					String methodName = method.getName();
					
					String j = methodName.substring(3, 4).toLowerCase();
					methodName = j + methodName.substring(4);
					String columnName = fieldMap.get(methodName);
					method.invoke(t, rs.getObject(columnName));
				}
				
				list.add(t);
			}
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 根据查询结果，获取查询列名
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static List<String> getColumnName(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();
		int cols = metaData.getColumnCount();
		List<String> columnName = new ArrayList<>(cols);
		
		for (int i=0; i<cols; i++){
			columnName.add(metaData.getColumnName(i+1).toUpperCase()) ;
		}
		return columnName;
	}
	
	/**
	 * 组装结果集List<Map<String, Object>>
	 * @param rs
	 * @param columnName
	 * @param map
	 * @throws SQLException
	 */
	public static void buildRecord(ResultSet rs, String[] columnName, 
			Map<String, Object> map) throws SQLException {
		
		for (int i=0; i<columnName.length; i++){
			Object result = null;
			
			if (rs.getString(columnName[i]) != null) {
				String type = rs.getMetaData().getColumnTypeName(i + 1);
				
				if (type.indexOf("DECIMAL") >= 0 || type.indexOf("INT") >= 0 //未排序
						|| type.indexOf("DOUBLE") >= 0
						|| type.indexOf("FLOAT") >= 0) {
					result = new BigDecimal(rs.getString(columnName[i]));
				} else {
					result = rs.getString(columnName[i]);
				}
			}
			
			map.put(columnName[i], result);
			
		}
	}
	
}
