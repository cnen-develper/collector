/**
 *UrlEngine.java
 *Version1.0
 *2015-3-16
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.main;

import com.cnendata.dev.collector.model.MyUrl;
import com.cnendata.dev.collector.queue.UrlQueue;
import com.cnendata.dev.collector.task.AbstractTask;
import com.cnendata.dev.collector.task.UrlTask;
import com.cnendata.dev.collector.threadpool.ThreadPool;

/**
 * 监视url队列，使用线程处理队列中的url<br>
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
public class UrlEngine extends Thread {
	private ThreadPool threadPool = null;

	public UrlEngine(ThreadPool th) {
		this.threadPool = th;
	}

	@Override
	public void run() {

		while (true) {
			MyUrl url = UrlQueue.getInstance().take();
			if (url != null) {
				AbstractTask task = new UrlTask(url);
				threadPool.get().startTask(task);
			}
		}

	}

}
