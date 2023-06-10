package com.aeontanvir.projectcosting.repositories;

import com.aeontanvir.projectcosting.domains.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
