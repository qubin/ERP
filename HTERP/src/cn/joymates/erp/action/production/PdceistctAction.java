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
import cn.joymates.erp.domain.CustPdct;
import cn.joymates.erp.domain.Material;
import cn.joymates.erp.domain.Pdceistct;
import cn.joymates.erp.domain.Product;
import cn.joymates.erp.domain.QCStore;
import cn.joymates.erp.domain.Warehouse;
import cn.joymates.erp.exceptions.DaoException;
import cn.joymates.erp.service.CustPdctService;
import cn.joymates.erp.service.MaterialService;
import cn.joymates.erp.service.PdceistctService;
import cn.joymates.erp.service.ProductService;
import cn.joymates.erp.service.QCStoreService;
import cn.joymates.erp.service.WarehousService;
import cn.joymates.erp.utils.ServiceProxyFactory;
import cn.joymates.erp.utils.db.DbUtils;

public class PdceistctAction extends BaseAction {

	/*
	 * 指令单录入-查询
	 */
	public String showHome() {
		if (pdceistct == null) {
			pdceistct = new Pdceistct();
		}
		List<Map<String, Object>> pdceistctList = service.find(pdceistct,pdceistct_key, pdceistct_name, ec_rd, req);
		req.setAttribute("pdceistctList", pdceistctList);
		req.setAttribute("logoutMap", Pdceistct.auditMap);
		return "home";
	}
	
	/*
	 * 指令单录入-查询
	 */
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
	 * 审核打印-查询
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
	 * 审核打印-审核(添加)
	 */
	public String showAuditPrintHome() {
		
		List<Map<String, Object>> sczldList = service.findPdceistct(pdceistct,pdceistct_key, pdceistct_name, ec_rd, req);
		req.setAttribute("sczldList", sczldList);
		req.setAttribute("logoutMap", Pdceistct.auditMap);
		return "shwoAuditPrint";
	}
	
	/*
	 * 审核打印-显示
	 */
	public String showAudit(){
		
		List<Map<String, Object>> sczldList = service.findAuditPdceistct(pdceistct, ec_rd, req);
		req.setAttribute("sczldList", sczldList);
		req.setAttribute("logoutMap", Pdceistct.auditMap);
		return "showAudit";
	}
	
	/*
	 * 审核
	 */
	public String audit(){
		if(pdceistct.getVerifyStatus().equals("0")){	//审核未通过
			service.delete(pdceistct);
		}else{											//审核通过
			service.update(pdceistct);
		}
		return showAuditPrintHome();
	}
	
	/*
	 * 审核打印-预览
	 */
	public String auditPrint(){
		
		return "";
	}
	
	
	
	/*
	 * 已结单单据查询-home
	 */
	public String showOverProductionHome(){
		
		List<Map<String, Object>> sczldList = service.findAuditOverPdceistct("1","1",pdceistct,pdceistct_key, pdceistct_name,ec_rd,req);
		req.setAttribute("sczldList", sczldList);
		req.setAttribute("overMap", Pdceistct.overMap);	
		return "showOverProductionHome";
	}
	
	/*
	 * 指令单-结单-home
	 */
	public String productionOverHome(){
		
		List<Map<String, Object>> sczldList = service.findAuditOverPdceistct("1","0",pdceistct,pdceistct_key, pdceistct_name,ec_rd,req);
		req.setAttribute("sczldList", sczldList);
		req.setAttribute("overMap", Pdceistct.overMap);
		
		return "productionOverHome";
	}
	
	/*
	 * 显示结单页面
	 */
	public String showOverProduction(){
		List<Map<String, Object>> sczldList = service.findPdceistctInfoById(pdceistct, pdceistct_key, req);
		req.setAttribute("sczldList", sczldList);
		
		return "showOverProduction";
	}
	
