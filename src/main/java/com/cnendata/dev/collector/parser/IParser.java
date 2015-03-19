/**
 *AbstractParser.java
 *Version1.0
 *2015-3-16
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.parser;

import org.jsoup.nodes.Document;

import com.cnendata.dev.collector.model.Product;

/**
 * 抽象的商品详情解析类<br>
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
public interface IParser {
	public abstract Product parse(Document doc);
}
