package com.otl.liveapi.vo;

import com.otl.liveapi.model.PlayerUrl;
import lombok.Data;

@Data
public class PlayDto {
	private long gameId;
	private String url;

	public PlayDto() {
		gameId = -1L;
		url = "";
	}

	public PlayDto(long gameId, String url) {
		this.gameId = gameId;
		this.url = url;
	}

	public PlayDto(PlayerUrl url) {
		this.gameId = url.getGameId();
		this.url = url.getUrl();
	}
}
