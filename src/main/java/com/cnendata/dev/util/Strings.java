/**
 *Strings.java
 *Version1.0
 *2015-3-17
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类<br>
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
public class Strings {
	public static String get(String src, String reg) {
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(src);
		StringBuilder result = new StringBuilder();
		while (matcher.find()) {
			result.append(matcher.group());
		}
		return result.toString();
	}
}
