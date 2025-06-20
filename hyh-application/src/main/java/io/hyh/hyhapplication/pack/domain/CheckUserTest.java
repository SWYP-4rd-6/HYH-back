package io.hyh.hyhapplication.pack.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "test_user")
@Getter
public class CheckUserTest {

    @Id
    @Column(name = "user_id")
    private String userId;

    @OneToMany(mappedBy = "user")
    private List<CheckList> checkLists = new ArrayList<>();

}
