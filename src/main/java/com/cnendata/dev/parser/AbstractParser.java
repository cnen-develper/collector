/**
 *AbstractParser.java
 *Version1.0
 *2015-3-16
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.parser;

import org.jsoup.nodes.Document;

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
public abstract class AbstractParser {
	public abstract void parse(Document doc);
}
