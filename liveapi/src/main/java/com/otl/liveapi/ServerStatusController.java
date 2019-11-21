package com.otl.liveapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerStatusController {
    @Autowired
    ServerStatusService serverStatusService;

    private final int SUCCESS = 0;
    private final int INVALID_VALUE = -1;

    @GetMapping("/admin/congested/{is_congested}")
    private int setIsCongested(@PathVariable("is_congested") int isCongested) {
        if (isCongested == 1) {
            serverStatusService.setIsCongested(true);
            return SUCCESS;
        } else if (isCongested == 0) {
            serverStatusService.setIsCongested(false);
            return SUCCESS;
        } else {
            return INVALID_VALUE;
        }
    }
}
