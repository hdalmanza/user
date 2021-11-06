package com.co.nisum.user.service;

import com.co.nisum.user.model.User;
import com.co.nisum.user.repository.UserRepository;
import com.co.nisum.user.util.GeneralUtil;
import com.co.nisum.user.util.Parameter;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final GeneralUtil generalUtil;
    private final UserRepository userRepository;
    private  final Parameter parameter;


    @Override
    public ResponseEntity<Object> create(User user) {
        ResponseEntity<Object> response = validateRequest(user);
        if(null == response){
            fillDataUser(user);
            saveUser(user);
            response = new ResponseEntity<>(new Gson().toJson(user),HttpStatus.OK);
        }
        return response;
    }

    private ResponseEntity<Object> validateRequest(User user){
        ResponseEntity<Object> response = null;
        if(!user.getEmail().matches(parameter.regexEmail)){
            return new ResponseEntity<>( generalUtil.buildMessage(parameter.invalidEmail),
                    HttpStatus.BAD_REQUEST);
        }
       ;
        if(!user.getPassword().matches(parameter.regexPassword)){
            return new ResponseEntity<>( generalUtil.buildMessage(parameter.invalidPassword),
                    HttpStatus.BAD_REQUEST);
        }
        if(existEmail(user)){
            response = new ResponseEntity<>( generalUtil.buildMessage(parameter.messageEmailExist),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }


    private void fillDataUser(User user){
        user.setId(UUID.randomUUID().toString());
        user.setToken(generalUtil.getJwtToken(user.getName()));
        String date = generalUtil.getDate();
        user.setCreated(date);
        user.setModified(date);
        user.setLast_login(date);
        user.setIsactive(true);
    }


    private boolean existEmail(User user){
        List<User> users =userRepository.findByEmail(user.getEmail());
        User result = users.stream().findFirst().orElse(null);
         return null != result;
    }


    private  void saveUser(User user){
        userRepository.save(user);
    }
}
