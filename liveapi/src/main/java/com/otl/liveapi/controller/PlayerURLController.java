package com.otl.liveapi.controller;


import com.otl.liveapi.service.PlayerURLService;
import com.otl.liveapi.service.ServerStatusService;
import com.otl.liveapi.vo.PlayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlayerURLController {
	private final PlayerURLService playerURLService;
	private final ServerStatusService serverStatusService;

	@GetMapping("/play/{game_id}")
	public PlayResponse getPlayerURL(@PathVariable("game_id") long gameId) {
		boolean isCongested = serverStatusService.getIsCongested();
		if (isCongested)
			return new PlayResponse(false);
		return new PlayResponse(true, gameId, playerURLService.getURLById(gameId));
	}
}
