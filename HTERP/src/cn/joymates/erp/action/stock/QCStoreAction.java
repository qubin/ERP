package cn.joymates.erp.action.stock;

import java.util.List;
import java.util.Map;
import cn.joymates.erp.action.BaseAction;
import cn.joymates.erp.domain.QCStore;
import cn.joymates.erp.service.QCStoreService;
import cn.joymates.erp.utils.ServiceProxyFactory;

public class QCStoreAction extends BaseAction {

	public String showHome() {
		if (qcstore == null) {
			qcstore = new QCStore();
		}
		List<Map<String, Object>> qcStoreList = service.find(qcstore,product_key,product_name,ec_rd,req);
		req.setAttribute("qcStoreList", qcStoreList);
		//req.setAttribute("logoutMap", QCStore.logoutMap);
		return "home";
	}
	
	public String find() {
		if (qcstore == null) {
			qcstore = new QCStore();
		}
		List<Map<String, Object>> qcStoreList = service.find(qcstore,product_key,product_name,ec_rd,req);
		req.setAttribute("qcStoreList", qcStoreList);
		return "home";
	}
	
	public String showAddUI() {
		return "addUI";
	}
	
	public String add() {

		return showHome();
	}
	
	public String showModifyUI() {
		return "modifyUI";
	}
	
	public String delete(){

		return showHome();
	}
	
	public String modify() {
		return showHome();
	}
	
	

	
	
	
	
	
	
	
	
	
	private static final long serialVersionUID = 1L;
	private QCStoreService service = ServiceProxyFactory.getInstanceNoMybatis(new QCStoreService());
	private QCStore qcstore;
	private String product_key;
	private String product_name;
	public QCStoreService getService() {
		return service;
	}

	public void setService(QCStoreService service) {
		this.service = service;
	}

	public QCStore getQcstore() {
		return qcstore;
	}

	public void setQcstore(QCStore qcstore) {
		this.qcstore = qcstore;
	}

	public String getProduct_key() {
		return product_key;
	}

	public void setProduct_key(String product_key) {
		this.product_key = product_key;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
}
