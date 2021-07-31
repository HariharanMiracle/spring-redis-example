package com.darkdevildev.springredisexample.repository;

import com.darkdevildev.springredisexample.model.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends CrudRepository<Test, String> {
}
