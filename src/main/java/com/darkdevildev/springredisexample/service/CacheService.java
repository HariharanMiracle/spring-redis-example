package com.darkdevildev.springredisexample.service;

import com.darkdevildev.springredisexample.model.Test;
import com.darkdevildev.springredisexample.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CacheService implements Serializable {

    @Autowired
    TestRepository testRepository;

    @Autowired
    TestService testService;

    @Autowired
    private RedisTemplate<String, Test> redisTemplate;

    private String testKey = "test";


    public List<Test> loadTestDetails() throws Exception{
        List<Test> dto = new ArrayList<>();
        Map<Object, Object> list = redisTemplate.opsForHash().entries(testKey);
        for(Map.Entry stdObj : list.entrySet()){
            System.out.println(stdObj.getKey() + " : " + stdObj.getValue());
            Test test = (Test) stdObj.getValue();
            dto.add(test);
        }

        if(dto.isEmpty()){
            return (List<Test>) testRepository.findAll();
        }
        return dto;
    }

    /**    @Scheduled(cron = "0 0 4 * * ?")*/
    @Scheduled(cron = "0 0 */6 * * *")
    public void scheduled() {
        populate();
    }

    public void populate() {
        redisTemplate.delete(testKey);
        testService.loadTestDetailsToRedis();
    }
}
