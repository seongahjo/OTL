package com.sajo.hackday.server.service;

import com.sajo.hackday.server.model.Status;
import com.sajo.hackday.server.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StatusService {

	private final StatusRepository statusRepository;

	public String getAllStatus() {
		return statusRepository.findAll().stream().map(Status::getValue).collect(Collectors.joining(","));
	}

}
