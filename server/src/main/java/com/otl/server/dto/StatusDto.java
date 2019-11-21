package com.otl.server.dto;

import lombok.Data;


@Data
public class StatusDto {
	private boolean isSuccess;
	private Integer gameId;
	private String url;
}
