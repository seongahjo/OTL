package com.otl.liveapi.repository;

import com.otl.liveapi.model.ServerStatus;
import org.springframework.data.repository.CrudRepository;

public interface ServerStatusRepository extends CrudRepository<ServerStatus, Integer> {
    ServerStatus findByKey(ServerStatus.SERVER_STATUS key);
}
