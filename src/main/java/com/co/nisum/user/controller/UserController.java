package com.co.nisum.user.controller;

import com.co.nisum.user.model.User;
import com.co.nisum.user.service.UserService;
import com.co.nisum.user.util.GeneralUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class UserController {

    private final GeneralUtil generalUtil;
    private final UserService userService;

    public UserController(GeneralUtil generalUtil, UserService userService) {
        this.generalUtil = generalUtil;
        this.userService = userService;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody @Valid User user){
        ResponseEntity<Object> response = null;
        try {
            response = userService.create(user);
        }catch (Exception exception){
            new ResponseEntity<>( generalUtil.buildMessage("Error " + exception.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
