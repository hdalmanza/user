package com.co.nisum.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Builder
@Getter
@Setter
public class Phone {

    private String number;
    @JsonProperty("citycode")
    private String cityCode;
    @JsonProperty("contrycode")
    private String contryCode;

}
