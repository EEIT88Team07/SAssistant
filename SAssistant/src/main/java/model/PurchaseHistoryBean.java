package model;

import java.util.Set;

public class PurchaseHistoryBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String purchaseNumber;
	private String stockId;
	private java.util.Date dateOfPurchase;
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
	
	
	private Set<SellingHistoryBean> sellingHistory;

	public Set<SellingHistoryBean> getSellingHistory() {
		return sellingHistory;
	}
	public void setSellingHistory(Set<SellingHistoryBean> sellingHistory) {
		this.sellingHistory = sellingHistory;
	}


	
	
	
	@Override
	public String toString() {
		return "PurchaseHistoryBean [purchaseNumber=" + purchaseNumber + ", stockId=" + stockId + ", dateOfPurchase=" + dateOfPurchase + ", purchasePrice=" + purchasePrice + ", purchaseQuantity="
				+ purchaseQuantity + ", investment=" + investment + ", stopLossLimit=" + stopLossLimit + ", takeProfitLimit=" + takeProfitLimit + ", dividendYield=" + dividendYield + ", account="
				+ account + ", members=" + members + ", sellingHistory=" + sellingHistory.size() + "]";
	}
	public String getPurchaseNumber() {
		return purchaseNumber;
	}
	public void setPurchaseNumber(String purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public java.util.Date getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(java.util.Date date) {
		this.dateOfPurchase = date;
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