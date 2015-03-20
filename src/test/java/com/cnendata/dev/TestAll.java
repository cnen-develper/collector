/**
 *TestAll.java
 *Version1.0
 *2015-3-20
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.cnendata.dev.collector.jms.AllJms;
import com.cnendata.dev.collector.queue.AllQueue;
import com.cnendata.dev.collector.task.AllTask;
import com.cnendata.dev.collector.threadpool.AllThreadPool;
import com.cnendata.dev.collector.website.AllWebsite;
import com.cnendata.dev.util.AllUtil;

/**
 * descript<br>
 * <!--<br>
 * 历史记录：<br>
 * --------------------------------------------------------
 * 2015-3-20,enilu(82552623@qq.com)新建文档<br>
 * 
 * -->
 * 
 * @author enilu(82552623@qq.com)
 * 
 *         since1.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ AllUtil.class, AllJms.class, AllQueue.class,
		AllTask.class, AllThreadPool.class, AllWebsite.class })
public class TestAll {
}