	/*
	 * 结单
	 */
	public String overProduction(){
		
		try {
			//材料库减去用料
			Pdceistct pd1 = service.selectOne(pdceistct);										//根据生产指令单ID查询该指令单信息
			material = materialService.selectOne(material);									//根据材料表ID查询材料信息
			BigDecimal weight;
			if(material.getWeight() == null){
				weight = pd1.getUsedMatWeight().multiply(new BigDecimal(-1));			//乘以-1
			}else{
				weight = material.getWeight().subtract(pd1.getUsedMatWeight());       //获得仓库材料数量减去用料重量的结果
			}
			material.setWeight(weight);
			materialService.update(material);
			
			//材料QC增加报废用料
			qcStore = new QCStore();
			qcStore.setMatOrPdct("1");					//1：材料  2：成品
			qcStore.setMatPdctId(material.getUuid());
			List<QCStore> qcMatList = qcStoreService.selectList(qcStore);
			if(qcMatList.size() > 0){					//判断QC中是否存在该材料(存在则更新，不存在则增加)
				qcStore = new QCStore();
				qcStore.setUuid(qcMatList.get(0).getUuid());
				BigDecimal qcWeight;
				if(qcMatList.get(0).getWeight() == null){
					qcWeight = pdceistct.getMatScpWeight();
				}else{
					qcWeight = qcMatList.get(0).getWeight().add(pdceistct.getMatScpWeight());	//给QC库增加报废材料数量
				}
				qcStore.setWeight(qcWeight);
				qcStoreService.update(qcStore);
			}else{
				qcStore = new QCStore();
				qcStore.setMatOrPdct("1");				//1：材料  2：成品
				qcStore.setMatPdctId(material.getUuid());
				qcStore.setWeight(pdceistct.getMatScpWeight());
				qcStore.setIsolateReason("生产指令单报废材料");
				qcStoreService.save(qcStore);
			}
			
			//产品库增加完成产品数量
			List<CustPdct> cusPdctList = custPdctService.selectList(cusPdct); //根据客户产品编号查询客户产品表信息
			if(cusPdctList.size() > 0){		//判断客户产品表中是否存在该信息（如果存在则更新，不存在则增加）
				cusPdct.setCpId(cusPdctList.get(0).getCpId());
				int picCount;
				if(cusPdctList.get(0).getPicCount() == null){
					picCount = pdceistct.getPdceCount();
				}else{
					picCount = pdceistct.getPdceCount() + cusPdctList.get(0).getPicCount();
				}
				cusPdct.setPicCount(picCount);
				custPdctService.update(cusPdct);
			}
			
			//产品QC库增加报废产品数量
			Product productInfo = productService.selectOne(product);
			qcStore = new QCStore();
			qcStore.setMatOrPdct("2");				//1：材料  2：成品
			qcStore.setMatPdctId(productInfo.getUuid());
			List<QCStore> qcProductList = qcStoreService.selectList(qcStore);
			if(qcProductList.size() > 0){
				qcStore = new QCStore();
				qcStore.setUuid(qcMatList.get(0).getUuid());
				int qcPicCount;
				if(qcProductList.get(0).getPicCount() == null){
					qcPicCount = pdceistct.getScpCount();
				}else{
					qcPicCount = qcProductList.get(0).getPicCount() + pdceistct.getScpCount();	//给QC库增加报废材料数量
				}
				qcStore.setPicCount(qcPicCount);
				qcStoreService.update(qcStore);
			}else{
				qcStore = new QCStore();
				qcStore.setMatOrPdct("2");				//1：材料  2：成品
				qcStore.setMatPdctId(productInfo.getUuid());
				qcStore.setPicCount(pdceistct.getScpCount());
				qcStore.setIsolateReason("生产指令单报废产品");
				qcStoreService.save(qcStore);
			}
			
			//更新生产指令单信息
			pdceistct.setIsOver("1");
			service.update(pdceistct);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productionOverHome();
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
	private WarehousService warehousService = ServiceProxyFactory.getInstanceNoMybatis(new WarehousService());
	private QCStoreService qcStoreService = ServiceProxyFactory.getInstanceNoMybatis(new QCStoreService());
	private CustPdctService custPdctService = ServiceProxyFactory.getInstanceNoMybatis(new CustPdctService());
	
	
	private Pdceistct pdceistct;
	private Material material;
	private Warehouse warehouse;
	private QCStore qcStore;
	private CustPdct cusPdct;
	private Product product;
	
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

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public QCStore getQcStore() {
		return qcStore;
	}

	public void setQcStore(QCStore qcStore) {
		this.qcStore = qcStore;
	}

	public CustPdct getCusPdct() {
		return cusPdct;
	}

	public void setCusPdct(CustPdct cusPdct) {
		this.cusPdct = cusPdct;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
