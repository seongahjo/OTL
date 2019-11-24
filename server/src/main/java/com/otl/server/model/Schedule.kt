package com.otl.server.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Schedule(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val idx: Long,
					val homeTeamName: String,
					val awayTeamName: String,
					val homeTeamScore: Int,
					val awayTeamScore: Int,
					val channelCode: Int,
					val gameDateTime: LocalDateTime)
