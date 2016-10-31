package model.misc;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Example {
	public String Page(){
		String html = null;
		try {
			html = Jsoup.connect("http://cmoney.pixnet.net/blog/post/167268636-學習地圖---新手投資股票入門理財的出發點").get().select("div[class='article-content-inner']").outerHtml();
		} catch (IOException e) {
			return "網站忙碌中......請重新整理，無法解決問題可在下方留言版留言，我們會儘快處理，謝謝!!";
		}
		return html;
	}
	public Elements Real(){
		Elements doc = null;
		try {
			doc = Jsoup.connect("https://tw.stock.yahoo.com/rss/url/d/e/N2.html").userAgent("Mozilla").get().select("item");
			
		} catch (IOException e) {
			System.out.println(e);
		}	
		return doc;
	}
	public static void main(String [] args){
		try {
			Elements doc = Jsoup.connect("https://tw.stock.yahoo.com/rss/url/d/e/N2.html").userAgent("Mozilla").get().select("item");
			for(int i = 0;i<doc.size();i++){
				String t = doc.select("title").get(i).text();
				String l = doc.select("link").get(i).text();
				System.out.println(t);
				System.out.println(l);
			}		
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}