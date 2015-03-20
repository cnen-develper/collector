/**
 *ThreadPoolDoc.java
 *Version1.0
 *2015-3-19
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.threadpool;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnendata.dev.collector.queue.IQueue;
import com.google.common.collect.Lists;

/**
 * Document引擎使用的线程池<br>
 * <!--<br>
 * 历史记录：<br>
 * --------------------------------------------------------
 * 2015-3-19,enilu(82552623@qq.com)新建文档<br>
 * 
 * -->
 * 
 * @author enilu(82552623@qq.com)
 * 
 *         since1.0
 */
public class ThreadPoolImpl implements IThreadPool {
	private static Logger logger = LoggerFactory
			.getLogger(ThreadPoolImpl.class);
	private static List<Worker> pool = Lists.newArrayList();// 线程队列
	private int threadNum = 10;
	private IQueue queue;

	public ThreadPoolImpl(IQueue queue) {
		this.queue = queue;

	}

	/**
	 * 根据制定的线程数量构造线程池
	 * 
	 * @param threadNum
	 * @return
	 */
	public void init(int num) {
		threadNum = num;
		for (int i = 0; i < num; i++) {
			Worker wt = new Worker(i, queue);
			wt.start();
			pool.add(wt);
		}

	}

	public Worker get() {
		synchronized (this) {
			Worker thread = null;
			while (thread == null) {
				for (Worker t : pool) {
					if (t.getStatus() == Worker.IDLE) {
						thread = t;
						break;
					}
				}
			}
			return thread;
		}

	}

	public int getIdleCount() {
		int idleCount = 0;
		for (Worker t : pool) {
			if (t.getStatus() == Worker.IDLE) {
				idleCount++;
			}
		}
		return idleCount;
	}

	public int getBusyCount() {
		int busyCount = 0;
		for (Worker t : pool) {
			if (t.getStatus() == Worker.BUSY) {
				busyCount++;
			}
		}
		return busyCount;
	}

	public int getClosedCount() {

		int count = 0;
		for (Worker t : pool) {
			if (t.getStatus() == Worker.CLOSED) {
				count++;
			}
		}
		return count;

	}

	/**
	 * 
	 */
	public void shutdown() {
		logger.info("threadPool shutdown...");
		// int urlSize = UrlQueue.getInstance().getTaskSize();
		// int taskSize = DocumentQueue.getInstance().getTaskSize();
		// while (taskSize > 0 || urlSize > 0) {
		// try {
		// Thread.sleep(500);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// urlSize = UrlQueue.getInstance().getTaskSize();
		// taskSize = DocumentQueue.getInstance().getTaskSize();
		// }
		// while (this.getClosedCount() < threadNum) {
		// for (Worker t : pool) {
		// if (t.getStatus() == Worker.IDLE) {
		// t.release();
		// }
		// }
		// }
		logger.info("threadPool shutdown ok");

	}
}
