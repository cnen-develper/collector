/**
 *Product.java
 *Version1.0
 *2015-3-17
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.model;

import java.io.File;
import java.io.Serializable;

/**
 * 商品信息<br>
 * <!--<br>
 * 历史记录：<br>
 * --------------------------------------------------------
 * 2015-3-17,enilu(82552623@qq.com)新建文档<br>
 * 
 * -->
 * 
 * @author enilu(82552623@qq.com)
 * 
 *         since1.0
 */
public class Product implements Serializable {
	private String name;
	private String idNum;
	private String url;
	private File img;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

}
