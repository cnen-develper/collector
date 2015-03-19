/**
 *DangdangParser.java
 *Version1.0
 *2015-3-16
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.website.dangdang;

import java.text.SimpleDateFormat;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnendata.dev.collector.model.Product;
import com.cnendata.dev.collector.parser.IParser;
import com.cnendata.dev.util.Strings;

/**
 * 当当网商品详情解析器<br>
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
public class DangdangParser implements IParser {
	private static Logger logger = LoggerFactory
			.getLogger(DangdangParser.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cnendata.dev.parser.IParser#parse(org.jsoup.nodes.Document)
	 */
	public Product parse(Document doc) {

		try {
			Product book = new Product();
			Element element = doc.getElementsByClass("show_info_autoheight")
					.get(0);
			Element headEl = element.getElementsByClass("head").get(0);
			if (headEl.getElementsByClass("subtitle").size() > 0) {
				headEl.select(".subtitle").remove();
			}
			String name = headEl.text();
			// subtitle
			Element priceEl = element.getElementsByClass("sale").get(0);
			String priceTag = Strings.getCnCharacter(priceEl
					.getElementsByClass("show_info_left").text());
			if (priceTag.contains("抢购价")) {
				String price = priceEl.getElementById("promo_price").attr(
						"prpr");
				book.setShopPrice(Float.valueOf(price));
			} else if (priceTag.contains("当当价")) {
				String price = priceEl.getElementById("salePriceTag").text();
				book.setShopPrice(Float.valueOf(price));
			}
			Elements bookInfoEls = doc.getElementsByClass("book_messbox")
					.get(0).getElementsByClass("clearfix");
			for (int i = 0; i < bookInfoEls.size(); i++) {
				Element tmpEl = bookInfoEls.get(i);
				try {
					String tagSrc = tmpEl.getElementsByClass("show_info_left")
							.get(0).text();
					String tagCn = Strings.getCnCharacter(tagSrc);
					String value = tmpEl.getElementsByClass("show_info_right")
							.get(0).text();
					if (tagCn.contains("作者")) {
						book.setAuthor(value);
					} else if (tagCn.contains("出版社")) {
						book.setPublisher(value);
					} else if (tagCn.contains("出版时间")) {
						book.setPublishDate(new SimpleDateFormat("yyyy-MM-dd")
								.parse(value));
					} else if (tagCn == null || tagCn.equals("")) {
						if (tagSrc.contains("ＩＳＢＮ")) {
							book.setIsbn(value);
						}
					}
				} catch (Exception e) {
					logger.error("parse Document book_messbox error", e);
				}
			}
			Element contentEl = doc.getElementById("content")
					.getElementsByClass("descrip").get(0)
					.getElementsByTag("textarea").get(0);
			try {
				contentEl.select("span").remove();
				contentEl.select("strong").remove();
			} catch (Exception e) {

			}
			String[] tmp = contentEl.text().split("p");
			if (tmp.length == 1) {
				book.setDescript(tmp[0]);
			} else {
				book.setDescript(tmp[1]);
			}

			book.setName(name);
			return book;
		} catch (Exception e) {
			logger.error("parse Document error ", e);
		}
		return null;

	}
}
