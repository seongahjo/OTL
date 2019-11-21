package com.sajo.hackday.server.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameRegistrator {
	private final Sender sender;

	public void send(String message) {
		sender.send(Sender.NAME.OWL, message);
	}

	public Integer getCount(){
		return sender.getCount(Sender.NAME.OWL);
	}
}
