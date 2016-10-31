package model;

import java.sql.Date;
import java.util.List;

public interface SellingHistoryDAO {
	public abstract SellingHistoryBean select(int sellingNumber);

	public abstract List<SellingHistoryBean> select();

	public abstract SellingHistoryBean insert(SellingHistoryBean bean);

	public abstract SellingHistoryBean update(Double sellingPrice, Double sellingQuantity, Date dateOfSelling,
			Double cost, Double income, Double netIncome, Double netProfitMargin, int sellingNumber);

	public abstract boolean delete(int sellingNumber);
}
