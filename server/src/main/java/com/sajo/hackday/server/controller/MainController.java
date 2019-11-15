package com.sajo.hackday.server.controller;


import com.sajo.hackday.server.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {
	private final StatusService statusService;

	@GetMapping("/")
	public String main() {
		return statusService.getAllStatus();
	}
}
