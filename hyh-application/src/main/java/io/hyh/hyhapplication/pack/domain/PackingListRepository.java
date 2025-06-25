package io.hyh.hyhapplication.pack.domain;

import java.util.List;

public interface PackingListRepository {
    List<CheckList> getPackingListByUserId(CheckUserTest user);
}
