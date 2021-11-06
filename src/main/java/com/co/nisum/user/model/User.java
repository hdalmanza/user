package com.co.nisum.user.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private  String id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Phone> phones;
    @Column(name = "created")
    private String created;
    @Column(name = "modified")
    private String modified;
    @Column(name = "last_login")
    private  String last_login;
    @Column(name = "token")
    private String token;
    @Column(name = "isActive")
    private  boolean isActive;
}
