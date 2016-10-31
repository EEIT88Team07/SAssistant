package model;

import java.util.Date;
import java.util.List;

public interface RSVDAO {

	public abstract List<RSVBean> select();
	public abstract List<RSVBean> select(String stockId, Date start, Date end);

	
	
	
	public abstract List<Object> selectBySQL(String sql);

	
	// 新增
	public abstract RSVBean insert(RSVBean rSVBean);

	// 刪除
	public abstract boolean delete(String stockId, java.util.Date buildDate);

	// 修改
	public abstract RSVBean update(String stockId, String buildDate, Double rSV, Double k, Double d);

}
