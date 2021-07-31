package com.darkdevildev.springredisexample.service;


import com.darkdevildev.springredisexample.model.Test;

import java.util.List;

public interface TestService {
    List<Test> listTests();
    List<Test> loadTestDetailsToRedis();
}
