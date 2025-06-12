package com.example.hyh.apilog.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiLogJpaRepository extends JpaRepository<ApiLogJpaEntity, Long> {
}
