package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "InstantlyInfo")
public class InstantlyInfoBean {
	@Id
	private String stockIdName;
	private String time;
	private String finalPrice;
	private Integer volume;
	private String yestPrice;
	private String buy;
	private String sell;
	private String openPrice;
	private String high;
	private String low;

	@Override
	public String toString() {
		return "InstantlyInfoBean [stockIdName=" + stockIdName + ", time=" + time + ", finalPrice=" + finalPrice + ", volume=" + volume + ", yestPrice=" + yestPrice + ", buy=" + buy + ", sell=" + sell
				+ ", openPrice=" + openPrice + ", high=" + high + ", low=" + low + "]";
	}

	public String getStockIdName() {
		return stockIdName;
	}

	public void setStockIdName(String stockIdName) {
		this.stockIdName = stockIdName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(String finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public String getYestPrice() {
		return yestPrice;
	}

	public void setYestPrice(String yestPrice) {
		this.yestPrice = yestPrice;
	}

	public String getBuy() {
		return buy;
	}

	public void setBuy(String buy) {
		this.buy = buy;
	}

	public String getSell() {
		return sell;
	}

	public void setSell(String sell) {
		this.sell = sell;
	}

	public String getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(String openPrice) {
		this.openPrice = openPrice;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

}