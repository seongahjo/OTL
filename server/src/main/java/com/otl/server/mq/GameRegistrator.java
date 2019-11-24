package com.otl.server.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameRegistrator {
	private final Sender sender;

	public void send(String message) {
		sender.send(Sender.Name.OWL, message);
	}

	public Integer size() {
		return sender.getCount(Sender.Name.OWL);
	}
}
