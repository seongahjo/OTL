package com.otl.server.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StatusDTO {
	@NotNull
	private boolean isSuccess;
	@NotNull
	private Integer gameId;
	private String url;
}
