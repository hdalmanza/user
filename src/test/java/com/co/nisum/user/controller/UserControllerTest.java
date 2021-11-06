package com.co.nisum.user.controller;

import com.co.nisum.user.model.Phone;
import com.co.nisum.user.model.User;
import com.co.nisum.user.service.UserService;
import com.co.nisum.user.util.GeneralUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private  GeneralUtil generalUtil;
    @Mock
    private  UserService userService;
    private User user;

    @Before
    public void setup(){
        Phone phone = Phone.builder()
                .cityCode("1")
                .countryCode("2")
                .number("73883743784")
                .build();
        List<Phone> phones = Arrays.asList(phone);
        user =  User.builder()
                .name("Juan Perez")
                .email("juan.perez@nisum.com")
                .password("xV245Nisum")
                .phones(phones)
                .build();
    }

    @Test
    public void createUserWhenIsSuccessTest(){
         String message = new Gson().toJson(user);
        ResponseEntity<Object> responseMock =  new ResponseEntity<>(message, HttpStatus.OK);
        Mockito.when(userService.create(user)).thenReturn(responseMock);
        ResponseEntity<Object> responseExpect = userController.createUser(user);
        Assert.assertNotNull(responseExpect);
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("200"));
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("OK"));
    }


    @Test
    public void createUserWhenExistUserTest(){
        JsonObject message = new JsonObject();
        message.addProperty("mensaje", "Usuario existe");
        ResponseEntity<Object> responseMock =  new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        Mockito.when(userService.create(user)).thenReturn(responseMock);
        ResponseEntity<Object> responseExpect = userController.createUser(user);
        Assert.assertNotNull(responseExpect);
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("500"));
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("INTERNAL_SERVER_ERROR"));
    }

    @Test
    public void createUserWhenEmailIsInvalidTest(){
        JsonObject message = new JsonObject();
        user.setEmail("correo.correo");
        message.addProperty("mensaje", "Formato de correo Invalid");
        ResponseEntity<Object> responseMock =  new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        Mockito.when(userService.create(user)).thenReturn(responseMock);
        ResponseEntity<Object> responseExpect = userController.createUser(user);
        Assert.assertNotNull(responseExpect);
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("400"));
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("BAD_REQUEST"));
        Assert.assertTrue(responseExpect.getBody().toString().contains("Formato de correo Invalid"));

    }


    public void createUserWhenPasswordIsInvalid(){
        JsonObject message = new JsonObject();
        user.setPassword("1234");
        message.addProperty("mensaje", "Formato de password Invalid");
        ResponseEntity<Object> responseMock =  new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        Mockito.when(userService.create(user)).thenReturn(responseMock);
        ResponseEntity<Object> responseExpect = userController.createUser(user);
        Assert.assertNotNull(responseExpect);
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("400"));
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("BAD_REQUEST"));
        Assert.assertTrue(responseExpect.getBody().toString().contains("Formato de password Invalid"));

    }


}
