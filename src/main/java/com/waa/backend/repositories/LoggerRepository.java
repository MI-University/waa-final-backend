package com.waa.backend.repositories;

import com.waa.backend.domains.LoggerEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggerRepository extends JpaRepository<LoggerEntry, Long> {
}
