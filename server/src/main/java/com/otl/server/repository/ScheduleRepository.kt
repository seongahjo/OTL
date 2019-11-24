package com.otl.server.repository

import com.otl.server.model.Schedule
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ScheduleRepository : JpaRepository<Schedule, Long> {
	fun findByChannelCode(channelCode: Int): Schedule
}