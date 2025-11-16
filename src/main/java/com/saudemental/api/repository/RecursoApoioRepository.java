package com.saudemental.api.repository;

import com.saudemental.api.model.RecursoApoio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursoApoioRepository extends JpaRepository<RecursoApoio, Long> {
}
