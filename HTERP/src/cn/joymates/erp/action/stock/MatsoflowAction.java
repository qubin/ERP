package cn.joymates.erp.action.stock;

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

import org.apache.ibatis.session.SqlSession;

import com.mysql.jdbc.ResultSetMetaData;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.dao.IWarehouse;
import cn.joymates.erp.dao.impl.WarehouseDaoImpl;
import cn.joymates.erp.domain.Material;
import cn.joymates.erp.domain.Matsoflow;
import cn.joymates.erp.domain.RowFlow;
import cn.joymates.erp.domain.Supplier;
import cn.joymates.erp.domain.SupplyMat;
import cn.joymates.erp.domain.User;
import cn.joymates.erp.domain.Warehouse;
import cn.joymates.erp.exceptions.DaoException;
import cn.joymates.erp.service.MaterialService;
import cn.joymates.erp.service.MatsoflowService;
import cn.joymates.erp.service.RowFlowService;
import cn.joymates.erp.service.SupplierService;
import cn.joymates.erp.service.SupplyMatService;
import cn.joymates.erp.service.WarehousService;
import cn.joymates.erp.utils.ServiceProxyFactory;
import cn.joymates.erp.utils.db.DbUtils;
import cn.joymates.erp.utils.db.SessionFactoryUtil;

public class MatsoflowAction extends BaseAction {
	
	@SuppressWarnings("static-access")
	public String showHome() {
		if (matsoflow == null) {
			matsoflow = new Matsoflow();
		}
		List<Map<String, Object>> matsoflowList = service.find(matsoflow,matsoflow_key,matsoflow_name,ec_rd,req);
		req.setAttribute("matsoflowList", matsoflowList);
		req.setAttribute("logoutMap", matsoflow.logoutMap);
		return "home";
	}
	
	@SuppressWarnings("static-access")
	public String find() {
		if (matsoflow == null) {
			matsoflow = new Matsoflow();
		}
		List<Map<String, Object>> matsoflowList = service.find(matsoflow,matsoflow_key,matsoflow_name,ec_rd,req);
		req.setAttribute("matsoflowList", matsoflowList);
		req.setAttribute("logoutMap", matsoflow.logoutMap);
		return "home";
	}
	
