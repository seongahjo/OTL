package com.otl.liveapi.repository;
import com.otl.liveapi.model.PlayerId;
import com.otl.liveapi.model.PlayerUrl;
import org.springframework.data.repository.CrudRepository;

public interface PlayerURLRepository extends CrudRepository<PlayerUrl, PlayerId> {
}
