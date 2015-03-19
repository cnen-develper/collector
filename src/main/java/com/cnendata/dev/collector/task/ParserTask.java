/**
 *ParserTask.java
 *Version1.0
 *2015-3-16
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.task;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnendata.dev.collector.jms.ApplicationRequest;
import com.cnendata.dev.collector.jms.JmsSendWorker;
import com.cnendata.dev.collector.model.Product;
import com.cnendata.dev.collector.parser.IParser;

/**
 * 解析document的任务<br>
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
public class ParserTask implements ITask {

	private static Logger logger = LoggerFactory.getLogger(ParserTask.class);
	private Document doc;
	private String parser;
	private String url;

	public ParserTask(Document doc, String parser, String url) {
		this.doc = doc;
		this.parser = parser;
		this.url = url;
	}

	public void execute() {
		try {
			IParser parserInstance = (IParser) Class.forName(
					parser).newInstance();
			Product product = parserInstance.parse(doc);
			product.setUrl(url);
			JmsSendWorker.getInstance().doSendResultToKernel(
					new ApplicationRequest(product));
		} catch (Exception e) {
			logger.error("execute parse Document error", e);
		}
	}
}
