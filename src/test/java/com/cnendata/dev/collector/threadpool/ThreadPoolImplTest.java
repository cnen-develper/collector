package com.cnendata.dev.collector.threadpool;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cnendata.dev.collector.queue.IQueue;
import com.cnendata.dev.collector.queue.QueueImpl;

public class ThreadPoolImplTest {

	@Test
	public void testInit() {
		IQueue queue = new QueueImpl();
		IThreadPool tp = new ThreadPoolImpl(queue);
		tp.init(50);
		assertTrue(tp.getIdleCount() == 50);
	}

	@Test
	public void testGet() {
		IQueue queue = new QueueImpl();
		IThreadPool tp = new ThreadPoolImpl(queue);
		tp.init(50);
		Worker worker = tp.get();
		assertTrue(worker != null);
	}

	@Test
	public void testGetIdleCount() {

		IQueue queue = new QueueImpl();
		IThreadPool tp = new ThreadPoolImpl(queue);
		tp.init(50);
		assertTrue(tp.getIdleCount() == 50);
	}

	@Test
	public void testGetBusyCount() {

		IQueue queue = new QueueImpl();
		IThreadPool tp = new ThreadPoolImpl(queue);
		tp.init(50);
		assertTrue(tp.getBusyCount() == 50);
	}

	@Test
	public void testGetClosedCount() {

		IQueue queue = new QueueImpl();
		IThreadPool tp = new ThreadPoolImpl(queue);
		tp.init(50);
		assertTrue(tp.getClosedCount() == 0);
	}

	@Test
	public void testShutdown() {
		assertTrue(true);
	}

}
