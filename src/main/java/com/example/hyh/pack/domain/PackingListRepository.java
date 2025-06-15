package com.example.hyh.pack.domain;

import java.util.List;

public interface PackingListRepository {
    List<CheckList> getPackingListByUserId(CheckUserTest user);
}
