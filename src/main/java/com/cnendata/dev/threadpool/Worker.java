/**
 *Worker.java
 *Version1.0
 *2015-3-16
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.threadpool;

import com.cnendata.dev.task.AbstractTask;

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
public class Worker implements Runnable {
	private AbstractTask task;

	public void run() {
		task.execute();
		// 放到doc 队列

	}

	public void setTask(AbstractTask task) {
		this.task = task;
	}

}
