package com.practice.redispractice;

import com.practice.redispractice.controller.MemberController;
import com.practice.redispractice.model.Member;
import com.practice.redispractice.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CachingTest {

    private final Long TEST_MEMBER_ID = 1L;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberController memberController;
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private RedisConnectionFactory factory;
    private Member savedMember;

    @BeforeEach
    public void setup() {
        savedMember = Member.createMember(TEST_MEMBER_ID, "CHANGEUN", "OH");
        memberService.save(savedMember);
    }

    @AfterEach
    public void cleanup() {
        factory.getConnection().serverCommands().flushDb();
    }

    @Test
    public void testCache() {
        // Member 조회
        memberController.getMemberById(TEST_MEMBER_ID);

        // 캐시 확인
        String cacheNames = "member";
        Cache cache = cacheManager.getCache(cacheNames);
        assertNotNull(cache);

        // 캐시된 데이터 확인
        Cache.ValueWrapper valueWrapper = cache.get(TEST_MEMBER_ID);
        assertNotNull(valueWrapper);

        // 저장한 Member, 캐시된 데이터 비교
        Member cachedMember = (Member) valueWrapper.get();
        assertNotNull(cachedMember);
        assertEquals(savedMember, cachedMember);
    }

}
