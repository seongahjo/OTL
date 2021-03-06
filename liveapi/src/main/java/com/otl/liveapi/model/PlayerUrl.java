package com.otl.liveapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@IdClass(PlayerId.class)
public class PlayerUrl {
    @Id
    private Long gameId;

    @NotNull
    @Size(max=1024)
    @Column
    private String url;

}