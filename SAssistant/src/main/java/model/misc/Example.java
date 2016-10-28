package model.misc;

import java.io.IOException;

import org.jsoup.Jsoup;

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
}