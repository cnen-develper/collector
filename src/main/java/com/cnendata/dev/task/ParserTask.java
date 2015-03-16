/**
 *ParserTask.java
 *Version1.0
 *2015-3-16
 *Copyright cnendata.com
 *
 */
package com.cnendata.dev.task;

import com.cnendata.dev.model.MyDocument;
import com.cnendata.dev.parser.AbstractParser;
import com.cnendata.dev.parser.ParserSelector;
import com.cnendata.dev.queue.DocumentQueue;

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
	@Override
	public void execute() {
		MyDocument doc = DocumentQueue.getInstance().take();
		AbstractParser parser = ParserSelector.getInstance().getParser(
				doc.getType());
		parser.parse(doc.getDoc());
	}
}
