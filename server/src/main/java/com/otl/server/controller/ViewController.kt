package com.otl.server.controller

import com.otl.server.service.ScheduleService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ViewController(private val scheduleService: ScheduleService) {
	@Value("\${otl.url.server}")
	lateinit var serverUrl: String

	@GetMapping("/view")
	fun mainView(gameId: Int, model: Model): String {
		model.apply {
			addAttribute("schedule", scheduleService.getScheduleByGameId(gameId))
			addAttribute("serverUrl", serverUrl)
		}
		return "main"
	}
}