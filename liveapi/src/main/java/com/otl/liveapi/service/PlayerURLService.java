package com.otl.liveapi.service;

import com.otl.liveapi.model.PlayerId;
import com.otl.liveapi.repository.PlayerURLRepository;
import com.otl.liveapi.vo.PlayResponses;
import com.otl.liveapi.vo.PlayDto;
import com.otl.liveapi.vo.PlayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PlayerURLService {

	private final PlayerURLRepository playerURLRepository;

	public PlayResponses getAll(boolean isCongested) {
		return new PlayResponses(isCongested, StreamSupport.stream(playerURLRepository.findAll().spliterator(), false).map(PlayDto::new).collect(Collectors.toList()));
	}

	public PlayResponse getById(long gameId, boolean isCongested) {
		return new PlayResponse(isCongested, new PlayDto(playerURLRepository.findById(new PlayerId(gameId)).orElseThrow(NullPointerException::new)));
	}
}
