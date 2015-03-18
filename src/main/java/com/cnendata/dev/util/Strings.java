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
	/**
	 * 提取正则表达式匹配的字符并返回
	 * 
	 * @param src
	 *            源字符串
	 * @param reg
	 *            正则表达式
	 * @return 正则表达式匹配的字符串
	 */
	public static String get(String src, String reg) {
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(src);
		StringBuilder result = new StringBuilder();
		while (matcher.find()) {
			result.append(matcher.group());
		}
		return result.toString();
	}

	/**
	 * 抽取给定字符串中得所有汉字字符并返回
	 * 
	 * @param src
	 * @return
	 */
	public static String getCnCharacter(String src) {
		return get(src, "([\u4e00-\u9fa5]{1,4})");
	}

	/**
	 * 抽取给定字符串中得所有英文字符并返回
	 * 
	 * @param src
	 * @return
	 */
	public static String getEnCharacter(String src) {
		return get(src, "[a-zA-Z]");
	}
}
