package model;

public class StockCompanyBean {
	private String stockId;
	private String establishDate;
	private String publicCompanyDate;
	private String chairman;
	private String generalManager;
	private String spokesman;
	private String capitalStock;
	private String cashDividend;
	private String stockDividend;
	private String sdre;
	private String sdcr;
	private String smDate;
	private String serviceAgent;
	private String companyPhone;
	private String ofRevenue;
	private String url;
	private String factory;
	
	private StockInfoBean stockInfo;
	public StockInfoBean getStockInfo() {
		return stockInfo;
	}
	public void setStockInfo(StockInfoBean stockInfo) {
		this.stockInfo = stockInfo;
	}
	
	@Override
	public String toString() {
		return "StockCompanyBean [stockId=" + stockId + ", establishDate=" + establishDate + ", publicCompanyDate="
				+ publicCompanyDate + ", chairman=" + chairman + ", generalManager=" + generalManager + ", spokesman="
				+ spokesman + ", capitalStock=" + capitalStock + ", cashDividend=" + cashDividend + ", stockDividend="
				+ stockDividend + ", sdre=" + sdre + ", sdcr=" + sdcr + ", smDate=" + smDate + ", serviceAgent="
				+ serviceAgent + ", companyPhone=" + companyPhone + ", ofRevenue=" + ofRevenue + ", url=" + url
				+ ", factory=" + factory + "]";
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getEstablishDate() {
		return establishDate;
	}
	public void setEstablishDate(String establishDate) {
		this.establishDate = establishDate;
	}
	public String getPublicCompanyDate() {
		return publicCompanyDate;
	}
	public void setPublicCompanyDate(String publicCompanyDate) {
		this.publicCompanyDate = publicCompanyDate;
	}
	public String getChairman() {
		return chairman;
	}
	public void setChairman(String chairman) {
		this.chairman = chairman;
	}
	public String getGeneralManager() {
		return generalManager;
	}
	public void setGeneralManager(String generalManager) {
		this.generalManager = generalManager;
	}
	public String getSpokesman() {
		return spokesman;
	}
	public void setSpokesman(String spokesman) {
		this.spokesman = spokesman;
	}
	public String getCapitalStock() {
		return capitalStock;
	}
	public void setCapitalStock(String capitalStock) {
		this.capitalStock = capitalStock;
	}
	public String getCashDividend() {
		return cashDividend;
	}
	public void setCashDividend(String cashDividend) {
		this.cashDividend = cashDividend;
	}
	public String getStockDividend() {
		return stockDividend;
	}
	public void setStockDividend(String stockDividend) {
		this.stockDividend = stockDividend;
	}
	public String getSdre() {
		return sdre;
	}
	public void setSdre(String sdre) {
		this.sdre = sdre;
	}
	public String getSdcr() {
		return sdcr;
	}
	public void setSdcr(String sdcr) {
		this.sdcr = sdcr;
	}
	public String getSmDate() {
		return smDate;
	}
	public void setSmDate(String smDate) {
		this.smDate = smDate;
	}
	public String getServiceAgent() {
		return serviceAgent;
	}
	public void setServiceAgent(String serviceAgent) {
		this.serviceAgent = serviceAgent;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getOfRevenue() {
		return ofRevenue;
	}
	public void setOfRevenue(String ofRevenue) {
		this.ofRevenue = ofRevenue;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
}
