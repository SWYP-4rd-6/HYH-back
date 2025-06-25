package io.hyh.hyhapplication.pack.infra;

import io.hyh.hyhapplication.pack.domain.CheckList;
import io.hyh.hyhapplication.pack.domain.CheckUserTest;
import io.hyh.hyhapplication.pack.domain.PackingListRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PackingListJpaRepository extends JpaRepository<CheckList, Long>, PackingListRepository {

    @Query("SELECT c FROM CheckList c WHERE c.user = :user")
    List<CheckList> getPackingListByUserId(@Param("user") CheckUserTest user);

}
