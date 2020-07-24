package com.kkd.exercise.springboot;

import com.kkd.exercise.springboot.dao.MemberDao;
import com.kkd.exercise.springboot.model.Member;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by doubleknd26 on 2018-12-28.
 */
@Controller
@SpringBootApplication
public class MemberApplication {
    private static final Logger logger = LogManager.getLogger(MemberApplication.class);

    @Autowired
    private MemberDao memberDao;

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }

    @ResponseBody
    @GetMapping("/member/{name}")
    public Member getMember(@PathVariable String name) {
        long start = System.currentTimeMillis();
        Member member = memberDao.findByName(name);
        long end = System.currentTimeMillis();

        logger.info(name+ "의 NoCache 수행시간 : "+ Long.toString(end-start));
        return member;
    }

    @ResponseBody
    @GetMapping("/member/cache/{name}")
    public Member getCacheMember(@PathVariable String name) {
        long start = System.currentTimeMillis();
        Member member = memberDao.findByNameFromCache(name);
        long end = System.currentTimeMillis();

        logger.info(name+ "의 Cache 수행시간 : "+ Long.toString(end-start));
        return member;
    }

    @GetMapping("/member/clear/{name}")
    @ResponseBody
    public String clear(@PathVariable String name) {
        memberDao.clearByName(name);
        return "cache clear!";
    }
}
