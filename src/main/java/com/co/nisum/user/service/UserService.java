package com.co.nisum.user.service;

import com.co.nisum.user.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Object>  create(User user);
}
