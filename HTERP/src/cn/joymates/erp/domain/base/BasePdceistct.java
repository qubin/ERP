package cn.joymates.erp.domain.base;

import java.math.BigDecimal;
import java.util.Date;

public class BasePdceistct extends BaseVO {
	

	public BasePdceistct() {
		tablename = "t_pdce_istct";
		
		fieldMap.put("uuid", "id");
		fieldMap.put("sDetailId", "s_detail_id");
		fieldMap.put("startDate", "start_date");
		fieldMap.put("pdceCount", "pdce_count");
		fieldMap.put("scpCount", "scp_count");
		fieldMap.put("usedMatWeight", "used_mat_weight");
		fieldMap.put("matScpWeight", "mat_scp_weight");
		fieldMap.put("pdctPerson", "pdct_person");
		fieldMap.put("qcPerson", "qc_person");
		fieldMap.put("isOver", "is_over");
		fieldMap.put("overRemark", "over_remark");
		fieldMap.put("overPerson", "over_person");
		fieldMap.put("remark", "remark");
		fieldMap.put("remark1", "remark1");
		fieldMap.put("materialId", "material_id");
		fieldMap.put("endDate", "end_date");
		fieldMap.put("productId", "product_id");
		fieldMap.put("verifyStatus", "verify_status");
		fieldMap.put("verifyRemark", "verify_remark");
		
		this.id = "id";
	}
	private Integer uuid;
	private Integer sDetailId;
	private Date startDate;
	private Integer pdceCount;
	private Integer scpCount;
	private BigDecimal usedMatWeight;
	private BigDecimal matScpWeight;
	private String pdctPerson;
	private String qcPerson;
	private String isOver;
	private String overRemark;
	private String overPerson;
	private String remark;
	private String remark1;
	private Integer materialId;
	private Date endDate;
	private Integer productId;
	private String verifyStatus;
	private String verifyRemark;
	
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public Integer getsDetailId() {
		return sDetailId;
	}
	public void setsDetailId(Integer sDetailId) {
		this.sDetailId = sDetailId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Integer getPdceCount() {
		return pdceCount;
	}
	public void setPdceCount(Integer pdceCount) {
		this.pdceCount = pdceCount;
	}
	public Integer getScpCount() {
		return scpCount;
	}
	public void setScpCount(Integer scpCount) {
		this.scpCount = scpCount;
	}
	public BigDecimal getUsedMatWeight() {
		return usedMatWeight;
	}
	public void setUsedMatWeight(BigDecimal usedMatWeight) {
		this.usedMatWeight = usedMatWeight;
	}
	public BigDecimal getMatScpWeight() {
		return matScpWeight;
	}
	public void setMatScpWeight(BigDecimal matScpWeight) {
		this.matScpWeight = matScpWeight;
	}
	public String getPdctPerson() {
		return pdctPerson;
	}
	public void setPdctPerson(String pdctPerson) {
		this.pdctPerson = pdctPerson;
	}
	public String getQcPerson() {
		return qcPerson;
	}
	public void setQcPerson(String qcPerson) {
		this.qcPerson = qcPerson;
	}
	public String getIsOver() {
		return isOver;
	}
	public void setIsOver(String isOver) {
		this.isOver = isOver;
	}
	public String getOverRemark() {
		return overRemark;
	}
	public void setOverRemark(String overRemark) {
		this.overRemark = overRemark;
	}
	public String getOverPerson() {
		return overPerson;
	}
	public void setOverPerson(String overPerson) {
		this.overPerson = overPerson;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public Integer getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getVerifyStatus() {
		return verifyStatus;
	}
	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus;
	}
	public String getVerifyRemark() {
		return verifyRemark;
	}
	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}
}
