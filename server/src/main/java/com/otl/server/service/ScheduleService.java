package com.otl.server.service;

import java.util.List;
import java.util.stream.Collectors;

import com.otl.server.dto.ScheduleDto;
import com.otl.server.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {
	private final ScheduleRepository scheduleRepository;

	public List<ScheduleDto> getAllSchedules() {
		return scheduleRepository.findAll().stream().map(ScheduleDto::new).collect(Collectors.toList());
	}
}
