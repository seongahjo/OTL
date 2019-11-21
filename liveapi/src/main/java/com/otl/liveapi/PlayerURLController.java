package com.otl.liveapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerURLController {
    @Autowired
    PlayerURLService playerURLService;

    @Autowired
    ServerStatusService serverStatusService;

    @GetMapping("/play/{client_id}/{game_id}")
    private PlayResponse getPlayerURL(@PathVariable("client_id") long clientId,
                                 @PathVariable("game_id") long gameId) {

        // A lock or customized DB script will be needed for sync

        boolean isCongested = serverStatusService.getIsCongested();

        if (isCongested) {
            return new PlayResponse(false, 0, "");
        } else {
            String url = playerURLService.getURLById(clientId, gameId);
            return new PlayResponse(true, gameId, url);
        }


        /* END of the sync block */
    }
}
