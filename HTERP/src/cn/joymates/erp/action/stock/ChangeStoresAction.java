package cn.joymates.erp.action.stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mysql.jdbc.ResultSetMetaData;

import net.sf.json.JSONArray;
import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.dao.IWarehouse;
import cn.joymates.erp.dao.impl.WarehouseDaoImpl;
import cn.joymates.erp.domain.CustPdct;
import cn.joymates.erp.domain.Material;
import cn.joymates.erp.domain.Product;
import cn.joymates.erp.domain.Supplier;
import cn.joymates.erp.domain.TransFlow;
import cn.joymates.erp.domain.User;
import cn.joymates.erp.domain.Warehouse;
import cn.joymates.erp.exceptions.DaoException;
import cn.joymates.erp.service.CustPdctService;
import cn.joymates.erp.service.MaterialService;
import cn.joymates.erp.service.ProductService;
import cn.joymates.erp.service.SupplierService;
import cn.joymates.erp.service.TransFlowService;
import cn.joymates.erp.utils.ServiceProxyFactory;
import cn.joymates.erp.utils.db.DbUtils;
import cn.joymates.erp.utils.db.SessionFactoryUtil;

public class ChangeStoresAction extends BaseAction {

	public String showHome() {

		supplier = new Supplier();
		supplier.setIsLogout("0");
		List<Supplier> supplierList = supplierService.selectList(supplier);

		IWarehouse warehouse = new WarehouseDaoImpl();

		warehous = new Warehouse();
		warehous.setIsLogout("0");
		List<Warehouse> warehouseList = warehouse.selectList(warehous);

		if(product == null){
			product = new Product();
		}
		String searchsql = "SELECT COUNT(*) FROM t_product AS this_ WHERE 1=1 AND is_logout='0' ";
		String resultsql  = "SELECT *,p.id AS pId,cp.id AS cpId FROM t_product AS p LEFT JOIN t_cust_pdct AS cp ON p.id=cp.product_id ";
		resultsql += "LEFT JOIN t_warehouse AS w ON cp.area=w.id ";
		resultsql += "WHERE w.id IS NOT NULL AND p.is_logout='0' limit ?, ?";
		List<Map<String, Object>> productList = productService.getEcsideList("1000", searchsql, resultsql, req);
		
		req.setAttribute("supplierList", supplierList);
		req.setAttribute("warehouseList", warehouseList);
		req.setAttribute("productList", productList);
		return "home";
	}

