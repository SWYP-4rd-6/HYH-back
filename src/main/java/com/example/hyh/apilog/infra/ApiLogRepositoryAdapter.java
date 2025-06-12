package com.example.hyh.apilog.infra;

import com.example.hyh.apilog.domain.ApiLog;
import com.example.hyh.apilog.domain.ApiLogRepository;
import com.example.hyh.apilog.infra.jpa.ApiLogJpaEntity;
import com.example.hyh.apilog.infra.jpa.ApiLogJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ApiLogRepositoryAdapter implements ApiLogRepository {

    private final ApiLogJpaRepository apiLogJpaRepository;


    @Override
    public ApiLog save(ApiLog apiLog) {
        return apiLogJpaRepository.save(ApiLogJpaEntity.from(apiLog))
                .toDomainModel();
    }

}
