package com.sajo.hackday.server.repository;

import com.sajo.hackday.server.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Long> {
}
