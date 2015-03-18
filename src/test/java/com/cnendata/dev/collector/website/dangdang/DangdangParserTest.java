package com.cnendata.dev.collector.website.dangdang;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnendata.dev.collector.model.Product;
import com.google.gson.Gson;

public class DangdangParserTest extends TestCase {
	private static Logger logger = LoggerFactory
			.getLogger(DangdangParserTest.class);

	public void testParse() throws Exception {
		// http://product.dangdang.com/22628370.html 抢购价url
		// http://product.dangdang.com/20861145.html 当当价url
		Document doc = Jsoup
				.connect("http://product.dangdang.com/20861145.html")// http://product.dangdang.com/22553729.html
				.timeout(60000).get();
		Product book = new DangdangParser().parse(doc);
		logger.debug(new Gson().toJson(book));
		// System.out.println("name:" + book.getName() + "\r\nshopPrice:"
		// + book.getShopPrice() + "\r\n作者：" + book.getAuthor());
		Assert.assertNotNull(book);
	}

}
