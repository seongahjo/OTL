package com.otl.server.controller;

import com.otl.server.dto.StatusDto;
import com.otl.server.mq.GameRegistrator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequiredArgsConstructor
@Slf4j
public class MainController {
	private final GameRegistrator registrator;

	private static final Integer CLIENT_ID = 0;

	@Value("${otl.url.live-api}")
	private String liveApiUrl;

	@Value("${otl.url.push}")
	private String pushUrl;


	@GetMapping("/play")
	public ResponseEntity<String> main(Integer gameId) {
		RestTemplate template = new RestTemplate();
		String url = String.format("%s/%d/%d", liveApiUrl, CLIENT_ID, gameId);
		Integer count = registrator.getCount(); // 2
		if (count == 0) {
			registrator.send(String.format("%d;%d", CLIENT_ID, gameId)); // 5
		} else {
			try {
				ResponseEntity<StatusDto> response = template.getForEntity(url, StatusDto.class); // 3
				StatusDto dto = response.getBody();
				if (dto.isSuccess()) {
					return ResponseEntity.ok().body(dto.getUrl()); // 6
				}
			} catch (Exception e) {
				log.info("LIVE SERVER {} is dead", liveApiUrl);
			}
		}
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(pushUrl);
	}
}
