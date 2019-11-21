package com.otl.liveapi;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class PlayerId implements Serializable {

    protected long clientId;

    protected long gameId;

    public PlayerId(long clientId, long gameId) {
        this.clientId = clientId;
        this.gameId = gameId;
    }
}
