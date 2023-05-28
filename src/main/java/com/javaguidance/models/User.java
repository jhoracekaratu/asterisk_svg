package com.javaguidance.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")

public class User {
    @Column(name = "id")
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Id
    int id;
    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;
    @Column(name = "email_address")
    String email_address;



}
