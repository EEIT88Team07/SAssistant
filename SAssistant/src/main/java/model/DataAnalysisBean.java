package model;

import java.io.Serializable;

public class DataAnalysisBean implements Serializable {
	private static final long serialVersionUID = 2118822944676880992L;
	private String stockId;// pk
	private String buildDate;// pk
	private Double openPrice;
	private Double closingPrice;
	private Long turnOverInValue;
	private String changeInPrice;
	private Long tradingVolume;
	private Integer numberOfTransactions;
	private Double highestPrice;
	private Double lowestPrice;
	private Double refDividendYield;

	private StockInfoBean stockInfo;
	
	public StockInfoBean getStockInfo() {
		return stockInfo;
	}

	public void setStockInfo(StockInfoBean stockInfo) {
		this.stockInfo = stockInfo;
	}

	@Override
	public String toString() {
		return "DataAnalysisBean [stockId=" + stockId + ", buildDate=" + buildDate + ", openPrice=" + openPrice
				+ ", closingPrice=" + closingPrice + ", turnOverInValue=" + turnOverInValue + ", changeInPrice="
				+ changeInPrice + ", tradingVolume=" + tradingVolume + ", numberOfTransactions=" + numberOfTransactions
				+ ", highestPrice=" + highestPrice + ", lowestPrice=" + lowestPrice + ", refDividendYield="
				+ refDividendYield + "]";
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getBuildDate() {
		return buildDate;
	}

	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}

	public Double getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(Double openPrice) {
		this.openPrice = openPrice;
	}

	public Double getClosingPrice() {
		return closingPrice;
	}

	public void setClosingPrice(Double closingPrice) {
		this.closingPrice = closingPrice;
	}

	public Long getTurnOverInValue() {
		return turnOverInValue;
	}

	public void setTurnOverInValue(Long turnOverInValue) {
		this.turnOverInValue = turnOverInValue;
	}

	public String getChangeInPrice() {
		return changeInPrice;
	}

	public void setChangeInPrice(String changeInPrice) {
		this.changeInPrice = changeInPrice;
	}

	public Long getTradingVolume() {
		return tradingVolume;
	}

	public void setTradingVolume(Long tradingVolume) {
		this.tradingVolume = tradingVolume;
	}

	public Integer getNumberOfTransactions() {
		return numberOfTransactions;
	}

	public void setNumberOfTransactions(Integer numberOfTransactions) {
		this.numberOfTransactions = numberOfTransactions;
	}

	public Double getHighestPrice() {
		return highestPrice;
	}

	public void setHighestPrice(Double highestPrice) {
		this.highestPrice = highestPrice;
	}

	public Double getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(Double lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public Double getRefDividendYield() {
		return refDividendYield;
	}

	public void setRefDividendYield(Double refDividendYield) {
		this.refDividendYield = refDividendYield;
	}
}
