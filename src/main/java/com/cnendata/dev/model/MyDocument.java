/**
 *MyDocument.java
 *Version1.0
 *2015-3-16
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.model;

import org.jsoup.nodes.Document;

/**
 * 文档对象<br>
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
public class MyDocument {
	/**
	 * 配置为parser解析器的类名
	 */
	private String type;
	private Document doc;

	public MyDocument(String type, Document doc) {
		super();
		this.type = type;
		this.doc = doc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

}
