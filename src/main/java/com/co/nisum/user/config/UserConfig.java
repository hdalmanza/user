package com.co.nisum.user.config;

import com.co.nisum.user.util.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
@AllArgsConstructor
public class UserConfig {

    private final Parameter parameter;

    @Bean
    public SimpleDateFormat simpleDateFormat(){
        String pattern = parameter.patternDateFormat;
        return new SimpleDateFormat(pattern);
    }

}
