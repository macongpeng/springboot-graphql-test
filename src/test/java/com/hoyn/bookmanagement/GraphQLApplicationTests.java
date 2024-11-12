package com.hoyn.bookmanagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import static com.hoyn.bookmanagement.config.CachingConfig.BOOK_CACHE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GraphQLApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void main() {
        GraphQLApplication.main(new String[] {});
    }

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void givenCacheManagerCustomizerWhenBootstrappedThenCacheManagerCustomized() {
        assertThat(cacheManager.getCacheNames())
                .containsOnly(BOOK_CACHE);
    }

}
