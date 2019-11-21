package com.otl.server.dto;

import java.time.LocalDateTime;

import com.otl.server.model.Schedule;
import lombok.Data;


@Data
public class ScheduleDto {
	private String homeTeamName;
	private String awayTeamName;
	private Integer homeTeamScore;
	private Integer awayTeamScore;
	private Integer channelCode;
	private String gameDate;
	private String gameTime;

	public ScheduleDto(Schedule schedule) {
		this.homeTeamName = schedule.getHomeTeamName();
		this.awayTeamName = schedule.getAwayTeamName();
		this.homeTeamScore = schedule.getHomeTeamScore();
		this.awayTeamScore = schedule.getAwayTeamScore();
		this.channelCode = schedule.getChannelCode();
		LocalDateTime localDateTime = schedule.getGameDateTime();
		this.gameDate = String.format("%d.%d", localDateTime.getMonthValue(), localDateTime.getDayOfMonth());
		this.gameTime = String.format("%d:%d", localDateTime.getHour(), localDateTime.getMinute());
	}
}
