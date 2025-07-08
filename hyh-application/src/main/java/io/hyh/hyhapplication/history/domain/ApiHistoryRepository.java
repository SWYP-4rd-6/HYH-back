package io.hyh.hyhapplication.history.domain;

import java.util.Optional;

public interface ApiHistoryRepository {

    ApiHistory save(ApiHistory apiHistory);

    Optional<ApiHistory> findById(Long id);

}
