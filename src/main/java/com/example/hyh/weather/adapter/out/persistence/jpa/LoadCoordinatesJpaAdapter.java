package com.example.hyh.weather.adapter.out.persistence.jpa;

import com.example.hyh.weather.adapter.out.persistence.jpa.entity.AdministrativeRegionJpaEntity;
import com.example.hyh.weather.adapter.out.persistence.jpa.repository.AdministrativeRegionJpaRepository;
import com.example.hyh.weather.application.port.out.LoadCoordinatesPort;
import com.example.hyh.weather.domain.AdministrativeRegion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoadCoordinatesJpaAdapter implements LoadCoordinatesPort {

    private final AdministrativeRegionJpaRepository repository;

    @Override
    public AdministrativeRegion getCoordinatesForAddress(String depth1, String depth2, String depth3) {
        return repository.findByRegionDepth1AndRegionDepth2AndRegionDepth3(depth1, depth2, depth3)
                .map(this::mapToDomain)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 주소르 찾을 수 없습니다. %s %s %s".formatted(depth1, depth2, depth3)));
    }

    private AdministrativeRegion mapToDomain(AdministrativeRegionJpaEntity entity) {
        return new AdministrativeRegion(
                entity.getCategory(),
                entity.getAdminCode(),
                entity.getRegionDepth1(),
                entity.getRegionDepth2(),
                entity.getRegionDepth3(),
                entity.getGridX(),
                entity.getGridY(),
                entity.getLongitudeDegree(),
                entity.getLongitudeMinute(),
                entity.getLongitudeSecond(),
                entity.getLatitudeDegree(),
                entity.getLatitudeMinute(),
                entity.getLatitudeSecond(),
                entity.getLongitudeDecimal(),
                entity.getLatitudeDecimal()
        );
    }

}
