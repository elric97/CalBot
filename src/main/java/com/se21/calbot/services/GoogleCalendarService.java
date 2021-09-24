package com.se21.calbot.services;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.CalendarScopes;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;


import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.se21.calbot.enums.Enums;
import com.se21.calbot.interfaces.Calendar;
import com.se21.calbot.model.User;
import com.se21.calbot.repositories.TokensRepository;
import lombok.extern.java.Log;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log
public class GoogleCalendarService implements Calendar {

    @Autowired
    TokensRepository tokensRepository;

    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final  List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);

    private GoogleClientSecrets clientSecrets;

    @Override
    public String authenticate(String discordId) {

        try {
            // Load client secrets.

            InputStream in = GoogleCalendarService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
            if (in == null) {
                throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
            }
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

            // Build flow and trigger user authorization request.
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                    .setAccessType("offline")
                    .build();

            String url = flow.newAuthorizationUrl().setRedirectUri("http://localhost:8080/test").setState(discordId).build();
            return url;
        } catch (Exception e) {
            log.severe("Google auth URL exception - " + e.getMessage());
        }

        return "Unable to authorize right now, please try again after sometime";
    }

    @Override
    public void saveAccessToken(String authCode, String discordId) {

        try {
            String clientId = clientSecrets.getWeb().getClientId();
            String clientSecret = clientSecrets.getWeb().getClientSecret();

            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            TokenResponse response = new GoogleAuthorizationCodeTokenRequest(HTTP_TRANSPORT, JSON_FACTORY, clientId,
                    clientSecret, authCode, "http://localhost:8080/test")
                    .setGrantType("authorization_code").execute();

            if(response == null || response.getAccessToken() == null
                    || response.getExpiresInSeconds() == null
                    || response.getScope() == null) {
                log.severe("Invalid response from Google Token API");
                return;
            }

            //save code and token in db mapped to discord id
            tokensRepository.save(new User(discordId,
                    response.getAccessToken(),
                    authCode,
                    response.getExpiresInSeconds(),
                    "",
                    response.getScope()));

        } catch (Exception e) {
            log.severe("Google access token exception - " + e.getMessage());
        }
    }

    @Override
    public JSONObject retrieveEvents(JSONObject req) throws Exception {
        return null;
    }

    @Override
    public Enums.calApiResponse updateEvents(JSONObject req) {
        return null;
    }

    @Override
    public Enums.calApiResponse addEvents() {
        return null;
    }

    @Override
    public Enums.calApiResponse deleteEvents() {
        return null;
    }

    @Override
    public Enums.calApiResponse createNewUnscheduledCalendar() {
        return null;
    }
}
