package com.otl.liveapi;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
//@IdClass(PlayerId.class)
public class PlayerURL {
    @EmbeddedId PlayerId playerId;

    @NotNull
    @Size(max=1024)
    @Column
    private String url;

}