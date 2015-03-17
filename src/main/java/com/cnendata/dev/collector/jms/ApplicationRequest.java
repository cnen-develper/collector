/**
 *ApplicationRequest.java
 *Version1.0
 *2015-3-17
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.jms;

import com.cnendata.dev.collector.model.Product;

/**
 * collector send request to activemq<br>
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
public class ApplicationRequest implements java.io.Serializable {

	private Product product;

	public ApplicationRequest(Product param) {
		this.product = param;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
