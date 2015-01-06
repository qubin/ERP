package cn.joymates.erp.domain.base;

import java.math.BigDecimal;

public class BaseSellDetail extends BaseVO {
	public BaseSellDetail(){
		tablename = "t_sell_details";
		fieldMap.put("sellDetailId", "id");
		fieldMap.put("sbId", "sell_bill_id");
		fieldMap.put("sdCode", "sell_detail_code");
		fieldMap.put("cpn", "cus_pdct_no");
		fieldMap.put("price", "price");
		fieldMap.put("amount", "amount");
		fieldMap.put("orderCount", "order_count");
		fieldMap.put("remark", "remark");
		fieldMap.put("remark1", "remark1");
		this.id = "id";
	}
	
	private Integer sellDetailId;
	private Integer sbId;
	private String sdCode;
	private String cpn;
	private BigDecimal price;
	private BigDecimal amount;
	private Integer orderCount;
	private String remark;
	private String remark1;
	
	public Integer getSellDetailId() {
		return sellDetailId;
	}
	public void setSellDetailId(Integer sellDetailId) {
		this.sellDetailId = sellDetailId;
	}
	public Integer getSbId() {
		return sbId;
	}
	public void setSbId(Integer sbId) {
		this.sbId = sbId;
	}
	public String getSdCode() {
		return sdCode;
	}
	public void setSdCode(String sdCode) {
		this.sdCode = sdCode;
	}
	public String getCpn() {
		return cpn;
	}
	public void setCpn(String cpn) {
		this.cpn = cpn;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
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
}
