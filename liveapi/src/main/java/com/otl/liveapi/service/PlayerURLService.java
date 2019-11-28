package com.otl.liveapi.service;

import com.otl.liveapi.model.PlayerId;
import com.otl.liveapi.model.PlayerUrl;
import com.otl.liveapi.repository.PlayerURLRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerURLService {

	private final PlayerURLRepository playerURLRepository;

	public String getURLById(long gameId) {
		PlayerUrl res = playerURLRepository.findById(new PlayerId(gameId)).orElseThrow(NullPointerException::new);
		return res.getUrl();
	}
}
