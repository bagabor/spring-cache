package com.example.cachetest.config;

import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching // It can be moved to the app.class if the config is not required
@Configuration
public class CustomCacheConfig extends CachingConfigurerSupport {

    @Bean
    public net.sf.ehcache.CacheManager ehCacheManager() {
        CacheConfiguration tenSecondCache = new CacheConfiguration();
        tenSecondCache.setName("student-cache-ten-sec");
        tenSecondCache.setMemoryStoreEvictionPolicy("LRU");
        tenSecondCache.setMaxEntriesLocalHeap(1000); //max 1000 entity in cache
        tenSecondCache.setTimeToLiveSeconds(10); //for 10 sec max

        //        CacheConfiguration twentySecondCache = new CacheConfiguration();
        //        twentySecondCache.setName("twenty-second-cache");
        //        twentySecondCache.setMemoryStoreEvictionPolicy("LRU");
        //        twentySecondCache.setMaxEntriesLocalHeap(1000);
        //        twentySecondCache.setTimeToLiveSeconds(20);

        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        config.addCache(tenSecondCache);
        return net.sf.ehcache.CacheManager.newInstance(config);
    }

    @Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }
}
