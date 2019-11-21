package com.otl.liveapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerStatusService {

    @Autowired
    ServerStatusRepository serverStatusRepository;

    public boolean getIsCongested() {
        String isCongested = serverStatusRepository.findByKey("isCongested").get(0).getValue();

        if (isCongested.equals(Constants.STR_TRUE)) {
            return true;
        } else if (isCongested.equals(Constants.STR_FALSE)) {
            return false;
        } else {
            // raise warning

            // something wrong
            return true;
        }
    }

    public void setIsCongested(boolean isCongested) {
        ServerStatus serverStatus = serverStatusRepository.findByKey("isCongested").get(0);
        if (isCongested) {
            serverStatus.setValue(Constants.STR_TRUE);
        } else {
           serverStatus.setValue(Constants.STR_FALSE);
        }
        serverStatusRepository.save(serverStatus);
    }
}
