package com.cnendata.dev.collector.website.dangdang;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.cnendata.dev.collector.model.Product;

public class DangdangParserTest extends TestCase {

	public void testParse() throws Exception {
		// http://product.dangdang.com/22628370.html 抢购价url
		// http://product.dangdang.com/20861145.html 当当价url
		Document doc = Jsoup
				.connect("http://product.dangdang.com/20861145.html")
				.timeout(60000).get();
		Product book = new DangdangParser().parse(doc);
		System.out.println("name:" + book.getName() + "\r\nshopPrice:"
				+ book.getShopPrice());
		Assert.assertNotNull(book);
	}

}
