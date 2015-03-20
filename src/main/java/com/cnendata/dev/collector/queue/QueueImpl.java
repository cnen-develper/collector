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

import com.cnendata.dev.collector.task.ITask;

/**
 * 默认的队列实现类<br>
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
public class QueueImpl implements IQueue {

	private static Logger logger = LoggerFactory.getLogger(QueueImpl.class);
	private static BlockingDeque<ITask> list = null;
	static QueueImpl instance;

	public QueueImpl() {
		list = new LinkedBlockingDeque<ITask>(100);
	}

	// public static QueueImpl getInstance() {
	// if (instance == null) {
	// instance = new QueueImpl();
	//
	// }
	// return instance;
	// }

	public void push(ITask task) {
		list.offer(task);
	}

	public ITask take() {

		try {
			return list.take();
		} catch (Exception e) {
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
