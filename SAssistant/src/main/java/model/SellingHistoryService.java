package model;

import java.util.ArrayList;
import java.util.List;

public class SellingHistoryService {
	private SellingHistoryDAO sellingHistoryDao = null;

	public SellingHistoryService(SellingHistoryDAO sellingHistoryDao) {
		this.sellingHistoryDao = sellingHistoryDao;
	}

	

	public List<SellingHistoryBean> select(SellingHistoryBean bean) {
		List<SellingHistoryBean> result = null;
		if (bean != null && bean.getSellingNumber() != null) {
			SellingHistoryBean temp = sellingHistoryDao.select(bean.getSellingNumber());
			if (temp != null) {
				result = new ArrayList<SellingHistoryBean>();
				result.add(temp);
			}
		} else {
			result = sellingHistoryDao.select();
		}
		return result;
	}

	public SellingHistoryBean insert(SellingHistoryBean bean) {
		SellingHistoryBean result = null;
		if (bean != null) {
			result = sellingHistoryDao.insert(bean);
		}
		return result;
	}

	public SellingHistoryBean update(SellingHistoryBean bean) {
		SellingHistoryBean result = null;
		if (bean != null) {
			result = sellingHistoryDao.update(bean.getSellingPrice(), bean.getSellingQuantity(), bean.getDateOfSelling(), bean.getCost(), bean.getIncome(),
					bean.getNetIncome(), bean.getNetProfitMargin(), bean.getPurchaseNumber(), bean.getSellingNumber());			
		}
		return result;
	}

	public boolean delete(SellingHistoryBean bean) {
		boolean result = false;
		if (bean != null) {
			result = sellingHistoryDao.delete(bean.getSellingNumber());
		}
		return result;
	}
}