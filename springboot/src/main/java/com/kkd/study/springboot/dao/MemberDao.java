package com.kkd.study.springboot.dao;

import com.kkd.study.springboot.model.Member;

/**
 * Created by doubleknd26 on 2018-12-28.
 */
public interface MemberDao {
    Member findByName(String name);
    Member findByNameFromCache(String name);
    void clearByName(String name);
}
