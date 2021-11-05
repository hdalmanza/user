package com.co.nisum.user.config;

import com.co.nisum.user.util.Parameter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class UserConfig {

    private final Parameter parameter;

    public UserConfig(Parameter parameter) {
        this.parameter = parameter;
    }

    @Bean
    public SimpleDateFormat simpleDateFormat(){
        String pattern = parameter.getPatternDateFormat();
        return new SimpleDateFormat(pattern);
    }

}
