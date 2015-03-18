/**
 *DocumentQueue.java
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

import com.cnendata.dev.collector.model.MyDocument;

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
public class DocumentQueue {

	private static Logger logger = LoggerFactory.getLogger(DocumentQueue.class);
	private static BlockingDeque<MyDocument> list = null;
	static DocumentQueue instance;

	private DocumentQueue() {
		list = new LinkedBlockingDeque<MyDocument>(100);

	}

	public static DocumentQueue getInstance() {
		if (instance == null) {
			instance = new DocumentQueue();

		}
		return instance;
	}

	public void push(MyDocument doc) {
		list.offer(doc);
	}

	public MyDocument take() {

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
