package io.hyh.hyhapplication.history.infra.jpa;

import io.hyh.hyhapplication.history.domain.ApiHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiHistoryJpaRepository extends JpaRepository<ApiHistory, Long> {
}
