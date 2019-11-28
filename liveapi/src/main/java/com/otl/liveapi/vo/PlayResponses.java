package com.otl.liveapi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PlayResponses {
	private boolean isCongested;
	private List<PlayDto> plays;

	public PlayResponses() {
		isCongested = true;
		plays = new ArrayList<>();
	}
}
