package model;

import java.util.List;

public interface PurchaseHistoryBeanDAO {
	public abstract PurchaseHistoryBean select(PurchaseHistoryBean bean);

	public abstract List<PurchaseHistoryBean> select();

	public abstract PurchaseHistoryBean insert(PurchaseHistoryBean bean);

	public abstract PurchaseHistoryBean update(PurchaseHistoryBean bean);

	public abstract boolean delete(PurchaseHistoryBean bean);
}