package com.example.SpringBoot_test_app.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.SpringBoot_test_app.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
