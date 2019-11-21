package com.otl.liveapi;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import static javax.swing.text.html.HTML.Tag.SELECT;

public interface PlayerURLRepository extends CrudRepository<PlayerURL, Integer> {
    List<PlayerURL> findByPlayerId(PlayerId playerId);
}
