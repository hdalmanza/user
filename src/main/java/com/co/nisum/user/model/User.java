package com.co.nisum.user.model;


import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private  String id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Phone> phones;
    @Column(name = "created")
    private String created;
    @Column(name = "modified")
    private String modified;
    @Column(name = "last_login")
    private  String last_login;
    @Column(name = "token")
    private String token;
    @Column(name = "isactive")
    private  boolean isactive;
}
