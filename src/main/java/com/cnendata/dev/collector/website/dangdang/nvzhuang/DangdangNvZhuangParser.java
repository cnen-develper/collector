/**
 *DangdangParser.java
 *Version1.0
 *2015-3-16
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.website.dangdang.nvzhuang;

import java.text.SimpleDateFormat;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnendata.dev.collector.model.Book;
import com.cnendata.dev.collector.model.NvZhuang;
import com.cnendata.dev.collector.model.Product;
import com.cnendata.dev.collector.parser.IParser;
import com.cnendata.dev.util.Strings;

/**
 * 当当网女装详情解析器<br>
 * <!--<br>
 * 历史记录：<br>
 * --------------------------------------------------------
 * 
 * -->
 * 
 * @author myan
 * 
 *         since1.0
 */
public class DangdangNvZhuangParser implements IParser {
	private static Logger logger = LoggerFactory
			.getLogger(DangdangNvZhuangParser.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cnendata.dev.parser.IParser#parse(org.jsoup.nodes.Document)
	 */
	public Product parse(Document doc) {

		try {
			NvZhuang item = new NvZhuang();
			Element element = doc.getElementsByClass("show_info_autoheight")
					.get(0);
			Element headEl = element.getElementsByClass("head").get(0);
			if (headEl.getElementsByClass("subtitle").size() > 0) {
				headEl.select(".subtitle").remove();
			}
			
			String name = headEl.text();
			item.setName(name);
			
			// subtitle
			Element priceEl = element.getElementsByClass("sale").get(0);
			String priceTag = Strings.getCnCharacter(priceEl
					.getElementsByClass("show_info_left").text());
			if (priceTag.contains("抢购价")) {
				String price = priceEl.getElementById("promo_price").attr(
						"prpr");
				item.setShopPrice(Float.valueOf(price));
			} else if (priceTag.contains("当当价")) {
				String price = priceEl.getElementById("salePriceTag").text();
				item.setShopPrice(Float.valueOf(price));
			}
			//市场价
			Element shiChangPriceEl = element.getElementsByClass("sale").get(1);
			String shiChangPriceTag = Strings.getCnCharacter(shiChangPriceEl
					.getElementsByClass("show_info_left").text());
			if (shiChangPriceTag.contains("市场价")) {
				String price = shiChangPriceEl.getElementById("originalPriceTag").text();
				item.setPrice(Float.valueOf(price));
			}
			
			Elements eleMallStyles = doc.getElementById("detail_all").getElementsByAttribute("mall_goods_foursort_style_frame");
			for (Element eleMallStyle : eleMallStyles) {
				String value = eleMallStyle.getElementsByTag("a").get(0).text();
				if ( (value.indexOf("品牌") > 0) ){
					item.setPinPai(value);
				}else if ( value.indexOf("版型") > 0 ){
					item.setBanXing(value);
				}else if ( value.indexOf("款式") > 0 ){
					item.setKuanShi(value);
				}else if ( value.indexOf("图案") > 0 ){
					item.setKuanShi(value);
				}else if ( value.indexOf("领型") > 0 ){
					item.setLinXing(value);
				}else if ( value.indexOf("衣长") > 0 ){
					item.setYiChang(value);
				}else if ( value.indexOf("厚度") > 0 ){
					item.setHouDu(value);
				}
			}
			
			logger.info(item.toString());
			
			return item;
		} catch (Exception e) {
			logger.error("parse Document error ", e);
			return null;
		}

	}
}
