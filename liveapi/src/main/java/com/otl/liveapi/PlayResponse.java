package com.otl.liveapi;

import lombok.Data;

@Data
public class PlayResponse {

    private boolean isSuccess;
    private long gameId;
    private String url;

    PlayResponse(Boolean isSuccess, long gameId, String url) {
        this.isSuccess = isSuccess;
        this.gameId = gameId;
        this.url = url;
    }
}
