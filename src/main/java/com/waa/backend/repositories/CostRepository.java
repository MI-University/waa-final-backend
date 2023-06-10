package com.waa.backend.repositories;

import com.waa.backend.domains.Cost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostRepository extends JpaRepository<Cost, Long> {
}
