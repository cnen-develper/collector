package com.cnendata.dev.collector.jms;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JmsQueueManager {
	private static JmsQueueManager instance = null;
	private static Logger logger = LoggerFactory
			.getLogger(JmsQueueManager.class);

	public synchronized static JmsQueueManager getInstance() {
		if (instance == null) {
			instance = new JmsQueueManager();
		}
		return instance;
	}

	private JmsQueueManager() {
	}

	private String user = null;// ActiveMQConnection.DEFAULT_USER;
	private String password = null;// ActiveMQConnection.DEFAULT_PASSWORD;
	private String url = null;
	private Connection connection = null;
	private ActiveMQConnectionFactory connectionFactory = null;

	public synchronized ActiveMQConnectionFactory getActiveMQConnectionFactory() {
		return connectionFactory;
	}

	public synchronized Connection getConnection() {
		return connection;
	}

	public synchronized void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void init(Properties prop) {
		url = prop.getProperty("jms.ip");
		user = prop.getProperty("jms.userName");
		password = prop.getProperty("jms.password");
		try {
			connectionFactory = new ActiveMQConnectionFactory(user, password,
					url);
			connection = connectionFactory.createQueueConnection();
			connection.start();
			logger.info("==========>proxy jms service startup.......");
		} catch (JMSException e) {
			logger.error("=====> error create jms connection", e);

		}
	}

}
