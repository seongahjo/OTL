package com.otl.server.service;

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

	public ScheduleDto getScheduleByGameId(Integer gameId) {
		return new ScheduleDto(scheduleRepository.findByChannelCode(gameId));
	}
}
