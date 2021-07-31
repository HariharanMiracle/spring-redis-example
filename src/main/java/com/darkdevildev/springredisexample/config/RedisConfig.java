package com.darkdevildev.springredisexample.config;

import com.darkdevildev.springredisexample.model.Test;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
@PropertySource("classpath:application.properties")
public class RedisConfig {
    @Bean
    public LettuceConnectionFactory redisConnectionFactory(Environment env) {
        RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
        redisConf.setHostName(env.getProperty("spring.redis.host"));
        redisConf.setPort(Integer.parseInt(env.getProperty("spring.redis.port")));
        return new LettuceConnectionFactory(redisConf);
    }

    @Bean
    public RedisTemplate<String, Test> redisTemplate(Environment env) {
        RedisTemplate<String, Test> redisTemplate = new RedisTemplate<String, Test>();
        redisTemplate.setConnectionFactory(redisConnectionFactory(env));
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
