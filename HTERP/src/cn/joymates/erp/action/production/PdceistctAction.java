package cn.joymates.erp.action.production;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mysql.jdbc.ResultSetMetaData;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Material;
import cn.joymates.erp.domain.Pdceistct;
import cn.joymates.erp.exceptions.DaoException;
import cn.joymates.erp.service.MaterialService;
import cn.joymates.erp.service.PdceistctService;
import cn.joymates.erp.service.ProductService;
import cn.joymates.erp.utils.ServiceProxyFactory;
import cn.joymates.erp.utils.db.DbUtils;

public class PdceistctAction extends BaseAction {

	public String showHome() {
		if (pdceistct == null) {
			pdceistct = new Pdceistct();
		}
		List<Map<String, Object>> pdceistctList = service.find(pdceistct,pdceistct_key, pdceistct_name, ec_rd, req);
		req.setAttribute("pdceistctList", pdceistctList);
		req.setAttribute("logoutMap", Pdceistct.auditMap);
		return "home";
	}
	//查询
	public String find() {
		if (pdceistct == null) {
			pdceistct = new Pdceistct();
		}
		List<Map<String, Object>> pdceistctList = service.find(pdceistct,pdceistct_key, pdceistct_name, ec_rd, req);
		req.setAttribute("pdceistctList", pdceistctList);
		req.setAttribute("logoutMap", Pdceistct.auditMap);
		return "home";
	}
	
	/*
	 * 查询审核审核打印
	 */
	public String findAuditPrint() {
		if (pdceistct == null) {
			pdceistct = new Pdceistct();
		}
		List<Map<String, Object>> pdceistctList = service.findPdceistct(pdceistct,pdceistct_key, pdceistct_name, ec_rd, req);
		req.setAttribute("pdceistctList", pdceistctList);
		req.setAttribute("logoutMap", Pdceistct.auditMap);
		return showAuditPrintHome();
	}
	
	/*
	 * 审核打印
	 */
	public String showAuditPrintHome() {
		
		List<Map<String, Object>> sczldList = service.findPdceistct(pdceistct,pdceistct_key, pdceistct_name, ec_rd, req);
		req.setAttribute("sczldList", sczldList);
		req.setAttribute("logoutMap", Pdceistct.auditMap);
		return "shwoAuditPrint";
	}
	
	/*
	 * 显示审核页面
	 */
	public String showAudit(){
		
		String searchsql = "SELECT COUNT(*) FROM t_pdce_istct AS PI LEFT JOIN ";
		searchsql += "(SELECT m.*,sm.mat_supplier_name,sm.ht_mat_no FROM t_material AS m LEFT JOIN t_supply_mat AS sm ON m.supplymat_id = sm.id) AS mater ON pi.material_id = mater.id ";
		searchsql += "LEFT JOIN t_product AS p ON pi.product_id = p.id ";
		searchsql += "LEFT JOIN t_sell_details AS sd ON pi.s_detail_id = sd.id ";
		searchsql += "LEFT JOIN t_sell_bill AS sb ON sd.sell_bill_id = sb.id ";
		searchsql += "where pi.id=" + pdceistct.getUuid() + " ";
		String resultsql = "SELECT *,pi.id AS sczldId FROM t_pdce_istct AS PI LEFT JOIN ";
		resultsql += "(SELECT m.*,sm.mat_supplier_name,sm.ht_mat_no FROM t_material AS m LEFT JOIN t_supply_mat AS sm ON m.supplymat_id = sm.id) AS mater ON pi.material_id = mater.id ";
		resultsql += "LEFT JOIN t_product AS p ON pi.product_id = p.id ";
		resultsql += "LEFT JOIN t_sell_details AS sd ON pi.s_detail_id = sd.id ";
		resultsql += "LEFT JOIN t_sell_bill AS sb ON sd.sell_bill_id = sb.id ";
		resultsql += "where pi.id=" + pdceistct.getUuid() + " ORDER BY pi.id DESC limit ?, ? ";
		
		List<Map<String, Object>> sczldList = service.getEcsideList(ec_rd, searchsql, resultsql, req);
		req.setAttribute("sczldList", sczldList);
		
		return "showAudit";
	}
	
	/*
	 * 审核
	 */
	public String audit(){
		service.update(pdceistct);
		return showAuditPrintHome();
	}
	
	/*
	 * 审核打印-预览
	 */
	public String auditPrint(){
		
		
		
		return "";
	}

	public String showAddUI() {
		String searchsql = "SELECT COUNT(*) FROM t_material AS m LEFT JOIN t_supply_mat AS sm ON m.supplymat_id=sm.id WHERE m.is_logout='0'";
		String resultsql = "SELECT m.*,sm.mat_supplier_name,sm.ht_mat_no FROM t_material AS m LEFT JOIN t_supply_mat AS sm ON m.supplymat_id=sm.id WHERE m.is_logout='0' limit ?, ?";
		List<Map<String, Object>> materialList = service.getEcsideList("1000",searchsql, resultsql, req);

		searchsql = "SELECT COUNT(*) FROM t_product WHERE is_logout='0'";
		resultsql = "SELECT * FROM t_product WHERE is_logout='0' limit ?, ?";
		List<Map<String, Object>> productList = productService.getEcsideList("1000", searchsql, resultsql, req);

		req.setAttribute("materialList", materialList);
		req.setAttribute("productList", productList);

		Integer productionUuid = pdceistct.getUuid();
		session.setAttribute("productionUuid", productionUuid);
		return "addUI";
	}

