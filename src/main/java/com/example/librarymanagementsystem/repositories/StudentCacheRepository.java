package com.example.librarymanagementsystem.repositories;

import com.example.librarymanagementsystem.models.Student;
import com.example.librarymanagementsystem.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class StudentCacheRepository {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public Student get(Integer studentId) {
        log.info("Redis Key to get student: " + getKey(studentId));
        Object result = redisTemplate.opsForValue().get(getKey(studentId));
        if(result != null) log.info(((Student)result).getName());
        return result != null ? (Student) result : null;
    }

    public void set(Student student) {
        redisTemplate.opsForValue().set(getKey(student.getId()), student);
    }

    private String getKey(Integer studentId) {
        return Constants.STUDENT_CACHE_KEY_PREFIX + studentId;
    }
}
