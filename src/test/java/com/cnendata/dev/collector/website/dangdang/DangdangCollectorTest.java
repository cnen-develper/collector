package com.cnendata.dev.collector.website.dangdang;

import junit.framework.TestCase;

import com.cnendata.dev.collector.queue.IQueue;
import com.cnendata.dev.collector.queue.QueueImpl;
import com.cnendata.dev.collector.website.dangdang.book.DangdangCollector;

public class DangdangCollectorTest extends TestCase {

	public void testCollect() {
		IQueue urlQueue = new QueueImpl();
		CollectorTheade ct = new CollectorTheade(urlQueue);
		Thread t = new Thread(ct);
		t.start();
		int times = 1;
		while (true) {
			try {
				times++;
				Thread.sleep(1000);
				if (urlQueue.size() > 0) {
					try {
						t.interrupt();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
				if (times == 30) {
					try {
						t.interrupt();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println(urlQueue.size());
		assertTrue(urlQueue.size() > 2);
	}

	class CollectorTheade implements Runnable {
		private IQueue queue;

		public CollectorTheade(IQueue queue) {
			this.queue = queue;
		}

		public void run() {
			new DangdangCollector().collect(queue);
		}

	}

}
