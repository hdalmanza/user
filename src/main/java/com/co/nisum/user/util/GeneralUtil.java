package com.co.nisum.user.util;

import com.google.gson.JsonObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class GeneralUtil {

    private final SimpleDateFormat simpleDateFormat;
    private final  Parameter parameter;

    public GeneralUtil(SimpleDateFormat simpleDateFormat, Parameter parameter) {
        this.simpleDateFormat = simpleDateFormat;
        this.parameter = parameter;
    }


    public String buildMessage(String info){
        JsonObject message = new JsonObject();
        message.addProperty(parameter.getLabelMessage(), info);
        return message.toString();
    }


    public String getJwtToken(String username){
        String secretKey = parameter.getSecretKey();
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(parameter.getJwtRol());
        return Jwts
                .builder()
                .setId(parameter.getJwtId())
                .setSubject(username)
                .claim(parameter.getJwtAuthorities(),
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
         //       .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

    }

    public String getDate(){
        return  simpleDateFormat.format(new Date());
    }
}
