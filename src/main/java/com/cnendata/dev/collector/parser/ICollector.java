/**
 *ICollector.java
 *Version1.0
 *2015-3-16
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.parser;

import com.cnendata.dev.collector.queue.IQueue;

/**
 * 主采集器接口<br>
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
public interface ICollector {
	public void collect(IQueue urlQueue);
}
