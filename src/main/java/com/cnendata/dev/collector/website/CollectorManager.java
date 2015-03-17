/**
 *CollectorManager.java
 *Version1.0
 *2015-3-17
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.website;

import java.util.Enumeration;
import java.util.Properties;

import com.cnendata.dev.collector.parser.ICollector;
import com.cnendata.dev.util.PropertiesUtil;

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
public class CollectorManager {
	public void start() throws Exception {
		Properties prop = PropertiesUtil.getProperties("/collector.properties");
		Enumeration<Object> keys = prop.keys();
		while (keys.hasMoreElements()) {
			String className = prop.getProperty(String.valueOf(keys
					.nextElement()));
			ICollector collector = (ICollector) Class.forName(className)
					.newInstance();
			collector.collect();
		}
	}
}
