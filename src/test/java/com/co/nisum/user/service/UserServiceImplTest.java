package com.co.nisum.user.service;

import com.co.nisum.user.model.Phone;
import com.co.nisum.user.model.User;
import com.co.nisum.user.repository.UserRepository;
import com.co.nisum.user.util.GeneralUtil;
import com.co.nisum.user.util.Parameter;
import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private  GeneralUtil generalUtil;
    @Mock
    private  UserRepository userRepository;
    @Mock
    private   Parameter parameter;
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
                .email("correo@correo.org")
                .password("xV245Nisum")
                .phones(phones)
                .build();
        parameter.regexEmail = "^(.+)@(\\S+)$";
        parameter.invalidEmail = "Formato invalido del correo";
        parameter.regexPassword = "^(?=.*[a-z])(?=.*[A-Z]).{2,}(?=.*[0-9]).{2,}$";
        parameter.invalidPassword = "Formato invalido de la contraseña";
        parameter.messageEmailExist = "El correo ya registrado";
    }

    @Test
    public void createUserSuccessTest(){
        Mockito.when(generalUtil.getJwtToken(user.getName()))
                .thenReturn("aG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
        Mockito.when(generalUtil.getDate()).thenReturn("11-06-2021");
        user.setCreated("11-06-2021");
        user.setLast_login("11-06-2021");
        user.setModified("11-06-2021");
        Mockito.when(userRepository.save(user)).thenReturn(user);
        ResponseEntity<Object> responseExpect = userService.create(user);
        Assert.assertNotNull(responseExpect);
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("200"));
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("OK"));
        Assert.assertTrue(responseExpect.getBody().toString().contains("11-06-2021"));
        Assert.assertTrue(responseExpect.getBody().toString().contains("aG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"));
    }

    @Test
    public void createUserWhenEmailIsInvalidTest(){
        user.setEmail("correo.correo");
        JsonObject message = new JsonObject();
        message.addProperty("mensaje", parameter.invalidEmail);
        Mockito.when(generalUtil.buildMessage(parameter.invalidEmail)).thenReturn(message.toString());
        ResponseEntity<Object> responseExpect = userService.create(user);
        Assert.assertNotNull(responseExpect);
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("400"));
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("BAD_REQUEST"));
        Assert.assertTrue(responseExpect.getBody().toString().contains("Formato invalido del correo"));
    }

    @Test
    public void createUserWhenPasswordIsInvalidTest(){
        user.setPassword("1234");
        JsonObject message = new JsonObject();
        message.addProperty("mensaje", parameter.invalidPassword);
        Mockito.when(generalUtil.buildMessage(parameter.invalidPassword)).thenReturn(message.toString());
        ResponseEntity<Object> responseExpect = userService.create(user);
        Assert.assertNotNull(responseExpect);
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("400"));
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("BAD_REQUEST"));
        Assert.assertTrue(responseExpect.getBody().toString().contains("Formato invalido de la contraseña"));
    }

    @Test
    public void createUserWhenExistTest(){
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Arrays.asList(user));
        JsonObject message = new JsonObject();
        message.addProperty("mensaje", parameter.messageEmailExist);
        Mockito.when(generalUtil.buildMessage(parameter.messageEmailExist)).thenReturn(message.toString());
        ResponseEntity<Object> responseExpect = userService.create(user);
        Assert.assertNotNull(responseExpect);
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("500"));
        Assert.assertTrue(responseExpect.getStatusCode().toString().contains("INTERNAL_SERVER_ERROR"));
        Assert.assertTrue(responseExpect.getBody().toString().contains("El correo ya registrado"));
    }
}
