/**
 *Task.java
 *Version1.0
 *2015-3-16
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.task;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.cnendata.dev.model.MyDocument;
import com.cnendata.dev.model.MyUrl;
import com.cnendata.dev.queue.DocumentQueue;
import com.cnendata.dev.queue.UrlQueue;

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

	@Override
	public void execute() {
		MyUrl url = UrlQueue.getInstance().take();
		try {
			Document doc = Jsoup.connect(url.getUrl()).get();
			DocumentQueue.getInstance()
					.push(new MyDocument(url.getType(), doc));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
