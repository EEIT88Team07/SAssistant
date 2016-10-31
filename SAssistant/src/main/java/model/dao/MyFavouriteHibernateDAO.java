package model.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.MyFavouriteBean;
import model.MyFavouriteDAO;

public class MyFavouriteHibernateDAO implements MyFavouriteDAO {
	private SessionFactory sessionFactory = null;// bean

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public MyFavouriteHibernateDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public MyFavouriteBean select(MyFavouriteBean bean) {
		return this.getSession().get(MyFavouriteBean.class, bean);// 一般儲存方法
	}

	@Override
	public MyFavouriteBean insert(MyFavouriteBean bean) {
		MyFavouriteBean result = (MyFavouriteBean) // 檢查有沒有東西 才能儲存
		this.getSession().get(MyFavouriteBean.class, bean);
		if (result == null) {
			this.getSession().save(bean);
		}
		return bean;
	}
	// 複合主鍵的HQL select語法
	// new一個Bean物件後將複合主鍵set進去
	// ProductBean p = new ProductBean();
	// p.setProductId(101);
	// p.setPrice(25000);

	// 將Bean物件做為select用的參數塞進去
	// p = session.get(ProductBean.class, p);
	@Override
	public boolean update(MyFavouriteBean bean) {
		MyFavouriteBean bean2 = new MyFavouriteBean();
		bean2.setAccount(bean.getAccount());
		bean2.setStockId(bean.getStockId());
		MyFavouriteBean result = this.getSession().get(MyFavouriteBean.class, bean2);
		if (result != null) {
			result.setAnnotation(bean.getAnnotation());
			result.setFinalPrice(bean.getFinalPrice());
			result.setHigh(bean.getHigh());
			result.setLow(bean.getLow());
			result.setOpenPrice(bean.getOpenPrice());
			result.setVolume(bean.getVolume());
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(MyFavouriteBean bean) {
		// 有問題 需要重寫
		MyFavouriteBean result = (MyFavouriteBean) this.getSession().get(MyFavouriteBean.class, bean);
		if (bean != null) {
			this.getSession().delete(result);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MyFavouriteBean> select() {
		Query query = this.getSession().createQuery("from MyFavouriteBean");
		return (List<MyFavouriteBean>) query.list();
	}
}
