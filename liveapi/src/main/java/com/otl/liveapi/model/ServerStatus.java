package com.otl.liveapi.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ServerStatus {
	@Id
	@Enumerated(value = EnumType.STRING)
	private SERVER_STATUS key;

	@Column
	private boolean value;

	public enum SERVER_STATUS {
		IS_CONGESTED
	}

}
