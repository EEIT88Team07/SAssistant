package model;

import java.sql.Date;

public class PurchaseHistoryBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer purchaseNumber;
	private String stockId;
	private Date dateOfPurchase;
	private Double purchasePrice;
	private Double purchaseQuantity;
	private Double investment;
	private Double stopLossLimit;
	private Double takeProfitLimit;
	private Double dividendYield;
	private String account;
	
	private MembersBean members;	
	public MembersBean getMembers() {
		return members;
	}
	public void setMembers(MembersBean members) {
		this.members = members;
	}
	
	@Override
	public String toString() {
		return "PurchaseHistoryBean [stockid=" + stockId + ", dateofpurchase=" + dateOfPurchase + ", purchaseprice="
				+ purchasePrice + ", purchasequantity=" + purchaseQuantity + ", investment=" + investment
				+ ", stoplosslimit=" + stopLossLimit + ", takeprofitlimit=" + takeProfitLimit + ", dividendyield="
				+ dividendYield + ", account=" + account + "]";
	}
	public Integer getPurchaseNumber() {
		return purchaseNumber;
	}
	public void setPurchaseNumber(Integer purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public Double getPurchaseQuantity() {
		return purchaseQuantity;
	}
	public void setPurchaseQuantity(Double purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}
	public Double getInvestment() {
		return investment;
	}
	public void setInvestment(Double investment) {
		this.investment = investment;
	}
	public Double getStopLossLimit() {
		return stopLossLimit;
	}
	public void setStopLossLimit(Double stopLossLimit) {
		this.stopLossLimit = stopLossLimit;
	}
	public Double getTakeProfitLimit() {
		return takeProfitLimit;
	}
	public void setTakeProfitLimit(Double takeProfitLimit) {
		this.takeProfitLimit = takeProfitLimit;
	}
	public Double getDividendYield() {
		return dividendYield;
	}
	public void setDividendYield(Double dividendYield) {
		this.dividendYield = dividendYield;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
}