package com.otl.server.service

import com.otl.server.dto.ScheduleDto
import com.otl.server.repository.ScheduleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ScheduleService(val scheduleRepository: ScheduleRepository) {
	fun getScheduleByGameId(gameId: Int): ScheduleDto {
		return ScheduleDto(scheduleRepository.findByChannelCode(gameId))
	}
}