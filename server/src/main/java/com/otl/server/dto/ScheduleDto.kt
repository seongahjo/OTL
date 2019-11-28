package com.otl.server.dto

import com.otl.server.model.Schedule

data class ScheduleDto(var schedule: Schedule) {
	val homeTeamName: String = schedule.homeTeamName
	val awayTeamName: String = schedule.awayTeamName
	val homeTeamScore: Int = schedule.homeTeamScore
	val awayTeamScore: Int = schedule.awayTeamScore
	val channelCode: Int = schedule.channelCode
	val gameDate: String
	val gameTime: String

	init {
		val localDateTime = schedule.gameDateTime
		gameDate = String.format("%d.%d", localDateTime.monthValue, localDateTime.dayOfMonth)
		gameTime = String.format("%d:%d", localDateTime.hour, localDateTime.minute)
	}
}