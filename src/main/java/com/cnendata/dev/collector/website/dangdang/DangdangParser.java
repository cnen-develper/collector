/**
 *DangdangParser.java
 *Version1.0
 *2015-3-16
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.website.dangdang;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnendata.dev.collector.model.Product;
import com.cnendata.dev.collector.parser.AbstractParser;
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
public class DangdangParser extends AbstractParser {
	private static Logger logger = LoggerFactory
			.getLogger(DangdangParser.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cnendata.dev.parser.AbstractParser#parse(org.jsoup.nodes.Document)
	 */
	@Override
	public Product parse(Document doc) {

		try {
			Product book = new Product();
			Element element = doc.getElementsByClass("show_info_autoheight")
					.get(0);
			String name = element.getElementsByClass("head").get(0).text();
			Element priceEl = element.getElementsByClass("sale").get(0);
			String priceTag = Strings.get(
					priceEl.getElementsByClass("show_info_left").text()
							.replaceAll("\\s", ""), "([\u4e00-\u9fa5]{1,4})");
			if (priceTag.contains("抢购价")) {
				String price = priceEl.getElementById("promo_price").attr(
						"prpr");
				book.setShopPrice(Float.valueOf(price));
			} else if (priceTag.contains("当当价")) {
				String price = priceEl.getElementById("salePriceTag").text();
				book.setShopPrice(Float.valueOf(price));
			}

			book.setName(name);
			logger.info("parse book");
			return book;
		} catch (Exception e) {
			logger.error("parse Document error ", e);
		}
		return null;

	}
}
