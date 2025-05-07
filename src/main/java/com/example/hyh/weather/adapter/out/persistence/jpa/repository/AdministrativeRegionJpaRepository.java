package com.example.hyh.weather.adapter.out.persistence.jpa.repository;

import com.example.hyh.weather.adapter.out.persistence.jpa.entity.AdministrativeRegionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministrativeRegionJpaRepository extends JpaRepository<AdministrativeRegionJpaEntity, Long> {

    Optional<AdministrativeRegionJpaEntity> findByRegionDepth1AndRegionDepth2AndRegionDepth3(String regionDepth1, String regionDepth2, String regionDepth3);

}
