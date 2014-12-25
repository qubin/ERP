package cn.joymates.erp.domain.base;

import java.math.BigDecimal;
import java.util.Date;

public class BaseTransFlow extends BaseVO {
	

	public BaseTransFlow() {
		tablename = "t_trans_flow";
		
		fieldMap.put("uuid", "id");
		fieldMap.put("transDate", "trans_date");
		fieldMap.put("person", "person");
		fieldMap.put("matOrPdct", "mat_or_pdct");
		fieldMap.put("matPdctId", "mat_pdct_id");
		fieldMap.put("srcWhSign", "src_wh_sign");
		fieldMap.put("tgtWhSign", "tgt_wh_sign");
		fieldMap.put("remark", "remark");
		
		this.id = "id";
	}
	private Integer uuid;
	private Date transDate;
	private String person;
	private String matOrPdct;
	private Integer matPdctId;
	private Integer srcWhSign;
	private Integer tgtWhSign;
	private String remark;
	
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getMatOrPdct() {
		return matOrPdct;
	}
	public void setMatOrPdct(String matOrPdct) {
		this.matOrPdct = matOrPdct;
	}
	public Integer getMatPdctId() {
		return matPdctId;
	}
	public void setMatPdctId(Integer matPdctId) {
		this.matPdctId = matPdctId;
	}
	public Integer getSrcWhSign() {
		return srcWhSign;
	}
	public void setSrcWhSign(Integer srcWhSign) {
		this.srcWhSign = srcWhSign;
	}
	public Integer getTgtWhSign() {
		return tgtWhSign;
	}
	public void setTgtWhSign(Integer tgtWhSign) {
		this.tgtWhSign = tgtWhSign;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