	public String showAddUI() {
		supplier = new Supplier();
		supplier.setIsLogout("0");
		List<Supplier> supplierList = supplierService.selectList(supplier);	  //供应商列表
		IWarehouse iwarehouse = new WarehouseDaoImpl();
		warehouse = new Warehouse();
		warehouse.setIsLogout("0");
		List<Warehouse> warehouseList = iwarehouse.selectList(warehouse);	//仓库列表
		req.setAttribute("warehouseList", warehouseList);
		req.setAttribute("supplierList", supplierList);
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
	
	public String add() {
		try {
			//更新材料重量
			BigDecimal weigth = material.getWeight();	//处理重量
			material = new Material();
			material.setSupplymatId(supplyMat.getSupplyMatId());
			List<Material> materialList = materialService.selectList(material);
			weigth = weigth.add(materialList.get(0).getWeight());	//处理值和库中值相加
			material.setUuid(materialList.get(0).getUuid());
			material.setWeight(weigth);
			materialService.update(material);
			
			//增加短溢处理记录
			User u = (User)session.getAttribute("loggedUser");
			String prcReason = matsoflow.getPrcReason();
			matsoflow = new Matsoflow();
			matsoflow.setPrcTime(new Date());
			matsoflow.setPrcPerson(u.getUserLoginId());
			matsoflow.setPrcReason(prcReason);						 //处理原因
			matsoflow.setbWeight(materialList.get(0).getWeight());	 //修正前重量
			matsoflow.setaWeight(weigth);   					     //修正后重量
			matsoflow.setMatD(materialList.get(0).getUuid());        //原材料编号
			service.save(matsoflow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showHome();
	}
	
	public String showModifyUI() {
		if(material == null){
			material = new Material();
		}
		if(warehouse == null){
			warehouse = new Warehouse();
		}
		if(rowFlow == null){
			rowFlow = new RowFlow();
		}
		if(supplyMat == null){
			supplyMat = new SupplyMat();
		}
		if(supplier == null){
			supplier = new Supplier();
		}
		if(matsoflow == null){
			matsoflow = new Matsoflow();
		}
		matsoflow = service.selectOne(matsoflow);
		BigDecimal aw = matsoflow.getaWeight().subtract(matsoflow.getbWeight()); //获得处理的重量
		matsoflow.setaWeight(aw);
		material.setUuid(matsoflow.getMatD());
		material = materialService.selectOne(material);	 			//获得材料信息
		warehouse.setWarehouseId(material.getWarehouseId());
		warehouse = warehousService.selectList(warehouse).get(0);	//获得仓库信息
		rowFlow.setMaterialId(material.getUuid());
		rowFlow = rowFlowService.selectList(rowFlow).get(0);		//获得仓库出入记录信息
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd");
		String outTime =  sdf.format(rowFlow.getOutTime());
		req.setAttribute("outTime", outTime);
		supplyMat.setSupplyMatId(material.getSupplymatId());
		supplyMat = supplyMatService.selectList(supplyMat).get(0);	//获得供应商材料信息
		supplier.setUuid(supplyMat.getSupplyId());
		supplier = supplierService.selectList(supplier).get(0);		//获得供应商信息
		return "modifyUI";
	}
	
	public String delete(){
		try {
			//注销
			if(!"".equals(matsoflow.getLogoutReason()) && matsoflow.getLogoutReason() != null){
				String lr = new String(matsoflow.getLogoutReason().getBytes("ISO-8859-1"),"UTF-8");
				matsoflow.setLogoutReason(lr);
			}
			service.update(matsoflow);
			
			if(material == null){
				material = new Material();
			}
			Matsoflow matsoflowInfo = service.selectOne(matsoflow);
			
			material.setUuid(matsoflowInfo.getMatD());
			if(matsoflowInfo.getIsLogout().equals("1")){
				material.setWeight(matsoflowInfo.getbWeight());	//撤销短溢处理
			}else{
				material.setWeight(matsoflowInfo.getaWeight());	//恢复短溢处理
			}
			materialService.update(material);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showHome();
	}
	
	public String modify() {
		String matsoflowAWeight = req.getParameter("matsoflowAWeight");
		BigDecimal bd = new BigDecimal(matsoflowAWeight);
		
		Matsoflow ms = new Matsoflow();
		ms.setUuid(matsoflow.getUuid());
		ms = service.selectOne(ms);
		BigDecimal w = ms.getbWeight().add(bd);	//更新的Weight
		matsoflow.setaWeight(w);
		service.update(matsoflow);	//更新短溢处理记录信息
		
		if(material == null){
			material = new Material();
		}
		material.setUuid(ms.getMatD());
		material.setWeight(w);
		materialService.update(material);	//更新原材料信息重量
		
		return showHome();
	}
	
	

	
	
	
	
	
	
	
	
	
	private static final long serialVersionUID = 1L;
	private MatsoflowService service = ServiceProxyFactory.getInstanceNoMybatis(new MatsoflowService());
	private SupplierService supplierService = ServiceProxyFactory.getInstanceNoMybatis(new SupplierService());
	private SupplyMatService supplyMatService = ServiceProxyFactory.getInstanceNoMybatis(new SupplyMatService());
	private MaterialService materialService = ServiceProxyFactory.getInstanceNoMybatis(new MaterialService());
	private WarehousService warehousService = ServiceProxyFactory.getInstanceNoMybatis(new WarehousService());
	private RowFlowService rowFlowService = ServiceProxyFactory.getInstanceNoMybatis(new RowFlowService());
	private Matsoflow matsoflow;
	private Supplier supplier;
	private SupplyMat supplyMat;
	private Warehouse warehouse;
	private Material material;
	private RowFlow rowFlow;
	
	private String matsoflow_key;
	private String matsoflow_name;
	private String htClid;
	public Matsoflow getMatsoflow() {
		return matsoflow;
	}
	
	public Warehouse getWarehouse() {
		return warehouse;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public void setMatsoflow(Matsoflow matsoflow) {
		this.matsoflow = matsoflow;
	}

	public String getMatsoflow_key() {
		return matsoflow_key;
	}

	public void setMatsoflow_key(String matsoflow_key) {
		this.matsoflow_key = matsoflow_key;
	}

	public String getMatsoflow_name() {
		return matsoflow_name;
	}

	public void setMatsoflow_name(String matsoflow_name) {
		this.matsoflow_name = matsoflow_name;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getHtClid() {
		return htClid;
	}

	public void setHtClid(String htClid) {
		this.htClid = htClid;
	}

	public SupplyMat getSupplyMat() {
		return supplyMat;
	}

	public void setSupplyMat(SupplyMat supplyMat) {
		this.supplyMat = supplyMat;
	}

	public RowFlow getRowFlow() {
		return rowFlow;
	}

	public void setRowFlow(RowFlow rowFlow) {
		this.rowFlow = rowFlow;
	}
	
}
