package model;

import java.util.Date;

public class SellingHistoryBean {
	private String sellingNumber;
	private Double sellingPrice;
	private Double sellingQuantity;
	private Date dateOfSelling;
	private Double cost;
	private Double income;
	private Double netIncome;
	private Double netProfitMargin;
	private String purchaseNumber;
	
	private PurchaseHistoryBean purchaseHistory;

	

	public PurchaseHistoryBean getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(PurchaseHistoryBean purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}

	
	
	
	@Override
	public String toString() {
		return "SellingHistoryBean [sellingNumber=" + sellingNumber + ", sellingPrice=" + sellingPrice + ", sellingQuantity=" + sellingQuantity + ", dateOfSelling=" + dateOfSelling + ", cost=" + cost
				+ ", income=" + income + ", netIncome=" + netIncome + ", netProfitMargin=" + netProfitMargin + ", purchaseNumber=" + purchaseNumber + ", purchaseHistory=" + purchaseHistory + "]";
	}

	public String getSellingNumber() {
		return sellingNumber;
	}

	public void setSellingNumber(String sellingNumber) {
		this.sellingNumber = sellingNumber;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Double getSellingQuantity() {
		return sellingQuantity;
	}

	public void setSellingQuantity(Double sellingQuantity) {
		this.sellingQuantity = sellingQuantity;
	}

	public Date getDateOfSelling() {
		return dateOfSelling;
	}

	public void setDateOfSelling(Date dateOfSelling) {
		this.dateOfSelling = dateOfSelling;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getNetIncome() {
		return netIncome;
	}

	public void setNetIncome(Double netIncome) {
		this.netIncome = netIncome;
	}

	public Double getNetProfitMargin() {
		return netProfitMargin;
	}

	public void setNetProfitMargin(Double netProfitMargin) {
		this.netProfitMargin = netProfitMargin;
	}

	public String getPurchaseNumber() {
		return purchaseNumber;
	}

	public void setPurchaseNumber(String purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}

	
	
	
	
}
	