	public String add() {
		try {
			Integer productionUuid = (Integer) session.getAttribute("productionUuid");
			Integer strClid = Integer.parseInt(req.getParameter("txtCLmc")); // 材料表Id
			Integer strHtljh = Integer.parseInt(req.getParameter("txtHtljh")); // 华天零件号(产品ID)
			String strWcrq = req.getParameter("txtWcrq"); // 完成日期
			BigDecimal syzl = new BigDecimal(req.getParameter("txtSyzl")); // 使用重量

			String sql = "SELECT sb.*,sd.id AS detailId,sd.sell_bill_id,sd.sell_detail_code,sd.cus_pdct_no,sd.order_count,sd.remark,sd.price,sd.amount FROM t_sell_bill AS sb ";
			sql += "LEFT JOIN t_sell_details AS sd ON sb.id=sd.sell_bill_id ";
			sql += "LEFT JOIN (";
			sql += "SELECT c.*,cp.product_id,cp.cus_pn FROM t_customer AS c LEFT JOIN t_cust_pdct AS cp ON c.id=cp.customer_id";
			sql += ") AS cus ON sb.customer_id=cus.id ";
			sql += "WHERE sb.id='" + productionUuid + "' ";

			List list = getMaterialInfoById(sql);
			JSONArray jo = JSONArray.fromObject(list.toArray());

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (pdceistct == null) {
				pdceistct = new Pdceistct();
			}
			pdceistct.setsDetailId(Integer.parseInt(jo.getJSONObject(0).getString("detailId")));
			pdceistct.setStartDate(sdf.parse(jo.getJSONObject(0).getString("order_date")));
			pdceistct.setPdceCount(Integer.parseInt(jo.getJSONObject(0).getString("order_count")));
			pdceistct.setUsedMatWeight(syzl);
			pdceistct.setIsOver("0");
			pdceistct.setMaterialId(strClid);			//材料ID
			pdceistct.setEndDate(sdf.parse(strWcrq));	//完成日期
			pdceistct.setProductId(strHtljh);			//产品ID
			service.save(pdceistct);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showHome();
	}

	public String delete() {
		return showHome();
	}

	public String modify() {
		return showHome();
	}

	public void getMaterialInfo() {
		try {
			Integer materId = Integer.parseInt(req.getParameter("materId"));

			if (material == null) {
				material = new Material();
			}
			material.setUuid(materId);
			material = materialService.selectOne(material);

			JSONObject jo = JSONObject.fromObject(material);

			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(jo.toString());
			resp.getWriter().flush();
			resp.getWriter().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSellBillInfoByPdceistctId() {
		try {
			Integer productionUuid = (Integer) session
					.getAttribute("productionUuid");

			String sql = "SELECT * FROM t_sell_bill AS sb ";
			sql += "LEFT JOIN t_sell_details AS sd ON sb.id=sd.sell_bill_id ";
			sql += "LEFT JOIN (";
			sql += "SELECT c.*,cp.product_id,cp.cus_pn FROM t_customer AS c LEFT JOIN t_cust_pdct AS cp ON c.id=cp.customer_id";
			sql += ") AS cus ON sb.customer_id=cus.id ";
			sql += "WHERE sb.id='" + productionUuid + "' ";

			List list = getMaterialInfoById(sql);

			JSONArray jo = JSONArray.fromObject(list.toArray());

			System.out.println("-----" + jo);

			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(jo.toString());
			resp.getWriter().flush();
			resp.getWriter().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getMaterialInfoById(String sql) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;

		try {
			if (pdceistct == null) {
				pdceistct = new Pdceistct();
			}
			conn = DbUtils.getConnection();
			prst = conn.prepareStatement(sql);

			rs = prst.executeQuery();

			ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
			List list = new ArrayList();
			int columnCount = md.getColumnCount(); // Map rowData;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while (rs.next()) {
				Map rowData = new HashMap();
				for (int i = 1; i <= columnCount; i++) {
					if (md.getColumnName(i).equals("order_date")) {
						try {
							String str = sdf.format((Date) rs.getObject(i));
							rowData.put(md.getColumnName(i), str);
						} catch (Exception e) {
							rowData.put(md.getColumnName(i), "");
						}
					} else if (md.getColumnName(i).equals("lead_date")) {
						try {
							String str = sdf.format((Date) rs.getObject(i));
							rowData.put(md.getColumnName(i), str);
						} catch (Exception e) {
							rowData.put(md.getColumnName(i), "");
						}
					} else {
						rowData.put(md.getColumnName(i), rs.getObject(i));
					}
				}
				list.add(rowData);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			DbUtils.closeStatement(prst);
		}
	}

	private PdceistctService service = ServiceProxyFactory.getInstanceNoMybatis(new PdceistctService());
	private ProductService productService = ServiceProxyFactory.getInstanceNoMybatis(new ProductService());
	private MaterialService materialService = ServiceProxyFactory.getInstanceNoMybatis(new MaterialService());
	private Pdceistct pdceistct;
	private Material material;

	private String pdceistct_key;
	private String pdceistct_name;

	public Pdceistct getPdceistct() {
		return pdceistct;
	}

	public void setPdceistct(Pdceistct pdceistct) {
		this.pdceistct = pdceistct;
	}

	public String getPdceistct_key() {
		return pdceistct_key;
	}

	public void setPdceistct_key(String pdceistct_key) {
		this.pdceistct_key = pdceistct_key;
	}

	public String getPdceistct_name() {
		return pdceistct_name;
	}

	public void setPdceistct_name(String pdceistct_name) {
		this.pdceistct_name = pdceistct_name;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

}
