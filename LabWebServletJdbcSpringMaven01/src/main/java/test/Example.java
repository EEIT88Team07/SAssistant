package test;

import java.net.URL;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Example {
	public static void main(String[] args) throws Exception {
		Document doc = Jsoup.parse(new URL("http://isin.twse.com.tw/isin/C_public.jsp?strMode=2"), 3000);
		for (int i = 0; i < doc.select("tr").size(); i++) {
			Iterator<Element> iterator = doc.select("tr").get(i).select("td").iterator();
			if (doc.select("tr").get(i).childNodeSize() > 6) {
				if ("ESVUFR".equals(doc.select("tr").get(i).child(5).text())) {
					while (iterator.hasNext()) {
						System.out.println(iterator.next().parent().childNode(0).childNode(0).toString().substring(0,4).trim());
						System.out.println(iterator.next().parent().childNode(0).childNode(0).toString().substring(5).replaceAll("　",""));
						System.out.println(iterator.next().parent().childNode(1).childNode(0).toString());
						System.out.println(iterator.next().parent().childNode(4).childNode(0).toString());
						break;
					}
				}
			}
		}
	}
}