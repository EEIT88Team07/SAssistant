package model;

import java.sql.Date;

public class SellingHistoryBean {
	private Integer sellingNumber;
	private String stockId;
	private Date dateOfPurchase;
	private Double sellingPrice;
	private Double sellingQuantity;
	private Date dateOfSelling;
	private Double cost;
	private Double income;
	private Double netIncome;
	private Double netProfitMargin;
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
		return "SellingHistoryBean [sellingnumber=" + sellingNumber + ", stockid=" + stockId + ", dateofpurchase="
				+ dateOfPurchase + ", sellingprice=" + sellingPrice + ", sellingquantity=" + sellingQuantity
				+ ", sateofselling=" + dateOfSelling + ", cost=" + cost + ", income=" + income + ", netincome="
				+ netIncome + ", netprofitmargin=" + netProfitMargin + "]";
	}

	public Integer getSellingNumber() {
		return sellingNumber;
	}

	public void setSellingNumber(Integer sellingNumber) {
		this.sellingNumber = sellingNumber;
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
	
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
}
