package com.co.nisum.user.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@AllArgsConstructor
public class GeneralUtil {

    private final SimpleDateFormat simpleDateFormat;
    private final  Parameter parameter;

    public String buildMessage(String info){
        JsonObject message = new JsonObject();
        message.addProperty(parameter.labelMessage, info);
        return message.toString();
    }


    public String getJwtToken(String username){
        String secretKey = parameter.secretKey;
        JWTCreator.Builder builder = JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date());
        return builder.sign(Algorithm.HMAC256(secretKey));
    }

    public String getDate(){
        return  simpleDateFormat.format(new Date());
    }
}
