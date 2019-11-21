package com.otl.liveapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerURLService {
    @Autowired
    PlayerURLRepository playerURLRepository;

    public String getURLById(long clientId, long gameId) {
        List<PlayerURL> res = playerURLRepository.findByPlayerId(new PlayerId(clientId, gameId));
        return res.get(0).getUrl();
    }
}
