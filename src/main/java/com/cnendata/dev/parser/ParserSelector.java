/**
 *ParserSelector.java
 *Version1.0
 *2015-3-16
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.parser;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 解析器选择器<br>
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
public class ParserSelector {
	private static Map<String, AbstractParser> map = Maps.newConcurrentMap();
	private static ParserSelector instance;

	public static ParserSelector getInstance() {
		if (instance == null) {
			instance = new ParserSelector();

		}
		return instance;
	}

	public AbstractParser getParser(String type) {
		String className = "com.cnendata.dev.parser." + type;
		AbstractParser parser = map.get(className);
		if (parser == null) {
			try {
				parser = (AbstractParser) Class.forName(className)
						.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return parser;
	}
}
