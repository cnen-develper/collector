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

import com.cnendata.dev.collector.model.MyDocument;
import com.cnendata.dev.collector.model.MyUrl;
import com.cnendata.dev.collector.queue.DocumentQueue;

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
public class UrlTask extends AbstractTask {
	private MyUrl url;

	public UrlTask(MyUrl url) {
		this.url = url;
	}

	@Override
	public void execute() {
		try {
			Document doc = Jsoup.connect(url.getUrl()).timeout(60000).get();
			DocumentQueue.getInstance()
					.push(new MyDocument(url.getType(), doc));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
