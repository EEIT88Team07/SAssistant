package model.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.RSVBean;
import model.RSVDAO;

public class RSVDAOHibernate implements RSVDAO {

	private SessionFactory sessionFactory = null;

	public RSVDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings({ "unchecked" })
	public List<RSVBean> select() {

		Query query = this.getSession().createQuery("from RSVBean");
		return (List<RSVBean>) query.list();

	}

	@SuppressWarnings({ "unchecked", "null" })
	public List<RSVBean> select(String stockId, Date start, Date end) {

		if (stockId != null || stockId.length() != 0) {
			if (start != null) {
				if (end != null) {
					Query query = this.getSession().createQuery(
							"from RSVBean D where D.stockId = :stock_Id AND D.buildDate >= :start AND D.buildDate <= :end");
					query.setParameter("stock_Id", stockId);
					query.setParameter("start", new SimpleDateFormat("yyyy/MM/dd").format(start));
					query.setParameter("end", new SimpleDateFormat("yyyy/MM/dd").format(end));
					return (List<RSVBean>) query.list();
				} else if (end == null) {
					Query query = this.getSession().createQuery(
							"from RSVBean D where D.stockId = :stock_Id AND D.buildDate >= :start");
					query.setParameter("stock_Id", stockId);
					query.setParameter("start", new SimpleDateFormat("yyyy/MM/dd").format(start));
					return (List<RSVBean>) query.list();

				}

			} else if (start == null) {
				if (end != null) {// !=!

					Query query = this.getSession()
							.createQuery("from RSVBean D where D.stockId = :stock_Id AND D.buildDate <= :end");
					query.setParameter("stock_Id", stockId);
					query.setParameter("end", new SimpleDateFormat("yyyy/MM/dd").format(end));

					return (List<RSVBean>) query.list();
				} else if (end == null) {// !==
					Query query = this.getSession().createQuery("from RSVBean D where D.stockId = :stock_Id");
					query.setParameter("stock_Id", stockId);
					return (List<RSVBean>) query.list();
				}
			}

		} else if (stockId == null || stockId.length() == 0) {
			if (start != null) {
				if (end != null) {// =!!
					Query query = this.getSession()
							.createQuery("from RSVBean D where D.buildDate >= :start AND D.buildDate <= :end");
					query.setParameter("start", new SimpleDateFormat("yyyy/MM/dd").format(start));
					query.setParameter("end", new SimpleDateFormat("yyyy/MM/dd").format(end));
					return (List<RSVBean>) query.list();
				} else if (end == null) {
					// =!=
					Query query = this.getSession().createQuery("from RSVBean D where D.buildDate >= :start");
					query.setParameter("start", new SimpleDateFormat("yyyy/MM/dd").format(start));
					return (List<RSVBean>) query.list();
				}

			} else if (start == null) {
				if (end != null) {
					// ==!
					Query query = this.getSession().createQuery("from RSVBean D where D.buildDate <= :end");
					query.setParameter("end", new SimpleDateFormat("yyyy/MM/dd").format(end));
					return (List<RSVBean>) query.list();
				} else if (end == null) {
					// ===
					Query query = this.getSession().createQuery("from RSVBean");
					return (List<RSVBean>) query.list();
				}
			}
		}
		return null;
	}
	
	
	
	public RSVBean insert(RSVBean rSVBean) {
		RSVBean bean = new RSVBean();
		bean.setStockId(rSVBean.getStockId());
		bean.setBuildDate(rSVBean.getBuildDate());
		bean = (RSVBean) this.getSession().get(RSVBean.class, bean);
		if (bean == null) {
			this.getSession().save(rSVBean);
			return rSVBean;
		}
		return null;
	}

	public boolean delete(String stockId, Date buildDate) {
		RSVBean bean = new RSVBean();
		bean.setStockId(stockId);
		bean.setBuildDate(new SimpleDateFormat("yyyy-MM-dd").format(buildDate));
		bean = (RSVBean) this.getSession().get(RSVBean.class, bean);
		if (bean != null) {
			this.getSession().delete(bean);
			return true;
		}
		return false;
	}

	public RSVBean update(String stockId, String buildDate, Double rSV, Double k, Double d) {
		RSVBean bean = new RSVBean();
		bean.setStockId(stockId);
		bean.setBuildDate(buildDate);

		bean = (RSVBean) this.getSession().get(RSVBean.class, bean);
		if (bean != null) {

			bean.setRSV(rSV);
			bean.setK(k);
			bean.setD(d);

		}
		return bean;
	}

	@SuppressWarnings("unchecked")
	public List<Object> selectBySQL(String sql) {
		List<Object> databeans = null;
		if (sql != null) {
			Query query = this.getSession().createSQLQuery(sql);
			databeans = query.list();
		}
		return databeans;

	}

}
