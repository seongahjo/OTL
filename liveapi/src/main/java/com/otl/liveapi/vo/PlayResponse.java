package com.otl.liveapi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayResponse {
	private boolean isCongested;
	private PlayDto play;

	public PlayResponse(boolean isCongested) {
		this.isCongested = isCongested;
		this.play = new PlayDto();
	}
}
