package cn.joymates.erp.service;

import cn.joymates.erp.dao.impl.PdctFlowDaoImpl;
import cn.joymates.erp.domain.PdctFlow;

public class PdctFlowService extends BaseService<PdctFlow> {
	public PdctFlowService(){
		dao = new PdctFlowDaoImpl();
	}
}
