/**
 *ParserTask.java
 *Version1.0
 *2015-3-16
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.collector.task;

import com.cnendata.dev.collector.jms.ApplicationRequest;
import com.cnendata.dev.collector.jms.ExectueResultSendWorker;
import com.cnendata.dev.collector.model.MyDocument;
import com.cnendata.dev.collector.model.Product;
import com.cnendata.dev.collector.parser.AbstractParser;
import com.cnendata.dev.collector.parser.ParserSelector;

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
public class ParserTask extends AbstractTask {
	private MyDocument doc = null;

	public ParserTask(MyDocument doc) {

		this.doc = doc;
	}

	@Override
	public void execute() {
		AbstractParser parser = ParserSelector.getInstance().getParser(
				doc.getType());
		Product product = parser.parse(doc.getDoc());
		ExectueResultSendWorker.getInstance().doSendResultToKernel(
				new ApplicationRequest(product));
	}
}
