package com.otl.server.mq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Sender {
	private final RabbitMessagingTemplate template;
	private final AmqpAdmin amqpAdmin;

	public enum Name {
		OWL
	}

	@Bean
	Queue queue() {
		return QueueBuilder.nonDurable(Name.OWL.name()).build();
	}

	public int getCount(Name queueName) {
		Integer count = (Integer) amqpAdmin.getQueueProperties(queueName.name()).get("QUEUE_MESSAGE_COUNT");
		log.info("Get Message Count {} ", count);
		return count;
	}

	public void send(Name queueName, String message) {
		template.convertAndSend(queueName.name(), message);
		log.info("Message Send {} ", message);
	}
}
