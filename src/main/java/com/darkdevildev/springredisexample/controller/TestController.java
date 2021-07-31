package com.darkdevildev.springredisexample.controller;

import com.darkdevildev.springredisexample.model.Test;
import com.darkdevildev.springredisexample.service.CacheService;
import com.darkdevildev.springredisexample.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    CacheService cacheService;

    @GetMapping("/tests")
    public List<Test> testList(){
        try{
            System.out.println("testList");
            return cacheService.loadTestDetails();
        }
        catch (Exception e){
            return new ArrayList<>();
        }
    }

    @GetMapping("/testsPop")
    public List<Test> testsPop(){
        try{
            System.out.println("testList");
            return testService.loadTestDetailsToRedis();
        }
        catch (Exception e){
            return new ArrayList<>();
        }
    }

}
