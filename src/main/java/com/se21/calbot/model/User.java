package com.se21.calbot.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.se21.calbot.repositories.TokensRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
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
    @Column(name = "calType")
    private String calType;


    public void setAuthResponseBeans(String discordId, String token, String code, Long expiresInSeconds, String refreshToken, String scope, String calType)
    {
        this.discordId = discordId;
        this.token = token;
        this.code = code;
        this.expiresInSeconds = expiresInSeconds;
        this.refreshToken = refreshToken;
        this.scope = scope;
        this.calType = calType;
    }
}
