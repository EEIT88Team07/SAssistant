package model;

import java.util.ArrayList;
import java.util.List;

public class PurchaseHistoryService {
	private PurchaseHistoryDAO purchaseHistoryDAO;

	public PurchaseHistoryService(PurchaseHistoryDAO purchaseHistoryDAO) {
		this.purchaseHistoryDAO = purchaseHistoryDAO;
	}


	public List<PurchaseHistoryBean> select(PurchaseHistoryBean bean) {
		List<PurchaseHistoryBean> result = null;
		if (bean != null && bean.getPurchaseNumber() != null) {
			PurchaseHistoryBean temp = purchaseHistoryDAO.select(bean.getPurchaseNumber());
			if (temp != null) {
				result = new ArrayList<PurchaseHistoryBean>();
				result.add(temp);
			}
		} else {
			result = purchaseHistoryDAO.select();
		}
		return result;
	}

	public List<PurchaseHistoryBean> selectByAccount(String account) {
		List<PurchaseHistoryBean> result = null;
		if (account != null) {
			result = purchaseHistoryDAO.selectByAccount(account);
		}
		return result;
	}

	public PurchaseHistoryBean insert(PurchaseHistoryBean bean) {
		PurchaseHistoryBean result = null;
		if (bean != null) {
			result = purchaseHistoryDAO.insert(bean);
		}
		return result;
	}


	public PurchaseHistoryBean update(PurchaseHistoryBean bean) {
		PurchaseHistoryBean result = null;
		if (bean != null) {

			result = purchaseHistoryDAO.update(bean);
		}
		return result;
	}

	public boolean delete(PurchaseHistoryBean bean) {
		boolean result = false;
		if (bean != null) {
			result = purchaseHistoryDAO.delete(bean);
		}
		return result;
	}
}