package model.misc;

import java.util.Date;
import java.util.Iterator;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Example {
	public static void main(String[] args) throws Exception {
		String url = "http://mis.twse.com.tw/stock/fibest.jsp?stock=1101&lang=zh-TW";
		HttpClient client = new HttpClient();
		client.executeMethod(new GetMethod(url));
		Document doc = Jsoup.connect("http://mis.twse.com.tw/stock/api/getStockInfo.jsp?json=1&delay=0&ex_ch=tse_1101.tw&_=" + new Date().getTime()).get();
		System.out.println(doc);
//		for (int i = 0; i < doc.select("tr").size(); i++) {
//			Iterator<Element> iterator = doc.select("tr").get(i).select("td").iterator();
//			if (doc.select("tr").get(i).childNodeSize() > 6) {
//				if ("ESVUFR".equals(doc.select("tr").get(i).child(5).text())) {
//					while (iterator.hasNext()) {
//						System.out.println(iterator.next().parent().childNode(0).childNode(0).toString().substring(0,4).trim());
//						System.out.println(iterator.next().parent().childNode(0).childNode(0).toString().substring(5).replaceAll("　",""));
//						System.out.println(iterator.next().parent().childNode(1).childNode(0).toString());
//						System.out.println(iterator.next().parent().childNode(4).childNode(0).toString());
//						break;
//					}
//				}
//			}
//		}
	}
}