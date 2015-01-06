package cn.joymates.erp.domain.base;

import java.math.BigDecimal;

public class BaseSellDetail extends BaseVO {
	public BaseSellDetail(){
		tablename = "t_sell_details";
		fieldMap.put("sellDetailId", "id");
		fieldMap.put("sbId", "sell_bill_id");
		fieldMap.put("cpId", "cust_pdct_id");
		fieldMap.put("price", "price");
		fieldMap.put("amount", "amount");
		fieldMap.put("orderCount", "order_count");
		fieldMap.put("remark", "remark");
		fieldMap.put("remark1", "remark1");
		this.id = "id";
	}
	
	private Integer sellDetailId;
	private Integer sbId;
	private Integer cpId;
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
	
	public Integer getCpId() {
		return cpId;
	}
	public void setCpId(Integer cpId) {
		this.cpId = cpId;
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
