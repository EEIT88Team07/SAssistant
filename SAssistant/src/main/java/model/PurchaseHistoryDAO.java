package model;

import java.util.List;

public interface PurchaseHistoryDAO {
	public abstract PurchaseHistoryBean select(String purchaseNumber);

	public abstract List<PurchaseHistoryBean> selectByAccount(String account);
	
	public abstract List<PurchaseHistoryBean> select();

	public abstract PurchaseHistoryBean insert(PurchaseHistoryBean bean);

	public abstract PurchaseHistoryBean update(PurchaseHistoryBean bean);

	public abstract boolean delete(PurchaseHistoryBean bean);
}