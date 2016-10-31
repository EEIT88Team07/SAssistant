package model;

import java.util.Date;
import java.util.List;

public interface SellingHistoryDAO {
	public abstract SellingHistoryBean select(String sellingNumber);

	public abstract List<SellingHistoryBean> select();

	public abstract SellingHistoryBean insert(SellingHistoryBean bean);

	public abstract SellingHistoryBean update(Double sellingPrice, Double sellingQuantity, Date dateOfSelling, Double cost, Double income, Double netIncome,
			Double netProfitMargin, String purchaseNumber, String sellingNumber);

	public abstract boolean delete(String sellingNumber);
}
