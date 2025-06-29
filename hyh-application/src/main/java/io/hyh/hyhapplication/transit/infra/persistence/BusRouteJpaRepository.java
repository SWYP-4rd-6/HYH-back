package io.hyh.hyhapplication.transit.infra.persistence;

import io.hyh.hyhapplication.transit.domain.BusRoute;
import io.hyh.hyhapplication.transit.domain.BusRouteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusRouteJpaRepository extends JpaRepository<BusRoute, Long>, BusRouteRepository {

    @Query("SELECT b FROM BusRoute b WHERE b.routeName LIKE %:keyword% ORDER BY b.routeName LIMIT :limit")
    List<BusRoute> searchByRouteName(@Param("keyword") String keyword, @Param("limit") int limit);

}
