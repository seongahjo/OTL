package com.sajo.hackday.server.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Status {
	@Id
	@GeneratedValue
	Long idx;

	@Column
	String value;

	public Status(String value) {
		this.value = value;
	}
}
