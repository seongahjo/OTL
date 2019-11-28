package com.otl.liveapi.vo;

import lombok.Data;

@Data
public class PlayResponse {

    private boolean isSuccess;
    private long gameId;
    private String url;

    public PlayResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
        gameId = -1L;
        url="";
    }

    public PlayResponse(Boolean isSuccess, long gameId, String url) {
        this.isSuccess = isSuccess;
        this.gameId = gameId;
        this.url = url;
    }
}
