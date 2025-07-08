package io.hyh.hyhapplication.history.infra;

import io.hyh.hyhapplication.history.domain.ApiHistory;
import io.hyh.hyhapplication.history.domain.ApiHistoryRepository;
import io.hyh.hyhapplication.history.infra.jpa.ApiHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
class ApiHistoryRepositoryAdapter implements ApiHistoryRepository {

    private final ApiHistoryJpaRepository apiHistoryJpaRepository;


    @Override
    public ApiHistory save(ApiHistory apiHistory) {
        return apiHistoryJpaRepository.save(apiHistory);
    }

    @Override
    public Optional<ApiHistory> findById(Long id) {
        return apiHistoryJpaRepository.findById(id);
    }

}
