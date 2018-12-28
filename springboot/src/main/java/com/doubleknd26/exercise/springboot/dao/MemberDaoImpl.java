package com.doubleknd26.exercise.springboot.dao;

import com.doubleknd26.exercise.springboot.model.Member;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * Created by doubleknd26 on 2018-12-28.
 */
@Repository
public class MemberDaoImpl implements MemberDao {

    @Override
    public Member findByName(String name) {
        process(2000);
        return new Member(name, 30);
    }

    @Override
    @Cacheable(value = "memberCache", key = "#name")
    public Member findByNameFromCache(String name) {
        process(2000);
        return new Member(name, 30);
    }

    @Override
    @CacheEvict(value = "memberCache", key = "#name")
    public void clearByName(String name) {
    }

    /**
     * assume processing time.
     * @param milliSeconds
     */
    private void process(long milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
