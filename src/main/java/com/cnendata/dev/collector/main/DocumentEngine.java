/**
 *DocumentEngine.java
 *Version1.0
 *2015-3-17
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.main;

import com.cnendata.dev.collector.model.MyDocument;
import com.cnendata.dev.collector.queue.DocumentQueue;
import com.cnendata.dev.collector.task.AbstractTask;
import com.cnendata.dev.collector.task.ParserTask;
import com.cnendata.dev.collector.threadpool.ThreadPool;

/**
 * descript<br>
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
public class DocumentEngine {
	public void bootstrap(ThreadPool threadPool) {
		while (true) {
			MyDocument doc = DocumentQueue.getInstance().take();
			if (doc != null) {
				AbstractTask task = new ParserTask(doc);
				threadPool.get().startTask(task);
			}
		}
	}
}
