package model;

import java.util.List;

public interface StockInfoDAO {
	public abstract StockInfoBean select(String stockId);

	public abstract List<StockInfoBean> select();

	public abstract List<String> select_All_Id();

	public abstract List<String> select_All_Group();

	public abstract StockInfoBean insert(StockInfoBean bean);

	public abstract StockInfoBean update(String stockName, String groupName, String isinCode, String stockId);

	public abstract boolean delete(String stockId);
}
