package com.se21.calbot.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tokens")
@JsonSerialize
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String discordId;
    @Column(name = "token")
    private String token;
    @Column(name = "code")
    private String code;
    @Column(name = "expires")
    private Long expiresInSeconds;
    @Column(name = "refresh")
    private String refreshToken;
    @Column(name = "scope")
    private String scope;
}
