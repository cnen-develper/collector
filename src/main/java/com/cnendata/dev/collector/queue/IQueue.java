/**
 *IQueue.java
 *Version1.0
 *2015-3-19
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.queue;

import com.cnendata.dev.collector.task.ITask;

/**
 * 队列接口<br>
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
public interface IQueue {
	public ITask take();

	public void push(ITask task);

	public int size();
}