	public void getSupplierInfoById() {
		try {
			if (material == null) {
				material = new Material();
			}
			Integer strSupId = Integer.parseInt(req.getParameter("supid"));
			SqlSession sess = SessionFactoryUtil.getSession();
			String strMatSupplierName = sess.selectOne("right.getSupplierMaterialIdBySuppyId", strSupId);
			if(strMatSupplierName == null){
				strMatSupplierName = "";
			}
			strMatSupplierName = new String(strMatSupplierName.getBytes("GBK"),"UTF-8");

			System.out.println(strMatSupplierName);

			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(strMatSupplierName);
			resp.getWriter().flush();
			resp.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getMaterialInfoById() {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;

		try {
			if (material == null) {
				material = new Material();
			}
			Integer strMatId = Integer.parseInt(req.getParameter("matid"));

			 String sql  = "SELECT m.*,r.out_time,w.sign1,s.name FROM t_material AS m ";
					sql += "LEFT JOIN t_raw_flow r ON m.id=r.material_id ";
					sql += "LEFT JOIN t_warehouse AS w ON m.warehouse_id=w.id ";
					sql += "LEFT JOIN t_supply_mat AS sm ON m.supplymat_id=sm.id ";
					sql += "LEFT JOIN t_supplier AS s ON sm.supply_id=s.id ";
					sql += "WHERE sm.id=" + strMatId + "";
			conn = DbUtils.getConnection();
			prst = conn.prepareStatement(sql);

			rs = prst.executeQuery();

			ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
			List list = new ArrayList();
			int columnCount = md.getColumnCount(); // Map rowData;
			while (rs.next()) {
				Map rowData = new HashMap();
				for (int i = 1; i <= columnCount; i++) {
					if (md.getColumnName(i).equals("out_time")) {
						try {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
							String str = sdf.format((Date)rs.getObject(i));
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
			JSONArray jo = JSONArray.fromObject(list);

			System.out.println(jo.toString());

			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(jo.toString());
			resp.getWriter().flush();
			resp.getWriter().close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			DbUtils.closeStatement(prst);
		}
	}

	/*
	 * 获取产品信息
	 */
	public void getProductInfo(){
		try {
			Integer productId = Integer.parseInt(req.getParameter("proid"));
			
			String sql  = "SELECT *,p.id AS pId,cp.id AS cpId FROM t_product AS p LEFT JOIN t_cust_pdct AS cp ON p.id=cp.product_id ";
				   sql += "LEFT JOIN t_warehouse AS w ON cp.area=w.id ";
				   sql += "WHERE w.id IS NOT NULL AND p.id='" + productId + "'";
				   
			List list = getProductInfoAndDetailById(sql);
			
			JSONArray jo = JSONArray.fromObject(list);
			
			System.out.println(jo.toString());

			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(jo.toString());
			resp.getWriter().flush();
			resp.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 产品转库
	 */
	public String changestoresProduct(){
		User u = (User)session.getAttribute("loggedUser");
		//转库
		CustPdct cp = new CustPdct();
		cp.setProdId(product.getUuid());
		List<CustPdct> custPdctInfo = custPdctService.selectList(cp);	//根据product id查询客户产品表信息
		
		custPdct.setCpId(custPdctInfo.get(0).getCpId());
		custPdctService.update(custPdct);
		
		//增加成品转库记录
		if(transFlow == null){
			transFlow = new TransFlow();
		}
		transFlow.setTransDate(new Date());
		transFlow.setPerson(u.getUserId());
		transFlow.setMatOrPdct("2");	//1：材料 2：成品
		transFlow.setMatPdctId(product.getUuid());
		transFlow.setSrcWhSign(custPdctInfo.get(0).getArea());  				//原仓库
		transFlow.setTgtWhSign(custPdct.getArea());    							//目标仓库
		transFlowService.save(transFlow);			   							//保存材料转库记录
		
		return showHome();
	}
	
	
	/*
	 * 获取产品和产品详情信息
	 */
	public List getProductInfoAndDetailById(String sql) {
		Connection conn = null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		try {
			conn = DbUtils.getConnection();
			prst = conn.prepareStatement(sql);
			rs = prst.executeQuery();

			ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
			List list = new ArrayList();
			int columnCount = md.getColumnCount(); // Map rowData;
			while (rs.next()) {
				Map rowData = new HashMap();
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i), rs.getObject(i));
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
	
	/**
	 * 材料转库
	 */
	public String changestores() {
		try {
			User u = (User)session.getAttribute("loggedUser");
			//转库
			material = new Material();
			material.setUuid(Integer.parseInt(strMaterialId));
			
			Material mFirst = materialService.selectOne(material);	//获取未更新仓库之前的材料
			
			material.setWarehouseId(Integer.parseInt(updatestore));
			materialService.update(material);
			
			Material mLater = materialService.selectOne(material);	//获取更新后仓库之前的材料
			
			//材料转库记录
			if(transFlow == null){
				transFlow = new TransFlow();
			}
			transFlow.setTransDate(new Date());
			transFlow.setPerson(u.getUserId());
			transFlow.setMatOrPdct("1");
			transFlow.setMatPdctId(Integer.parseInt(strMaterialId));
			transFlow.setSrcWhSign(mFirst.getWarehouseId());
			transFlow.setTgtWhSign(mLater.getWarehouseId());
			transFlowService.save(transFlow);		//保存材料转库记录
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return showHome();
	}

	
	
	
	
	
	private static final long serialVersionUID = 1L;
	private SupplierService supplierService = ServiceProxyFactory.getInstanceNoMybatis(new SupplierService());
	private MaterialService materialService = ServiceProxyFactory.getInstanceNoMybatis(new MaterialService());
	private TransFlowService transFlowService = ServiceProxyFactory.getInstanceNoMybatis(new TransFlowService());
	private ProductService productService = ServiceProxyFactory.getInstanceNoMybatis(new ProductService());
	private CustPdctService custPdctService = ServiceProxyFactory.getInstanceNoMybatis(new CustPdctService());
	
	private Supplier supplier;
	private Warehouse warehous;
	private Material material;
	private TransFlow transFlow;
	private Product product;
	private CustPdct custPdct;
	
	private String strMaterialId;
	private String updatestore;

	public Supplier getSupplier() {
		return supplier;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Warehouse getWarehous() {
		return warehous;
	}

	public void setWarehous(Warehouse warehous) {
		this.warehous = warehous;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public String getStrMaterialId() {
		return strMaterialId;
	}

	public void setStrMaterialId(String strMaterialId) {
		this.strMaterialId = strMaterialId;
	}

	public String getUpdatestore() {
		return updatestore;
	}

	public void setUpdatestore(String updatestore) {
		this.updatestore = updatestore;
	}

	public TransFlow getTransFlow() {
		return transFlow;
	}

	public void setTransFlow(TransFlow transFlow) {
		this.transFlow = transFlow;
	}

	public CustPdct getCustPdct() {
		return custPdct;
	}

	public void setCustPdct(CustPdct custPdct) {
		this.custPdct = custPdct;
	}

}
