package model;

public class MyFavouriteBean implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String stockId;
	private String account;
	private Integer volume;
	private Double finalPrice;
	private Double openPrice;
	private Double high;
	private Double low;
	private String annotation;
	
	private MembersBean members;
	public MembersBean getMembers() {
		return members;
	}
	public void setMembers(MembersBean members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return "MyFavouriteBean [StockId=" + stockId + ", Account=" + account + ", Volume=" + volume + ", FinalPrice="
				+ finalPrice + ", OpenPrice=" + openPrice + ", High=" + high + ", Low=" + low + ", Annotation="
				+ annotation + "]";
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Double getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(Double openPrice) {
		this.openPrice = openPrice;
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

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
}
