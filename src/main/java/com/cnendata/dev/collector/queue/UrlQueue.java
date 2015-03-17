/**
 *UrlQueue.java
 *Version1.0
 *2015-3-16
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.queue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnendata.dev.collector.model.MyUrl;

/**
 * descript<br>
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
public class UrlQueue {

	private static Logger logger = LoggerFactory.getLogger(UrlQueue.class);
	private static BlockingDeque<MyUrl> list = null;
	static UrlQueue instance;

	private UrlQueue() {

	}

	public static UrlQueue getInstance() {
		if (instance == null) {
			instance = new UrlQueue();
			list = new LinkedBlockingDeque<MyUrl>(100);
		}
		return instance;
	}

	public void push(MyUrl url) {
		list.offer(url);
		logger.debug("push url");

	}

	public MyUrl take() {

		try {
			return list.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return
	 */
	public int getTaskSize() {
		return list.size();
	}

}
