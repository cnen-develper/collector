/**
 *AmazoneCollector.java
 *Version1.0
 *2015-3-16
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.website.dangdang.nvzhuang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnendata.dev.collector.parser.ICollector;
import com.cnendata.dev.collector.queue.IQueue;
import com.cnendata.dev.collector.task.UrlTask;

/**
 * descript<br>
 * <!--<br>
 * 历史记录：<br>
 * --------------------------------------------------------
 * -->
 * 
 * @author myan
 * 
 *         since1.0
 */
public class DangdangNvZhuangCollector implements ICollector {
	private static Logger logger = LoggerFactory
			.getLogger(DangdangNvZhuangCollector.class);
	// 女装
	private String url = "/cid4002778.html";
	private String BaseUrl = "http://category.dangdang.com";
	private IQueue uqlQueue;

	private Map<Integer, String> pageNumMap = new HashMap<Integer, String>();
	private List<Integer> pageNumList = new ArrayList<Integer>();

	public void collect(IQueue uqlQueue) {
		this.uqlQueue = uqlQueue;
		getNextPageURL(BaseUrl + url, true, 1);
		this.collectItemUrl();
	}

	//得到下一页的url
	private String getNextPageURL(String aurl, boolean isGetMaxNum, int aPageNum) {
		try {
			Document doc = Jsoup.connect(aurl).timeout(60000).get();
			Elements lis = doc.getElementById("content").getElementsByClass(
					"content_list");

			// get paging flag
			Elements eleLis = doc.getElementsByClass("paging").get(0)
					.getElementsByTag("ul").get(0).getElementsByTag("li");

			String nextUrl = "";
			for (Element eleLi : eleLis) {
				String txtA = eleLi.getElementsByTag("a").text();

				try {
					if (isGetMaxNum) {
						pageNumList.add(new Integer(Integer.parseInt(txtA)));
						pageNumMap.put(new Integer(Integer.parseInt(txtA)),
								eleLi.getElementsByTag("a").attr("href"));
					}
					if (aPageNum == Integer.parseInt(txtA))
						nextUrl = eleLi.getElementsByTag("a").attr("href");
				} catch (Exception e) {
					logger.warn("pageNum convert to integer error, value: "
							+ txtA);
				}

				if (txtA.equals("上一页") || txtA.equals("下一页"))
					continue;
			}
			return nextUrl;

		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 得到每件服装的url
	 * 
	 * @param url
	 */
	private void collectItemUrl() {
		int sum = 0;

		//一共100页，服装
		for (int i = 0; i < 100; i++) {
			logger.debug("the page num is: " + (i+1));
			
			try {
				Document doc = Jsoup.connect(BaseUrl + url)
						.timeout(60000).get();
				Elements lis = doc.getElementById("content")
						.getElementsByClass("content_list");

				// get item url
				for (int j = 0; j < lis.size(); j++) {
					Elements elesShopBox = lis.get(j).getElementsByClass(
							"shop_box");

					for (Element eleShopbox : elesShopBox) {
						String tmpUrl = eleShopbox
								.getElementsByClass("shop_box_inner").get(0)
								.getElementsByTag("a").attr("href");
						uqlQueue.push(new UrlTask(
								Class.forName("com.cnendata.dev.collector.website.dangdang.nvzhuang.DangdangNvZhuangParser")
								, tmpUrl));
						logger.debug("dangdang NvZhuang item url: " + tmpUrl);
						sum++;
					}
				}
				
			} catch (Exception e) {
				logger.error("collector dangdang NvZhuang item url error", e);
			}
			
			url = getNextPageURL(BaseUrl + url, false, i+1);
			logger.debug("next page url: " + BaseUrl + url);
			
			break;
		}
		
		
		//for test
//		try {
//			uqlQueue.push(new UrlTask(Class.forName("com.cnendata.dev.collector.website.dangdang.nvzhuang.DangdangNvZhuangParser"), 
//					"http://product.dangdang.com/10"
//			+ "84802430.html#ddclick?act=click&pos=0_0_0_m&cat=4002778&key=&qinfo=&pinfo=&minfo=103125_6_58&ninfo=&custid=&permid=&ref=&rcount=&t"
//			+ "ype=&t=1427709580000"));
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		logger.info("collect from dandang Nvzhuang total count: " + sum);		
	}

}
