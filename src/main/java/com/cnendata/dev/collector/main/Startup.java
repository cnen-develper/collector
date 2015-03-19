/**
 *Startup.java
 *Version1.0
 *2015-3-16
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.main;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnendata.dev.collector.jms.JmsQueueManager;
import com.cnendata.dev.collector.website.CollectorManager;
import com.cnendata.dev.util.PropertiesUtil;

/**
 * 采集器启动类<br>
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
public class Startup {
	final static Logger logger = LoggerFactory.getLogger(Startup.class);

	/**
	 * @param args
	 * @throws Exception
	 * @throws
	 */
	public static void main(String[] args) throws Exception {
		logger.debug("collector startup...");
		// 读取config.properties配置文件中的线程池数量
		Properties prop = PropertiesUtil.getProperties("/config.properties");
		System.setProperty("threadCount", prop.getProperty("threadCount"));

		// 初始化线程池
		// ThreadPool threadPool =
		// ThreadPool.getThreadPool(Integer.valueOf(String
		// .valueOf(prop.get("threadCount"))));

		// 启动jms管理器
		JmsQueueManager.getInstance().init(prop);
		// 启动主采集器
		new CollectorManager().start();
		// 启动url处理引擎
		new UrlEngine().start();
		// 启动document解析引擎
		new DocumentEngine().start();

	}

}
