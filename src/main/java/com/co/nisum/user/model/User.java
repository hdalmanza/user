package com.co.nisum.user.model;


import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;




@Builder
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private  Integer id;
    private String name;
  //  @Pattern(regexp = "^(.+)@(.+)$", message = "el campo email no es valido")
   // @Email(message = "Email should be valid")
    private String email;
    //@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "el campo password no es valido")
    private String password;
    private List<Phone> phones;

    private String created;
    private String modified;
    private  String last_login;
    private String token;
    private  boolean isactive;
}
