package com.riyadbank.course.app.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.instance.HazelcastInstanceFactory;

@Configuration
@Component
public class CacheConfig extends CachingConfigurerSupport{

	public static final String ACCOUNTS = "accounts";
	
	@Bean
	public HazelcastInstance hazelCastInstance() {
		return HazelcastInstanceFactory.getOrCreateHazelcastInstance(createConfig());
		
	}
	
	//TODO-16: Find a way to add a new MapConfig and configure it to make some operations cacheables in Person Service
	public Config createConfig() {
		Config config = new Config("course-cache");
		config.addMapConfig(createMapConfig());
		return config;
	}
	
	public MapConfig createMapConfig() {
		MapConfig mapConfig = new MapConfig(ACCOUNTS);
		mapConfig.setTimeToLiveSeconds(360);
		mapConfig.setMaxIdleSeconds(20);
		
		return mapConfig;
	}
	
	
}
