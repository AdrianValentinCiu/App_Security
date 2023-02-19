package com.security.API_App.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data // getters, setters, constructor, equals, hashcode
@Builder // design pattern builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user") // exista deja o tabela user in DB
public class User {
    @Id
    @GeneratedValue// default value is AUTO --> (strategy = GenerationType.AUTO) // autogenreaza id-ul
    private Integer id; // PK
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
