package com.otl.liveapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class PlayerId implements Serializable {
	protected long gameId;
}
