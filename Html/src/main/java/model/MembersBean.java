package model;

import java.util.Set;

public class MembersBean {
	private String account;
	private String password;
	private String name;
	private java.util.Date birthday;
	private Integer gender;
	private String email;
	private String phone;

	private Set<MyFavouriteBean> myFavourite;
	

	
	
	public Set<MyFavouriteBean> getMyFavourite() {
		return myFavourite;
	}

	public void setMyFavourite(Set<MyFavouriteBean> myFavourite) {
		this.myFavourite = myFavourite;
	}

	private Set<PurchaseHistoryBean> purchaseHistory;

	public Set<PurchaseHistoryBean> getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(Set<PurchaseHistoryBean> purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}

	private Set<SellingHistoryBean> sellingHistory;

	public Set<SellingHistoryBean> getSellingHistory() {
		return sellingHistory;
	}

	public void setSellingHistory(Set<SellingHistoryBean> sellingHistory) {
		this.sellingHistory = sellingHistory;
	}



	@Override
	public String toString() {
		return "MembersBean [account=" + account + ", password=" + password + ", name=" + name + ", birthday="
				+ birthday + ", gender=" + gender + ", email=" + email + ", phone=" + phone + ", myFavourite="
				+ myFavourite + ", purchaseHistory=" + purchaseHistory + ", sellingHistory=" + sellingHistory + "]";
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.util.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
