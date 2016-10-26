package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import model.InstantlyInfoBean;
import model.InstantlyInfoDAO;

public class InstantlyInfoDAOJdbc implements InstantlyInfoDAO {
	private DataSource dataSource;

	public InstantlyInfoDAOJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final String SELECT_BY_ID = "select * from InstantlyInfo where stockId=?";

	@Override
	public InstantlyInfoBean select(String stockId) {
		InstantlyInfoBean result = null;

		ResultSet rset = null;
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);) {
			stmt.setString(1, stockId);
			rset = stmt.executeQuery();
			if (rset.next()) {
				result = new InstantlyInfoBean();
				result.setStockId(rset.getString("stockId"));
				result.setStockName(rset.getString("stockName"));
				result.setFinalPrice(rset.getDouble("finalPrice"));
				result.setTemporalVolume(rset.getInt("temporalVolume"));
				result.setVolume(rset.getInt("volume"));
				result.setInfomationTime(rset.getString("infomationTime"));
				result.setInfomationDate(rset.getString("infomationDate"));
				result.setHigh(rset.getDouble("high"));
				result.setLow(rset.getDouble("low"));
				result.setOpenPrice(rset.getDouble("openPrice"));
				result.setA(rset.getString("a"));
				result.setF(rset.getString("f"));
				result.setB(rset.getString("b"));
				result.setG(rset.getString("g"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	private static final String SELECT_ALL = "select * from InstantlyInfo";

	@Override
	public List<InstantlyInfoBean> select() {
		List<InstantlyInfoBean> result = null;

		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rset = stmt.executeQuery();) {
			result = new ArrayList<InstantlyInfoBean>();
			while (rset.next()) {
				InstantlyInfoBean bean = new InstantlyInfoBean();
				bean.setStockId(rset.getString("stockid"));
				bean.setStockName(rset.getString("stockname"));
				bean.setFinalPrice(rset.getDouble("finalPrice"));
				bean.setTemporalVolume(rset.getInt("temporalVolume"));
				bean.setVolume(rset.getInt("volume"));
				bean.setInfomationTime(rset.getString("infomationTime"));
				bean.setInfomationDate(rset.getString("infomationDate"));
				bean.setHigh(rset.getDouble("high"));
				bean.setLow(rset.getDouble("low"));
				bean.setOpenPrice(rset.getDouble("openPrice"));
				bean.setA(rset.getString("a"));
				bean.setF(rset.getString("f"));
				bean.setB(rset.getString("b"));
				bean.setG(rset.getString("g"));
				result.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String INSERT = "insert into InstantlyInfo (stockId, stockname, finalPrice, temporalVolume, volume, infomationTime, infomationDate, high, low, openPrice, limitUpPoint, limitDownPoint, closingPrice, a, f, b, g) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	@Override
	public InstantlyInfoBean insert(InstantlyInfoBean bean) {
		InstantlyInfoBean result = null;
		if (bean != null) {
			try (Connection conn = dataSource.getConnection();
					PreparedStatement stmt = conn.prepareStatement(INSERT);) {
				stmt.setString(1, bean.getStockId());
				stmt.setString(2, bean.getStockName());
				stmt.setDouble(3, bean.getFinalPrice());
				stmt.setInt(4, bean.getTemporalVolume());
				stmt.setInt(5, bean.getVolume());
				stmt.setString(6, bean.getInfomationTime());
				stmt.setString(7, bean.getInfomationDate());
				stmt.setDouble(8, bean.getHigh());
				stmt.setDouble(9, bean.getLow());
				stmt.setDouble(10, bean.getOpenPrice());
				stmt.setString(14, bean.getA());
				stmt.setString(15, bean.getF());
				stmt.setString(16, bean.getB());
				stmt.setString(17, bean.getG());

				int i = stmt.executeUpdate();
				if (i == 1) {
					result = bean;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private static final String UPDATE = "update InstantlyInfo set stockname=?, finalPrice=?, temporalVolume=? , volume=?, infomationTime=?, infomationDate=?, high=?, low=?, openPrice=?, limitUpPoint=?, limitDownPoint=?, closingPrice=?, a=?, f=?, b=?, g=? where stockId=?";

	@Override
	public InstantlyInfoBean update(String stockName, Double finalPrice, Integer temporalVolume, Integer volume,
			String infomationTime, String infomationDate, Double high, Double low, Double openPrice, String a, String f,
			String b, String g, String stockId) {
		InstantlyInfoBean result = null;
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE);) {
			stmt.setString(1, stockName);
			stmt.setDouble(2, finalPrice);
			stmt.setInt(3, temporalVolume);
			stmt.setInt(4, volume);
			stmt.setString(5, infomationTime);
			stmt.setString(6, infomationDate);
			stmt.setDouble(7, high);
			stmt.setDouble(8, low);
			stmt.setDouble(9, openPrice);
			stmt.setString(13, a);
			stmt.setString(14, f);
			stmt.setString(15, b);
			stmt.setString(16, g);
			stmt.setString(17, stockId);

			int i = stmt.executeUpdate();
			if (i == 1) {
				result = this.select(stockId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String DELETE = "delete from InstantlyInfo where stockId=?";

	@Override
	public boolean delete(String stockId) {
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE);) {
			stmt.setString(1, stockId);
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
