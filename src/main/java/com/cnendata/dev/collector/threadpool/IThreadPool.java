/**
 *IThreadPool.java
 *Version1.0
 *2015-3-19
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.threadpool;

/**
 * descript<br>
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
public interface IThreadPool {
	/**
	 * 线程池初始化方法
	 * 
	 * @param threadCount
	 *            初始化线程数量
	 */
	public void init(int threadCount);

	public Worker get();

	public int getIdleCount();

	public int getBusyCount();

	public int getClosedCount();

	public void shutdown();
}
