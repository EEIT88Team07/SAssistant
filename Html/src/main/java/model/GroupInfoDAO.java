package model;

import java.util.List;

public interface GroupInfoDAO {

	public abstract List<GroupInfoBean> select();

	
	public abstract GroupInfoBean select(String groupName);

	// 新增
	public abstract GroupInfoBean insert(GroupInfoBean groupInfoBean);

	// 刪除
	public abstract boolean delete(String groupName);

	// 修改
	public abstract GroupInfoBean update(String groupName,String groupId);
	
	
}
