package com.otl.liveapi;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Data
public class PlayerId implements Serializable {
    protected long clientId;
    protected long gameId;
}
