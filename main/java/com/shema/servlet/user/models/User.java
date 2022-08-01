package com.shema.servlet.user.models;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import javax.naming.AuthenticationException;
import io.jsonwebtoken.*;

import com.google.gson.annotations.Expose;
import com.shema.servlet.db.Db;
import com.shema.servlet.helpers.Message;
import com.shema.servlet.user.dtos.Gender;
import com.shema.servlet.user.dtos.UserTypes;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class User {
    public String id;
    @NonNull
    public String username;
    @NonNull
    public String firstName;
    @NonNull
    private String lastName;
    @Expose(serialize = false)
    private String password;
    private Gender gender;
    private Integer age;
    private Long phone;
    private UserTypes role;

    public User() {
        id = UUID.randomUUID().toString();
    }

    public void encrptPass() {
        String encryptedPassword = "";
        for (int i = 0; i < this.password.length(); i++) {
            encryptedPassword = this.password.charAt(i) + encryptedPassword;
        }
        encryptedPassword = "**" + encryptedPassword + "<>"+ this.age + "**";
        this.setPassword(encryptedPassword);
    }
    

    public String descrptPass() {
        String decryptedPwd = "";
        String reversedPwd = this.password.substring(2, this.password.length() - 2).split("<>")[0];
        for (int i = 0; i < reversedPwd.length(); i++) {
            decryptedPwd = reversedPwd.charAt(i) + decryptedPwd;
        }
        return decryptedPwd;
    }

    public Message<String> login(String email, String Password) throws Exception {
        User foundedUser = Db.findUser(email);

        if (foundedUser == null)
            throw new AuthenticationException("Invalid Credentials");


        if (!Password.equals(foundedUser.descrptPass()))
            throw new AuthenticationException("Invalid Credentials");

        Claims claims = Jwts.claims().setSubject(foundedUser.username);
        claims.put("role", foundedUser.role.name());
        claims.put("username", foundedUser.username);

        Instant now = Instant.now();

        String jwtToken = Jwts.builder()
        .claim("role", foundedUser.role.name())
        .claim("username", foundedUser.username)
        .setSubject(foundedUser.username)
        .setId(foundedUser.id)
        .setIssuedAt(Date.from(now))
        .setExpiration(Date.from(now.plus(5l, ChronoUnit.HOURS)))
        .compact();

        return new Message<String>("User login Success", jwtToken);
    }
 
}