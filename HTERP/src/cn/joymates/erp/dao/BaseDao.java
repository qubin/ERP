package cn.joymates.erp.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.joymates.erp.domain.base.BaseVO;

/**
 * 
 * @author Jackie Hou
 *
 * @param <T>
 */
public interface BaseDao<T extends BaseVO> {
	
	/**
	 * 根据对象查询
	 * @param ob 实体对象(封装查询条件，下同)
	 * @return 查询对象列表 
	 */
	public List<T> selectList(T ob);
	
	/**
	 * 根据对象查询 
	 * @param ob 
	 * @param lastsql 需要补充的sql(下同)
	 * @return
	 */
	public List<T> selectList(T ob, String lastsql);
	
	/**
	 * 根据对象主键查询(对象主键必须有值)
	 * @param ob
	 * @return
	 */
	public T selectOne(T ob);
	
	/**
	 * 根据对象插入
	 * @param ob
	 * @return
	 */
	public int save(T ob);
	
	/**
	 * 根据对象更新(对象主键必须有值,且不能将值更新为null)
	 * @param ob
	 * @return
	 */
	public void update(T ob) ;
	
	/**
	 * 根据对象删除(对象主键必须有值)
	 * @param ob
	 * @return
	 */
	public void delete(T ob);	
	
	/**
	 * 根据对象获取分页列表
	 * @param ec_rd  每页记录条数（默认12条）
	 * @param ob  实体对象(封装查询条件)
	 * @param req （页面request请求）
	 * @return
	 */
	
	public List<Map<String, Object>> getEcsideList(String ec_rd, T ob,HttpServletRequest req);
	
	/**
	 * 根据对象获取分页列表
	 * @param ec_rd 每页记录条数（默认12条）
	 * @param ob    实体对象(封装查询条件)
	 * @param lastsql （需要补充的sql）
	 * @param req  （页面request请求）
	 * @return
	 */	
	public List<Map<String, Object>> getEcsideList(String ec_rd, T ob, String lastsql, HttpServletRequest req);
	
	
	/**
	 * 获取分页列表(需要拼接sql语句只供dao调用，不在service层出现)
	 * @param ec_rd       每页记录条数（默认12条）
	 * @param searchsql  (获取总条数的sql)
	 * @param resultsql  (获取结果集的sql)
	 * @param req        （页面request请求）
	 * @return
	 */
	public List<Map<String, Object>> getEcsideList(String ec_rd, String searchsql, String resultsql, HttpServletRequest req);
	
	/**
	 * 联合查询
	 * @param ec_rd		 	
	 * @param selectItem select语句除select本身
	 * @param orderItem	 按照哪个字段排序，字段名	
	 * @param fromItem	 from语句 除from本身
	 * @param whereItem	 where语句 除where本身
	 * @param req		HttpServletRequest
	 * @return
	 */
	public List<Map<String, Object>> getComplexEcsideList(String ec_rd, String selectItem, String orderItem,
			String fromItem, String whereItem ,HttpServletRequest req);
	
	public List<Map<String, Object>> getComplexEcsideList(String ec_rd, String selectItem,
			String orderItem, String fromItem, HttpServletRequest req);
	
	@Deprecated
	public List<Map<String, Object>> getEcsideListForObj(String ec_rd, List list, HttpServletRequest req);
	
	/**
	 * ecside分页。自定义排序的字段
	 * @param ec_rd
	 * @param ob
	 * @param lastsql
	 * @param req
	 * @param sortField  要排序的字段
	 * @return
	 */
	@Deprecated
	public List<Map<String, Object>> getEcsideList(String ec_rd, T ob, String lastsql,
			HttpServletRequest req, String sortField);
	
}	