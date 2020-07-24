package com.kkd.exercise.springboot.dao;

import com.kkd.exercise.springboot.model.Member;

/**
 * Created by doubleknd26 on 2018-12-28.
 */
public interface MemberDao {
    Member findByName(String name);
    Member findByNameFromCache(String name);
    void clearByName(String name);
}
