/**
 *ThreadPool.java
 *Version1.0
 *2015-3-16
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.threadpool;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnendata.dev.collector.queue.DocumentQueue;
import com.cnendata.dev.collector.queue.UrlQueue;
import com.google.common.collect.Lists;

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
public class ThreadPool {
	private static Logger logger = LoggerFactory.getLogger(ThreadPool.class);
	private static ThreadPool instance;
	private static List<Worker> pool = Lists.newArrayList();// 线程队列
	private static int threadNum = 10;

	/**
	 * 根据制定的线程数量构造线程池
	 * 
	 * @param threadNum
	 * @return
	 */
	public static ThreadPool getThreadPool(int num) {
		if (instance == null) {
			logger.debug("create " + num + " threads add to threadPool");
			instance = new ThreadPool();
			threadNum = num;
			for (int i = 0; i < num; i++) {
				Worker wt = new Worker(i);
				wt.start();
				pool.add(wt);
			}

		}
		int xiangchaNum = num > threadNum ? (num - threadNum) : 0;
		for (int i = 0; i < xiangchaNum; i++) {
			Worker wt = new Worker(i);
			wt.start();
			pool.add(wt);
		}
		return instance;

	}

	/**
	 * 使用默认的线程数目构造线程池
	 * 
	 * @return
	 */
	public static ThreadPool getTheadPool() {
		return getThreadPool(50);// 默认生成50个线程
	}

	public Worker get() {
		synchronized (this) {
			Worker thread = null;
			while (thread == null) {
				for (Worker t : pool) {
					if (t.getStatus() == Worker.IDLE) {
						thread = t;
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
		int urlSize = UrlQueue.getInstance().getTaskSize();
		int taskSize = DocumentQueue.getInstance().getTaskSize();
		while (taskSize > 0 || urlSize > 0) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			urlSize = UrlQueue.getInstance().getTaskSize();
			taskSize = DocumentQueue.getInstance().getTaskSize();
		}
		while (this.getClosedCount() < threadNum) {
			for (Worker t : pool) {
				if (t.getStatus() == Worker.IDLE) {
					t.release();
				}
			}
		}
		logger.info("threadPool shutdown ok");

	}
}
