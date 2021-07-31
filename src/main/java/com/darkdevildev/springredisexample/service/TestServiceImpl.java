package com.darkdevildev.springredisexample.service;

import com.darkdevildev.springredisexample.model.Test;
import com.darkdevildev.springredisexample.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestRepository testRepository;

    @Autowired
    private RedisTemplate<String, Test> redisTemplate;

    @Override
    public List<Test> listTests() {
        return (List<Test>) testRepository.findAll();
    }

    @Override
    public List<Test> loadTestDetailsToRedis() {
        List<Test> dto = (List<Test>) testRepository.findAll();

        for(Test dtoObj : dto){
            String key = dtoObj.getId();
            redisTemplate.opsForHash().put("test", key, dtoObj);
        }

        return dto;
    }
}
