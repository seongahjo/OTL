package com.otl.liveapi;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerURLRepository extends CrudRepository<PlayerUrl, PlayerId> {
}
