/**
 *ThreadPoolFactory.java
 *Version1.0
 *2015-3-19
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.threadpool;

import java.util.Map;

import com.google.common.collect.Maps;

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
public class ThreadPoolFactory {

	private static ThreadPoolFactory instance;
	private Map<String, IThreadPool> tps = null;

	private ThreadPoolFactory() {
		tps = Maps.newConcurrentMap();

		int threadCount = Integer.valueOf(System.getProperty("threadCount"));
		IThreadPool pool1 = new ThreadPoolDoc();
		pool1.init(threadCount);

		tps.put(ThreadPoolDoc.class.getName(), pool1);
		IThreadPool pool2 = new ThreadPoolUrl();
		pool2.init(threadCount);
		tps.put(ThreadPoolUrl.class.getName(), pool2);
	}

	public static ThreadPoolFactory getInstance() {
		if (instance == null) {
			instance = new ThreadPoolFactory();
		}
		return instance;
	}

	public IThreadPool getThredPool(Class clazz) {
		return tps.get(clazz.getName());
	}

}
