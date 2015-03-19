/**
 *Worker.java
 *Version1.0
 *2015-3-16
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnendata.dev.collector.queue.IQueue;
import com.cnendata.dev.collector.task.ITask;

/**
 * 工作线程<br>
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
public class Worker

extends Thread {
	private static Logger logger = LoggerFactory.getLogger(Worker.class);
	protected static final int IDLE = 0;
	protected static final int BUSY = 1;
	protected static final int CLOSED = 2;
	private int id;// 线程id
	private int status;// 0:idle,1:busy
	private boolean isRunning = true;
	private IQueue queue;

	public Worker(int id, IQueue queue) {
		super();
		this.id = id;
		status = 0;
		this.queue = queue;
	}

	private ITask task;

	public int getStatus() {
		return status;
	}

	public ITask getTask() {
		return task;
	}

	public synchronized void startTask(ITask task) {
		this.task = task;
		notify();
	}

	@Override
	public synchronized void run() {
		while (isRunning) {
			ITask task = queue.take();
			task.execute();
			// new UrlTask(UrlQueue.getInstance().take()).execute();

			// if (this.status == Worker.IDLE && this.task != null) {
			// this.status = Worker.BUSY;
			// logger.info("Thread " + id + "start task");
			// this.task.execute();
			//
			// this.status = Worker.IDLE;
			// this.task = null;
			// } else {
			// try {
			// logger.debug("Thread " + id + "wait...");
			// wait();
			// logger.debug("Thread " + id + "是否被唤醒...");
			// } catch (InterruptedException e) {
			//
			// e.printStackTrace();
			// }
			// }
		}
	}

	/**
	 * 清出先撑资源，关闭线程
	 */
	public void release() {
		if (this.getStatus() != Worker.CLOSED) {
			isRunning = false;
			if (this.getStatus() == Worker.IDLE) {
				this.status = Worker.CLOSED;
				notify();

			}
		}

	}

}
