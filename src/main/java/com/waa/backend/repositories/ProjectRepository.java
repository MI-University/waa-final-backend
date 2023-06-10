package com.waa.backend.repositories;

import com.waa.backend.domains.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
