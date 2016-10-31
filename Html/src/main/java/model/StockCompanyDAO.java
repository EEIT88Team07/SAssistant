package model;

import java.util.List;

public interface StockCompanyDAO {
	public abstract StockCompanyBean select(String stockId);

	public abstract StockCompanyBean insert(StockCompanyBean bean);
	
	public abstract StockCompanyBean update(StockCompanyBean bean);

	public abstract boolean delete(String stockId);

	public abstract List<StockCompanyBean> select();
}
