package cn.joymates.erp.service;

import cn.joymates.erp.dao.impl.ProdDetailDaoImpl;
import cn.joymates.erp.domain.ProdDetail;

public class ProdDetailService extends BaseService<ProdDetail> {
	public ProdDetailService(){
		dao = new ProdDetailDaoImpl();
	}
}
