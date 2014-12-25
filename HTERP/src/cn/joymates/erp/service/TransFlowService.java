package cn.joymates.erp.service;

import cn.joymates.erp.dao.impl.TransFlowDaoImpl;
import cn.joymates.erp.domain.TransFlow;

public class TransFlowService  extends BaseService<TransFlow> {
	
	public TransFlowService() {
		dao = new TransFlowDaoImpl();
	}
	
}
