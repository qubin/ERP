package cn.joymates.erp.action.stock;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.ResultSetMetaData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Material;
import cn.joymates.erp.domain.Supplier;
import cn.joymates.erp.domain.SupplyMat;
import cn.joymates.erp.domain.Warehouse;
import cn.joymates.erp.exceptions.DaoException;
import cn.joymates.erp.service.MaterialService;
import cn.joymates.erp.service.SupplierService;
import cn.joymates.erp.service.SupplyMatService;
import cn.joymates.erp.utils.ServiceProxyFactory;
import cn.joymates.erp.utils.db.DbUtils;

public class CutMaterialAction extends BaseAction {

	public String showHome() {
		try {
			if (material == null) {
				material = new Material();
			}
			List<Material> materialList = materialService.selectList(material, " AND mmat_id IS NOT NULL"); //查询所有母卷
			List<Material> sonList = materialService.selectList(material, " AND mmat_id IS NULL ");		//查询所有子卷
			String sql = "SELECT m.*,sm.mat_supplier_name FROM t_material AS m LEFT JOIN t_supply_mat AS sm ON m.supplymat_id=sm.id WHERE m.is_logout='0'";
			List list = getObjectList(sql);
			
			req.setAttribute("materialList", materialList);
			req.setAttribute("sonList", sonList);
			req.setAttribute("materialModeList", JSONArray.fromObject(list));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "home";
	}

	/*
	 * 获得母卷描述
	 */
	public void getMmatDesc(){
		try {
			if(material == null){
				material = new Material();
			}
			Integer materialId = Integer.parseInt(req.getParameter("mmatId"));
			material.setUuid(materialId);
			Material materialInfo = materialService.selectOne(material);
			//String materialDesc = materialInfo.getRemark();
			
			JSONObject jo = JSONObject.fromObject(materialInfo);
			
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(jo.toString());
			resp.getWriter().flush();
			resp.getWriter().close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 获得材料型号
	 */
	public void getMaterialModel(){
		try {
			if(material == null){
				material = new Material();
			}
//			Integer materId = Integer.parseInt(req.getParameter("materId"));
//			
//			material.setUuid(materId);
//			material = materialService.selectOne(material);
			String sql = "SELECT m.*,sm.mat_supplier_name FROM t_material AS m LEFT JOIN t_supply_mat AS sm ON m.supplymat_id=sm.id";
			List list = getObjectList(sql);
			
			JSONArray ja = JSONArray.fromObject(list.toString());
			
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(ja.toString());
			resp.getWriter().flush();
			resp.getWriter().close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String cutmaterial(){
		Integer materUuid = material.getUuid();	//材料表ID
		
		int intFjRow = Integer.parseInt(req.getParameter("txtFjRow")); 
		
		//根据材料表id查出该母卷的信息
		material = new Material();
		material.setUuid(materUuid);
		Material minfo = materialService.selectOne(material);
		
		SupplyMat sm = new SupplyMat();
		sm.setSupplyMatId(minfo.getSupplymatId());
		sm = supplyMatService.selectOne(sm);
		
		int js = 0;	//获取裁剪卷数之和
		for(int i=1;i <= intFjRow;i++){
			int cjjs = Integer.parseInt(req.getParameter("txtCjjs"+i));
			js += cjjs;
		}
		
		for(int i=1;i <= intFjRow;i++){
			String strClxh = req.getParameter("txtClxh"+i);
			int cjjs = Integer.parseInt(req.getParameter("txtCjjs"+i));//裁剪卷数
			String strCjcc = req.getParameter("txtCjcc"+i);		//裁剪尺寸及公差
			
			BigDecimal mjz = minfo.getWeight().divide(new BigDecimal(js),3);		//获取小卷的重量
			
			//保存信息到材料表
			minfo.setUuid(null);
			minfo.setWeight(mjz);
			minfo.setStandard(strCjcc);
			minfo.setScrollCount(cjjs);
			minfo.setMmatId(null);
			minfo.setScrollId(minfo.getScrollId());
			minfo.setMaterialModel(strClxh);
			minfo.setRemark("分卷获得");
			materialService.save(minfo);
			
			//保存信息到供应材料表
			supplyMat = new SupplyMat();
			supplyMat.setSupplyId(sm.getSupplyId());
			supplyMat.setMatSupplierName(strClxh);
			supplyMat.setHtMatNo(sm.getHtMatNo());
			supplyMatService.save(supplyMat);
		}
		
		return "home";
	}
	
	
	
	public List getObjectList(String sql) {
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
	
	
	

	private static final long serialVersionUID = 1L;
	private SupplierService supplierService = ServiceProxyFactory.getInstanceNoMybatis(new SupplierService());
	private MaterialService materialService = ServiceProxyFactory.getInstanceNoMybatis(new MaterialService());
	private SupplyMatService supplyMatService = ServiceProxyFactory.getInstanceNoMybatis(new SupplyMatService());
	private Supplier supplier;
	private Warehouse warehous;
	private Material material;
	private SupplyMat supplyMat;
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

	public SupplyMat getSupplyMat() {
		return supplyMat;
	}

	public void setSupplyMat(SupplyMat supplyMat) {
		this.supplyMat = supplyMat;
	}
	
}
