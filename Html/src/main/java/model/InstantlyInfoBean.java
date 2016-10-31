package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="InstantlyInfo")
public class InstantlyInfoBean {
	@Id
	private String stockId;
	private String stockName;
	private Double finalPrice;
	private Integer temporalVolume;
	private Integer volume;
	private String infomationTime;
	private String infomationDate;
	private Double high;
	private Double low;
	private Double openPrice;
	private String a;
	private String f;
	private String b;
	private String g;

	@Override
	public String toString() {
		return "InstantlyInfoBean [stockId=" + stockId + ", stockName=" + stockName + ", finalPrice=" + finalPrice
				+ ", temporalVolume=" + temporalVolume + ", volume=" + volume + ", infomationTime=" + infomationTime
				+ ", infomationDate=" + infomationDate + ", high=" + high + ", low=" + low + ", openPrice=" + openPrice
				+ ", a=" + a + ", f=" + f + ", b=" + b + ", g=" + g + "]";
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Integer getTemporalVolume() {
		return temporalVolume;
	}

	public void setTemporalVolume(Integer temporalVolume) {
		this.temporalVolume = temporalVolume;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public String getInfomationTime() {
		return infomationTime;
	}

	public void setInfomationTime(String infomationTime) {
		this.infomationTime = infomationTime;
	}

	public String getInfomationDate() {
		return infomationDate;
	}

	public void setInfomationDate(String infomationDate) {
		this.infomationDate = infomationDate;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(Double openPrice) {
		this.openPrice = openPrice;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getG() {
		return g;
	}

	public void setG(String g) {
		this.g = g;
	}
}
