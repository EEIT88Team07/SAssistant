package model;

import java.util.ArrayList;
import java.util.List;

public class MembersService {
	private MembersDAO membersDAO;

	public MembersService(MembersDAO membersDAO) {
		this.membersDAO = membersDAO;
	}// spring 控制反轉

	
	public List<MembersBean> select(MembersBean bean) {
		List<MembersBean> result = null;
		if (bean != null && bean.getAccount().trim() != null) {
			MembersBean temp = membersDAO.select(bean.getAccount());
			if (temp != null) {
				result = new ArrayList<MembersBean>();
				result.add(temp);
			}
		} else {
			result = membersDAO.select();
		}
		return result;
	}
	
	public MembersBean checkIDPassword(String account, String password) {
		// 透過變數dao，呼叫它的select()方法，要傳入參數 id。將傳回值放入變數
		// MemberBean mb 內。
		MembersBean mb = membersDAO.select(account);
		// 如果mb不等於 null 而且參數 password等於mb內的password) {
		if (mb != null && password.equals(mb.getPassword())) {
			// 傳回 mb物件，同時結束本方法
			return mb;
		}
		// 傳回null物件
		return null;
	}

	public MembersBean insert(MembersBean bean) {
		MembersBean result = null;
		if (bean != null) {
			result = membersDAO.insert(bean);
		}
		return result;
	}

	public boolean update(MembersBean bean) {
		// String password,String name,Integer Gender,String Phone,Integer
		// Permissions,Integer number, String email, java.util.Date birth,
		// String account
		if (bean != null) {
			membersDAO.update(bean.getPassword(), bean.getName(), bean.getGender(), bean.getPhone(), bean.getEmail(),
					bean.getBirthday(), bean.getAccount());
			return true;
		}
		return false;
	}

	public boolean delete(String account) {
		boolean result = false;
		if (account != null && account.trim() != null) {
			result = membersDAO.delete(account);
		}
		return result;
	}
}
