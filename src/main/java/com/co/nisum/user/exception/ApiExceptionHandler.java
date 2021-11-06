package com.co.nisum.user.exception;

import com.co.nisum.user.util.GeneralUtil;
import com.co.nisum.user.util.Parameter;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

//@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    private final GeneralUtil generalUtil;
    private final Parameter parameter;

   // @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> handleException(Exception exception, WebRequest webRequest){
        String bodyResponse = "";
       if(exception.getMessage().contains("Email")){
           bodyResponse = generalUtil.buildMessage(parameter.invalidEmail);
       }
       if (exception.getMessage().contains("Password")){
           bodyResponse = generalUtil.buildMessage(parameter.invalidPassword);
       }
        JsonObject message = new JsonObject();
        message.addProperty(parameter.labelMessage, parameter.invalidPassword);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
