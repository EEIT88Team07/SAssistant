package model;

import java.util.Date;
import java.util.List;

public interface InstantlyInfoDAO {

	// 查詢
	public abstract List<InstantlyInfoBean> select();

	public abstract InstantlyInfoBean select(String stockIdName);

	// 新增
	public abstract InstantlyInfoBean insert(InstantlyInfoBean instantlyInfoBean);

	// 刪除
	public abstract boolean delete(String stockIdName);

	// 修改
	public abstract InstantlyInfoBean update(String stockIdName, Date time, String finalPrice, Integer volume, String yestPrice, String buy, String sell, String openPrice, String high, String low);

}
