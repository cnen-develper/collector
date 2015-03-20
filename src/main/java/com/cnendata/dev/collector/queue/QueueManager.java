/**
 *QueueManager.java
 *Version1.0
 *2015-3-19
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.queue;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 队列管理器，队列使用者统一通过该类使用队列<br>
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
public class QueueManager {
	public static final String URL_QUEUE = "urlqueue";
	public static final String DOC_QUEUE = "docqueue";
	private static QueueManager instance;
	Map<String, IQueue> map = Maps.newConcurrentMap();

	private QueueManager() {
	};

	public static QueueManager getInstance() {
		if (instance == null) {
			instance = new QueueManager();
		}
		return instance;
	}

	public IQueue get(String key) {
		return map.get(key);
	}

	public void put(String key, IQueue queue) {
		map.put(key, queue);
	}

}
