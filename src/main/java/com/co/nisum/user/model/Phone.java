package com.co.nisum.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phone")
public class Phone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long idPhone;
    @Column(name = "number")
    private String number;
    @JsonProperty("citycode")
    @Column(name = "cityCode")
    private String cityCode;
    @JsonProperty("contrycode")
    @Column(name = "contryCode")
    private String contryCode;


}
