package model;

import java.io.Serializable;

public class RSVBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String stockId;
	private String BuildDate;
	private Double rSV;
	private Double k;
	private Double d;

	@Override
	public String toString() {
		return "RSVBean [stockId=" + stockId + ", BuildDate=" + BuildDate + ", rSV=" + rSV + ", k=" + k + ", d=" + d
				+ "]";
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getBuildDate() {
		return BuildDate;
	}

	public void setBuildDate(String buildDate) {
		BuildDate = buildDate;
	}

	public Double getRSV() {
		return rSV;
	}

	public void setRSV(Double rSV) {
		this.rSV = rSV;
	}

	public Double getK() {
		return k;
	}

	public void setK(Double k) {
		this.k = k;
	}

	public Double getD() {
		return d;
	}

	public void setD(Double d) {
		this.d = d;
	}
}
