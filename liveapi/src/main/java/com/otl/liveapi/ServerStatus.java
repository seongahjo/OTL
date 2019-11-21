package com.otl.liveapi;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ServerStatus {
    @Id
    private String key;

    @Column
    private String value;
}
