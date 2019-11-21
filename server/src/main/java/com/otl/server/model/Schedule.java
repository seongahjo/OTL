package com.otl.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;

	@Column
	private String homeTeamName;
	@Column
	private String awayTeamName;
	@Column
	private Integer homeTeamScore;
	@Column
	private Integer awayTeamScore;
	@Column
	private Integer channelCode;
	@Column
	private LocalDateTime gameDateTime;
}
