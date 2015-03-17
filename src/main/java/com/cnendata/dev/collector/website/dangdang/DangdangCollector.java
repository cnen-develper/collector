/**
 *DangdangCollector.java
 *Version1.0
 *2015-3-16
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.website.dangdang;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnendata.dev.collector.model.MyUrl;
import com.cnendata.dev.collector.parser.ICollector;
import com.cnendata.dev.collector.queue.UrlQueue;

/**
 * descript<br>
 * <!--<br>
 * 历史记录：<br>
 * --------------------------------------------------------
 * 2015-3-16,enilu(82552623@qq.com)新建文档<br>
 * 
 * -->
 * 
 * @author enilu(82552623@qq.com)
 * 
 *         since1.0
 */
public class DangdangCollector implements ICollector {
	private static Logger logger = LoggerFactory
			.getLogger(DangdangCollector.class);
	String url = "http://category.dangdang.com/cp01.00.00.00.00.00.html";

	public void collect() {
		this.collectCate1(url);
	}

	/**
	 * 根据入口url获取每个大类的url
	 * 
	 * @param url
	 */
	private void collectCate1(String url) {

		try {
			Document doc = Jsoup.connect(url).timeout(60000).get();
			Elements lis = doc.getElementById("leftCate")
					.getElementsByTag("ul").get(0).getElementsByTag("li");
			for (int i = 0; i < lis.size(); i++) {
				String url1 = lis.get(i).getElementsByTag("a").get(0)
						.attr("href");
				logger.debug("level 1:" + url1);
				this.collectCate2(url1);
			}
		} catch (IOException e) {
			logger.error("collector dangdang main url error", e);
		}

	}

	/**
	 * 根据每个大类的url，获取子类的url
	 * 
	 * @param url
	 */
	private void collectCate2(String url1) {

		try {
			Document doc = Jsoup.connect(url1).timeout(60000).get();
			Elements lis = doc.getElementById("leftCate")
					.getElementsByClass("on").get(0).getElementsByTag("div")
					.get(0).getElementsByTag("span");
			for (int i = 0; i < lis.size(); i++) {
				String url2 = lis.get(i).getElementsByTag("a").get(0)
						.attr("href");
				logger.debug("level 2:" + url2);
				this.collectCate3(url2);
			}
		} catch (IOException e) {
			logger.error("collector dangdang main url error", e);
		}

	}

	/**
	 * 根据小类url，获取商品列表页面的商品详情url，并放到url队列中
	 * 
	 * @param url3
	 */
	private void collectCate3(String url3) {

		try {
			Document doc = Jsoup.connect(url3).timeout(60000).get();
			Elements lis = doc.getElementById("leftCate")
					.getElementsByClass("on").get(0).getElementsByTag("div")
					.get(0).getElementsByTag("span");
			for (int i = 0; i < lis.size(); i++) {
				String url4 = lis.get(i).getElementsByTag("a").get(0)
						.attr("href");
				UrlQueue.getInstance().push(new MyUrl(null, url4));

			}
		} catch (IOException e) {
			logger.error("collector dangdang main url error", e);
		}

	}

}
