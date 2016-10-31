package model;

import java.util.List;

public interface MembersDAO {
	public abstract MembersBean select(String account);

	public abstract MembersBean insert(MembersBean bean);

	boolean update(String password, String name, Integer Gender, String Phone, String email, java.util.Date birth,
			String account);

	public boolean delete(String account);

	public abstract List<MembersBean> select();
}
