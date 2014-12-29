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

import net.sf.json.JSONArray;

import org.apache.ibatis.session.SqlSession;

import com.mysql.jdbc.ResultSetMetaData;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.dao.IWarehouse;
import cn.joymates.erp.dao.impl.WarehouseDaoImpl;
import cn.joymates.erp.domain.Material;
import cn.joymates.erp.domain.Product;
import cn.joymates.erp.domain.QCStore;
import cn.joymates.erp.domain.Supplier;
import cn.joymates.erp.domain.SupplyMat;
import cn.joymates.erp.domain.Warehouse;
import cn.joymates.erp.exceptions.DaoException;
import cn.joymates.erp.service.MaterialService;
import cn.joymates.erp.service.ProductService;
import cn.joymates.erp.service.QCStoreService;
import cn.joymates.erp.service.SupplierService;
import cn.joymates.erp.service.SupplyMatService;
import cn.joymates.erp.utils.ServiceProxyFactory;
import cn.joymates.erp.utils.db.DbUtils;
import cn.joymates.erp.utils.db.SessionFactoryUtil;

public class QCStoreAction extends BaseAction {

	public String showHome() {
		if (qcstore == null) {
			qcstore = new QCStore();
		}
		List<Map<String, Object>> qcStoreList = service.find(qcstore,qcstore_key,qcstore_name,ec_rd,req);
		req.setAttribute("qcStoreList", qcStoreList);
		req.setAttribute("qcTypeMap", QCStore.qcTypeMap);
		return "home";
	}
	
	public String find() {
		if (qcstore == null) {
			qcstore = new QCStore();
		}
		List<Map<String, Object>> qcStoreList = service.find(qcstore,qcstore_key,qcstore_name,ec_rd,req);
		req.setAttribute("qcStoreList", qcStoreList);
		req.setAttribute("qcTypeMap", QCStore.qcTypeMap);
		return "home";
	}
	
	public String showAddUI() {
		Supplier supplier = new Supplier();
		supplier.setIsLogout("0");
		List<Supplier> supplierList = supplierService.selectList(supplier);

		IWarehouse warehouse = new WarehouseDaoImpl();

		Warehouse warehous = new Warehouse();
		warehous.setIsLogout("0");
		List<Warehouse> warehouseList = warehouse.selectList(warehous);

		String searchsql = "SELECT COUNT(*) FROM T_PRODUCT AS this_ WHERE 1=1 AND is_logout='0' ";
		String resultsql = "SELECT * FROM T_PRODUCT AS this_ WHERE 1=1 AND is_logout='0' limit ?, ?";
		List<Map<String, Object>> productList = productService.getEcsideList("1000", searchsql, resultsql, req);
		
		req.setAttribute("supplierList", supplierList);
		req.setAttribute("warehouseList", warehouseList);
		req.setAttribute("productList", productList);
		
		return "addUI";
	}
	
	
	public void getSupplierInfoById() {
		try {
			if (material == null) {
				material = new Material();
			}
			Integer strSupId = Integer.parseInt(req.getParameter("supid"));
			SqlSession sess = SessionFactoryUtil.getSession();
			String strMatSupplierName = sess.selectOne("right.getSupplierMaterialIdBySuppyId", strSupId);
			strMatSupplierName = new String(strMatSupplierName.getBytes("GBK"),"UTF-8");
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
	 * 添加材料QC
	 */
	public String add() {
		String strQcType = req.getParameter("txtQcType");	//获取保存qc的类型  1：材料  2：成品
		if(strQcType.equals("1")){
			if(qcstore == null){
				qcstore = new QCStore();
			}
			
			QCStore qc = new QCStore();
			qc.setMatPdctId(material.getUuid());
			List<QCStore> qclist = service.selectList(qc);	//查询是否存在该信息
			if(qclist.size() > 0){	//如果存在则修改
				qcstore.setUuid(qclist.get(0).getUuid());
				qcstore.setMatOrPdct("1");
				qcstore.setMatPdctId(material.getUuid());
				qcstore.setWeight(qcstore.getWeight().add(qclist.get(0).getWeight()));
				service.update(qcstore);
			}else{					//如果不存在则添加
				qcstore.setMatOrPdct("1");
				qcstore.setMatPdctId(material.getUuid());
				service.save(qcstore);
			}
		}
		return showHome();
	}
	
	
	/*
	 * 获取产品信息
	 */
	public void getProductInfo(){
		try {
			Integer productId = Integer.parseInt(req.getParameter("proid"));
			
			String sql  = "SELECT *,p.id AS pId,cp.id AS cpId FROM t_product AS p LEFT JOIN t_cust_pdct AS cp ON p.id=cp.product_id ";
				   sql += "LEFT JOIN t_warehouse AS w ON cp.area=w.id ";
				   sql += "WHERE p.id='" + productId + "'";
				   
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
	
	/*
	 * 增加产品QC
	 */
	public String qcstoreProduct(){
		QCStore qc = new QCStore();
		qc.setMatOrPdct("2");							//1：材料   2：产品
		qc.setMatPdctId(product.getUuid());
		List<QCStore> qclist = service.selectList(qc);	//查询是否存在该信息
		if(qclist.size() > 0){	//如果存在则修改
			qcstore.setUuid(qclist.get(0).getUuid());
			qcstore.setPicCount(qclist.get(0).getPicCount() + qcstore.getPicCount());
			service.update(qcstore);
		}else{					//如果不存在则添加
			qcstore.setMatOrPdct("2");					//1：材料   2：产品
			qcstore.setMatPdctId(product.getUuid());
			service.save(qcstore);
		}
		return showHome();
	}
	
	
	
	
	
	
	
	
	private static final long serialVersionUID = 1L;
	private QCStoreService service = ServiceProxyFactory.getInstanceNoMybatis(new QCStoreService());
	private SupplierService supplierService = ServiceProxyFactory.getInstanceNoMybatis(new SupplierService());
	private MaterialService materialService = ServiceProxyFactory.getInstanceNoMybatis(new MaterialService());
	private ProductService productService = ServiceProxyFactory.getInstanceNoMybatis(new ProductService());
	private SupplyMatService supplyMatService = ServiceProxyFactory.getInstanceNoMybatis(new SupplyMatService());
	
	private Material material;
	private SupplyMat supplyMat;
	private Supplier supplier;
	private QCStore qcstore;
	private Product product;
	
	private String qcstore_key;
	private String qcstore_name;
	
	public QCStoreService getService() {
		return service;
	}

	public void setService(QCStoreService service) {
		this.service = service;
	}

	public QCStore getQcstore() {
		return qcstore;
	}

	public void setQcstore(QCStore qcstore) {
		this.qcstore = qcstore;
	}

	public String getQcstore_key() {
		return qcstore_key;
	}

	public void setQcstore_key(String qcstore_key) {
		this.qcstore_key = qcstore_key;
	}

	public String getQcstore_name() {
		return qcstore_name;
	}

	public void setQcstore_name(String qcstore_name) {
		this.qcstore_name = qcstore_name;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public SupplyMat getSupplyMat() {
		return supplyMat;
	}

	public void setSupplyMat(SupplyMat supplyMat) {
		this.supplyMat = supplyMat;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
