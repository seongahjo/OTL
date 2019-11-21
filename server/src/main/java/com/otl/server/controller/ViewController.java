package com.otl.server.controller;

import com.otl.server.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ViewController {
	private final ScheduleService scheduleService;

	@GetMapping("/view")
	public String mainView(Integer gameId, Model model) {
		model.addAttribute(scheduleService.getScheduleByGameId(gameId));
		return "main";
	}
}
