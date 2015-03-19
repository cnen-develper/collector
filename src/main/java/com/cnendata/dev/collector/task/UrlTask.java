/**
 *Task.java
 *Version1.0
 *2015-3-16
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.task;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.cnendata.dev.collector.queue.QueueManager;

/**
 * descript<br>
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
public class UrlTask implements ITask {
	private String url;
	private String parser;

	public UrlTask(Class parserClass, String url) {
		this.parser = parserClass.getName();
		this.url = url;
	}

	public void execute() {
		try {
			Document doc = Jsoup.connect(url).timeout(60000).get();
			ITask task = new ParserTask(doc, parser, url);
			QueueManager.getInstance().get(QueueManager.DOC_QUEUE).push(task);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
