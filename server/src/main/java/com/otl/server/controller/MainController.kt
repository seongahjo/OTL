package com.otl.server.controller

import com.otl.server.dto.ResponseDto
import com.otl.server.dto.StatusDto
import com.otl.server.mq.GameRegistrator
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.util.*

@RestController
class MainController(private val registrator: GameRegistrator) {
	@Value("\${otl.url.live-api}")
	lateinit var liveApiUrl: String

	@Value("\${otl.url.push}")
	lateinit var pushUrl: String

	@GetMapping("/play")
	fun main(gameId: Int): ResponseEntity<ResponseDto> {
		val template = RestTemplate()
		val url = "${liveApiUrl}/play/${gameId}"
		val clientId = UUID.randomUUID().toString()
		val q = "${clientId};${gameId}"
		when (registrator.size()) {
			0 -> registrator.send(q)
			else -> {
				try {
					val response = template.getForEntity(url, StatusDto::class.java)
					val dto = response.body
					if (dto.success) return ResponseEntity.ok().body(ResponseDto(dto.url, clientId)) else registrator.send(q)
				} catch (e: Exception) {
					return ResponseEntity.notFound().build();
				}
			}
		}
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(ResponseDto(pushUrl, clientId))
	}
}