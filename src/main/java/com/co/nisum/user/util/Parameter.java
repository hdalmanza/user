package com.co.nisum.user.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Parameter {

    @Value("${message.exist.user}")
    private String messageUserExist;
    @Value("${message.exist.email}")
    private String messageEmailExist;
    @Value("${message.invalid.email}")
    private String invalidEmail;
    @Value("${message.invalid.password}")
    private String invalidPassword;

    @Value("${label.message}")
    private String labelMessage;

    @Value("${parameter.secret.key}")
    private String secretKey;
    @Value("${parameter.jwt.id}")
    private String jwtId;
    @Value("${parameter.jwt.rol}")
    private String jwtRol;
    @Value("${parameter.jwt.claim.authorities}")
    private String jwtAuthorities;
    @Value("${parameter.pattern.date.format}")
    private String patternDateFormat;
    @Value("${parameter.regex.email}")
    private String regexEmail;
    @Value("${parameter.regex.password}")
    private String regexPassword;


}
