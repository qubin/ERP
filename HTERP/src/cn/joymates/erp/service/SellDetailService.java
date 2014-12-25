package cn.joymates.erp.service;

import cn.joymates.erp.dao.impl.SellDetailDaoImpl;
import cn.joymates.erp.domain.SellDetail;

public class SellDetailService extends BaseService<SellDetail> {
	public SellDetailService(){
		dao = new SellDetailDaoImpl();
	}
}
