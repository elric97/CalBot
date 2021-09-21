package com.se21.calbot.services;

import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;


import org.springframework.stereotype.Service;

@Service
public class GoogleCalendarService {

    String accessToken;
    String refreshToken;
    String authToken;
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final  List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);

    public String authenticate(String discordId) throws Exception {
        // Load client secrets.

        InputStream in = GoogleCalendarService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        String url = flow.newAuthorizationUrl().setRedirectUri("http://localhost:8080/test").setState(discordId).build();
        LocalServerReceiver receiver = new LocalServerReceiver();
//        Credential auth = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
//        System.out.println(auth.getAccessToken());
//        auth.`

//        GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();
        return url;
//        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
//        return null;
    }
}
