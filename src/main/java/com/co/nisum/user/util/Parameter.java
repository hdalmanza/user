package com.co.nisum.user.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Parameter {

    @Value("${message.exist.email}")
    public String messageEmailExist;
    @Value("${message.invalid.email}")
    public String invalidEmail;
    @Value("${message.invalid.password}")
    public String invalidPassword;

    @Value("${label.message}")
    public String labelMessage;

    @Value("${parameter.secret.key}")
    public String secretKey;
    @Value("${parameter.jwt.id}")
    public String jwtId;
    @Value("${parameter.jwt.rol}")
    public String jwtRol;
    @Value("${parameter.jwt.claim.authorities}")
    public String jwtAuthorities;
    @Value("${parameter.pattern.date.format}")
    public String patternDateFormat;
    @Value("${parameter.regex.email}")
    public String regexEmail;
    @Value("${parameter.regex.password}")
    public String regexPassword;


}
