package cn.joymates.erp.domain.base;


public class BasetFinancePay extends BaseVO {
	public BasetFinancePay(){
		tablename = "t_finance_pay";
		fieldMap.put("uuid", "id");
		fieldMap.put("sellBillId", "sell_bill_id");
		fieldMap.put("prepaid", "prepaid");
		fieldMap.put("financeVerifyStatus", "finance_verify_status");
		fieldMap.put("financeVerifyRemark", "finance_verify_remark");
		this.id = "id";
	}
	
	private Integer uuid;
	private Integer sellBillId;
	private String prepaid;
	private String financeVerifyStatus;
	private String financeVerifyRemark;
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public Integer getSellBillId() {
		return sellBillId;
	}
	public void setSellBillId(Integer sellBillId) {
		this.sellBillId = sellBillId;
	}
	public String getPrepaid() {
		return prepaid;
	}
	public void setPrepaid(String prepaid) {
		this.prepaid = prepaid;
	}
	public String getFinanceVerifyStatus() {
		return financeVerifyStatus;
	}
	public void setFinanceVerifyStatus(String financeVerifyStatus) {
		this.financeVerifyStatus = financeVerifyStatus;
	}
	public String getFinanceVerifyRemark() {
		return financeVerifyRemark;
	}
	public void setFinanceVerifyRemark(String financeVerifyRemark) {
		this.financeVerifyRemark = financeVerifyRemark;
	}
	
}
