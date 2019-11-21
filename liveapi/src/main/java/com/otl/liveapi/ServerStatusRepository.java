package com.otl.liveapi;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServerStatusRepository extends CrudRepository<ServerStatus, Integer> {
    List<ServerStatus> findByKey(String key);
}
