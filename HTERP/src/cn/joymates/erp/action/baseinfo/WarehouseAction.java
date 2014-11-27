package cn.joymates.erp.action.baseinfo;

import com.opensymphony.xwork2.ModelDriven;

import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.Warehouse;

public class WarehouseAction extends BaseAction implements ModelDriven<Warehouse>{

	private static final long serialVersionUID = 7984522836906622668L;
	
	private Warehouse wh;
	
	@Override
	public Warehouse getModel() {
		if(wh == null){
			wh = new Warehouse();
		}
		return wh;
	}
	public Warehouse getWh() {
		return wh;
	}

	public void setWh(Warehouse wh) {
		this.wh = wh;
	}
	
}
