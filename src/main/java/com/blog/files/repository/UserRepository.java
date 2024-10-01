package com.blog.files.repository;

import com.blog.files.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUserIdAndDeletedFalse(String userId);
    Optional<User> findByEmail(String email);



}
