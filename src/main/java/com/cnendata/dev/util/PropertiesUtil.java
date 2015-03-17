/**
 *PropertiesUtil.java
 *Version1.0
 *2015-3-17
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
public class PropertiesUtil {
	public static Properties getProperties(String filename) throws IOException,
			FileNotFoundException {
		if (filename == null || filename.trim().length() == 0) {
			throw new FileNotFoundException(
					"Files Name can't be null or String of length 0.");
		}
		InputStream configStream = null;
		Properties prop = new Properties();

		try {
			configStream = PropertiesUtil.class.getResourceAsStream(filename);
			prop.load(configStream);
		} catch (Exception e) {

		} finally {
			if (null != configStream) {
				try {
					configStream.close();
				} catch (Throwable t) {
					t.getMessage();
				}
			}
		}
		return prop;
	}
}
