package cn.joymates.erp.service;

import cn.joymates.erp.dao.impl.WarehouseDaoImpl;
import cn.joymates.erp.domain.Warehouse;

public class WarehousService extends BaseService<Warehouse>{
	
	private WarehouseDaoImpl dao = new WarehouseDaoImpl();
	
	public int save(Warehouse ob){
		return dao.save(ob);
	}
	
	public void update(Warehouse ob){
		dao.update(ob);
	}

}
