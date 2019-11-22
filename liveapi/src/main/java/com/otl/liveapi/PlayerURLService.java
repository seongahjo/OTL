package com.otl.liveapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerURLService {

    final PlayerURLRepository playerURLRepository;

    public String getURLById(long gameId) {

        PlayerId playerId = new PlayerId();
        playerId.setGameId(gameId);
        PlayerUrl res = playerURLRepository.findById(playerId).get();
        return res.getUrl();
    }
}
