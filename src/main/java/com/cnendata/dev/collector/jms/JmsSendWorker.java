package com.cnendata.dev.collector.jms;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class JmsSendWorker {
	private static Logger logger = LoggerFactory.getLogger(JmsSendWorker.class);

	private long timeToLive = 1200000L;

	private static JmsSendWorker instance;

	public static JmsSendWorker getInstance() {
		if (instance == null) {
			instance = new JmsSendWorker();
		}
		return instance;
	}

	private JmsSendWorker() {
		init();
	}

	private Session session = null;
	private MessageProducer producer = null;
	private Destination destination;
	private String subject = "COLLECTOR_RESULT_QUEUE";

	private void init() {
		try {
			session = JmsQueueManager.getInstance().getConnection()
					.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue(subject);
			producer = session.createProducer(destination);
			producer.setTimeToLive(timeToLive);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		} catch (JMSException e) {
			logger.error("error init CommandSender .....", e);
		}

	}

	/**
	 * 发送ApplicationRequest 消息的唯一方法
	 * 
	 * @param request
	 */
	public synchronized void doSendResultToKernel(ApplicationRequest request) {
		ObjectMessage msg;
		try {
			logger.debug(new Gson().toJson(request.getProduct()));
			msg = session.createObjectMessage(request);
			producer.send(msg);
		} catch (JMSException e) {
			logger.error("error doSendCommandToProxy .....", e);
		}

	}

}
