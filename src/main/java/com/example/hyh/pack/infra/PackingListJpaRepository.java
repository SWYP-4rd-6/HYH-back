package com.example.hyh.pack.infra;

import com.example.hyh.pack.domain.CheckList;
import com.example.hyh.pack.domain.CheckUserTest;
import com.example.hyh.pack.domain.PackingListRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PackingListJpaRepository extends JpaRepository<CheckList, Long>, PackingListRepository {

    @Query("SELECT c FROM CheckList c WHERE c.user = :user")
    List<CheckList> getPackingListByUserId(@Param("user") CheckUserTest user);

}
