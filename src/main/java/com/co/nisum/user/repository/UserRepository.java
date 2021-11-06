package com.co.nisum.user.repository;

import com.co.nisum.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByEmail(String email);
}
