package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GroupInfoService {

	public static void main(String[] args) throws IOException {

		ApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext("beans.config.xml");
			SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
			sessionFactory.getCurrentSession().beginTransaction();

			GroupInfoService groupInfoService = (GroupInfoService) context.getBean("groupInfoService");

			StockInfoService stockInfoService = (StockInfoService) context.getBean("stockInfoService");

			HashMap<String, List<String>> groups = groupInfoService.getGroupInfos(stockInfoService.select(null));
			
			System.out.println(groups);
			
			Set keys = groups.keySet();
			
			System.out.println(keys);
			
			
			// groupInfoService.insertGroupInfo(groupInfoService.getOptions(),
			// service.select_All_Group());

//			groupInfoService.getGroupInfos(stockInfoService.select(null));

//			System.out.println(groupInfoService.select(null));
			
			
			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
	}

	private GroupInfoDAO groupInfoDAO;

	public GroupInfoDAO getGroupInfoDAO() {
		return groupInfoDAO;
	}

	public GroupInfoService(GroupInfoDAO groupInfoDAO) {
		this.groupInfoDAO = groupInfoDAO;
	}

	
	
	
	public List<GroupInfoBean> select(GroupInfoBean bean){
		List<GroupInfoBean> result = null;
		if (bean != null && bean.getGroupName() != null) {
			GroupInfoBean temp = groupInfoDAO.select(bean.getGroupName());
			if (temp != null) {
				result = new ArrayList<GroupInfoBean>();
				result.add(temp);
			}
		} else {
			result = groupInfoDAO.select();
		}

		return result;
	}
	
	//取得每個產業的股票ID{產業名稱:[ID,ID,ID]}
	@SuppressWarnings("null")
	public HashMap<String, List<String>> getGroupInfos(List<StockInfoBean> stockInfoBeans) {

		HashMap<String, List<String>> groupInfos = new HashMap<String, List<String>>();

		List<GroupInfoBean> groupbeans = groupInfoDAO.select();

		if (groupbeans != null) {
			for (int i = 0; i < groupbeans.size(); i++) {
				List<String> groupStockIds = new ArrayList();
				for (int k = 0; k < stockInfoBeans.size(); k++) {
					if (groupbeans.get(i).getGroupName().equals(stockInfoBeans.get(k).getGroupName())) {
						groupStockIds.add(stockInfoBeans.get(k).getStockId());
					}
				}
				groupInfos.put(groupbeans.get(i).getGroupName(), groupStockIds);
			}
			return groupInfos;
		} else {
			return null;
		}
	}

	public List<String> getGroupId() {

		List<String> groupIds = new ArrayList();

		List<GroupInfoBean> groupbeans = groupInfoDAO.select();

		for (int i = 0; i < groupbeans.size(); i++) {
			groupIds.add(groupbeans.get(i).getGroupId());
		}
		return groupIds;
	}

	public void insertGroupInfo(HashMap<String, String> options, List<String> groups) {

		if (options != null && groups != null) {

			for (int i = 0; i < groups.size(); i++) {
				GroupInfoBean groupInfoBean = new GroupInfoBean();
				groupInfoBean.setGroupName(groups.get(i));
				groupInfoBean.setGroupId(options.get(groups.get(i)));
				groupInfoDAO.insert(groupInfoBean);

			}
		}
	}

	public HashMap<String, String> getOptions() {

		String targetURL = "http://www.twse.com.tw/ch/trading/exchange/MI_INDEX/MI_INDEX.php";

		HashMap<String, String> options = null;
		try {
			Document doc = Jsoup.connect(targetURL).userAgent("Mozilla").get();

			options = new HashMap();

			for (int i = 0; i < doc.select("select").get(2).childNodeSize() - 2; i++) {
				options.put(doc.select("select").get(2).child(i).text(), doc.select("select").get(2).child(i).val());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//
		return options;
	}

}
