package com.co.nisum.user.service;

import com.co.nisum.user.model.User;
import com.co.nisum.user.repository.UserRepository;
import com.co.nisum.user.util.GeneralUtil;
import com.co.nisum.user.util.Parameter;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {


    private final GeneralUtil generalUtil;
    private final UserRepository userRepository;
    private  final Parameter parameter;

    public UserServiceImpl(GeneralUtil generalUtil, UserRepository userRepository,
                            Parameter parameter) {
        this.generalUtil = generalUtil;
        this.userRepository = userRepository;
        this.parameter = parameter;
    }

    @Override
    public ResponseEntity<Object> create(User user) {
        if(Pattern.matches(parameter.getRegexEmail(), user.getEmail())){
            return new ResponseEntity<>( generalUtil.buildMessage(parameter.getInvalidEmail()),
                    HttpStatus.BAD_REQUEST);
        }
        if(Pattern.matches(parameter.getRegexPassword(), user.getPassword())){
            return new ResponseEntity<>( generalUtil.buildMessage(parameter.getInvalidPassword()),
                    HttpStatus.BAD_REQUEST);
        }
        if(existUser(user)) {
            return new ResponseEntity<>(generalUtil.buildMessage(parameter.getMessageEmailExist()),
                        HttpStatus.FAILED_DEPENDENCY);
        }
        if(existEmail(user)){
            return new ResponseEntity<>( generalUtil.buildMessage(parameter.getMessageUserExist()),
                            HttpStatus.FAILED_DEPENDENCY);
        }
        fillDataUser(user);
        saveUser(user);
        return new ResponseEntity<>(new Gson().toJson(user),HttpStatus.OK);
    }




    private void fillDataUser(User user){
        user.setToken(generalUtil.getJwtToken(user.getName()));
        String date = generalUtil.getDate();
        user.setCreated(date);
        user.setModified(date);
        user.setLast_login(date);
        user.setIsactive(true);
    }


    private boolean existUser(User user){
        Integer id = user.getId();
        Optional<User> userFind = userRepository.findById(user.getId());
        return userFind.filter(data -> !data.getId().equals(null)).isPresent();

    }

    private boolean existEmail(User user){
        Integer id = user.getId();
        Optional<User> userFind = userRepository.findById(user.getId());
        return userFind.filter(data -> !data.getId().equals(null)).isPresent();

    }


    private  void saveUser(User user){
        userRepository.save(user);
    }
}
