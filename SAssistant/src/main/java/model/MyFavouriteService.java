package model;

import java.util.ArrayList;
import java.util.List;

public class MyFavouriteService {
	private MyFavouriteDAO myFavouriteDAO;

	public MyFavouriteService(MyFavouriteDAO myFavouriteDAO) {
		this.myFavouriteDAO = myFavouriteDAO;
	}// spring 控制反轉

	

	public List<MyFavouriteBean> select(MyFavouriteBean bean) {
		List<MyFavouriteBean> result = null;
		if (bean != null && bean.getAccount().trim() != null) {
			MyFavouriteBean temp = myFavouriteDAO.select(bean);
			if (temp != null) {
				result = new ArrayList<MyFavouriteBean>();
				result.add(temp);
			}
		} else {
			result = myFavouriteDAO.select();
		}
		return result;
	}

	public MyFavouriteBean insert(MyFavouriteBean bean) {
		MyFavouriteBean result = null;
		if (bean != null) {
			result = myFavouriteDAO.insert(bean);
		}
		return result;
	}

	public boolean update(MyFavouriteBean bean) {
		// String password,String name,Integer Gender,String Phone,Integer
		// Permissions,Integer number, String email, java.util.Date birth,
		// String account
		if (bean != null) {
			myFavouriteDAO.update(bean);
			return true;
		}
		return false;
	}

	public boolean delete(MyFavouriteBean bean) {
		boolean result = false;
		if (bean != null) {
			result = myFavouriteDAO.delete(bean);
		}
		return result;
	}
}
