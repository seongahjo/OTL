package com.otl.liveapi.controller;

import com.otl.liveapi.service.ServerStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ServerStatusController {

	private final ServerStatusService serverStatusService;

	@GetMapping("/admin/congested/{is_congested}")
	public ResponseEntity<String> setIsCongested(@PathVariable("is_congested") int isCongested) {
		serverStatusService.setIsCongested(isCongested == 1);
		return ResponseEntity.ok("succeed");
	}

	@ExceptionHandler({NullPointerException.class})
	public ResponseEntity<String> handle(Exception ex) {
		return ResponseEntity.badRequest().build();
	}
}
