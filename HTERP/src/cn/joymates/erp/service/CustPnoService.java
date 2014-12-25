package cn.joymates.erp.service;

import cn.joymates.erp.dao.impl.CustPnoDaoImpl;
import cn.joymates.erp.domain.CustPno;

public class CustPnoService extends BaseService<CustPno> {
	public CustPnoService(){
		dao = new CustPnoDaoImpl();
	}
}
