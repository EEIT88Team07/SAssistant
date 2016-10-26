package model;

import java.util.List;

public interface MyFavouriteDAO {
	public abstract MyFavouriteBean select(MyFavouriteBean bean) ;
	
	public abstract List<MyFavouriteBean> select();
	
	public abstract MyFavouriteBean insert(MyFavouriteBean bean);
	
	public abstract boolean update(MyFavouriteBean bean);

	public abstract boolean delete(MyFavouriteBean bean);
}
