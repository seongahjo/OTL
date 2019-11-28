package com.otl.liveapi.controller;


import com.otl.liveapi.service.PlayerURLService;
import com.otl.liveapi.service.ServerStatusService;
import com.otl.liveapi.vo.PlayResponse;
import com.otl.liveapi.vo.PlayResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequiredArgsConstructor
public class PlayerURLController {
	private final PlayerURLService playerURLService;
	private final ServerStatusService serverStatusService;

	private AtomicInteger accessCount = new AtomicInteger();

	@GetMapping("/play/{game_id}")
	public ResponseEntity<PlayResponse> getPlayerURL(@PathVariable("game_id") long gameId) {
		accessCount.addAndGet(1);
		boolean isCongested = serverStatusService.getIsCongested();

		if (isCongested)
			return ResponseEntity.ok(new PlayResponse(false));

		PlayResponse response = playerURLService.getById(gameId, false);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/play")
	public ResponseEntity<PlayResponses> getPlayList() {
		boolean isCongested = serverStatusService.getIsCongested();
		return ResponseEntity.ok(playerURLService.getAll(isCongested));
	}

	public AtomicInteger getAccessCount() {
		return accessCount;
	}
}
