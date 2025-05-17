package com.example.spring.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="custom_user")
public class CustomUser {
    @Id
    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

}
