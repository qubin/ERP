package cn.joymates.erp.action.stock;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mysql.jdbc.ResultSetMetaData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.dao.IWarehouse;
import cn.joymates.erp.dao.impl.WarehouseDaoImpl;
import cn.joymates.erp.domain.Material;
import cn.joymates.erp.domain.Supplier;
import cn.joymates.erp.domain.Warehouse;
import cn.joymates.erp.exceptions.DaoException;
import cn.joymates.erp.service.MaterialService;
import cn.joymates.erp.service.SupplierService;
import cn.joymates.erp.utils.ServiceProxyFactory;
import cn.joymates.erp.utils.db.Bean2SqlUtils;
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

		req.setAttribute("supplierList", supplierList);
		req.setAttribute("warehouseList", warehouseList);
		return "home";
	}

	public void getSupplierInfoById() {
		try {
			if (material == null) {
				material = new Material();
			}
			Integer strSupId = Integer.parseInt(req.getParameter("supid"));
			SqlSession sess = SessionFactoryUtil.getSession();
			String strMatSupplierName = sess.selectOne(
					"right.getSupplierMaterialIdBySuppyId", strSupId);

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
			
			String sql = "SELECT m.*,r.out_time,w.sign1,s.name FROM t_material AS m "
					+ "LEFT JOIN t_raw_flow r ON m.id=r.material_id "
					+ "LEFT JOIN t_warehouse AS w ON m.warehouse_id=w.id "
					+ "LEFT JOIN t_supply_mat AS sm ON m.supplymat_id=sm.id "
					+ "LEFT JOIN t_supplier AS s ON sm.supply_id=s.id "
					+ "WHERE m.id=" + strMatId + "";
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
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
							String str = sdf.format(rs.getObject(i));
							rowData.put(md.getColumnName(i), str);
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
	
	/**
	 * 转库
	 */
	public String changestores(){
		try {
			String a = strMaterialId;
			String b = updatestore;
			material = new Material();
			material.setUuid(Integer.parseInt(strMaterialId));
			material.setWarehouseId(Integer.parseInt(updatestore));
			materialService.update(material);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return showHome();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private static final long serialVersionUID = 1L;
	private SupplierService supplierService = ServiceProxyFactory.getInstanceNoMybatis(new SupplierService());
	private MaterialService materialService = ServiceProxyFactory.getInstanceNoMybatis(new MaterialService());
	private Supplier supplier;
	private Warehouse warehous;
	private Material material;
	private String strMaterialId;
	private String updatestore;
	public Supplier getSupplier() {
		return supplier;
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
	
}
