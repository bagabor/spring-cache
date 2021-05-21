package com.example.cachetest.service;

import java.util.Optional;

import com.example.cachetest.dao.model.Student;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static java.lang.Thread.sleep;

@Service
public class StudentService {

    //PRESS F12 in the browser just to check the response time
    //@CacheEvict(value = "student-cache", key = "'Student-cache'+#id", beforeInvocation = true) // it removes the value from the cache
    @Cacheable(value = "student-cache-ten-sec", unless = "#id >= 10", key = "'Student-cache'+#id") //it puts the value into the cache In case the id is less than 10
    public Optional<Student> getStudentById(final Long id) throws InterruptedException {
        sleep(5000);
        return Optional.of(new Student(id, "Teszt Student", 12));
    }
}
