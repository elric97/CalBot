package com.se21.calbot.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static com.se21.calbot.Calendar.Enums.calApiResponse.Failure;
import static com.se21.calbot.Calendar.Enums.calApiResponse.Success;

@Service
public class GoogleCalendar implements Calendar {

    String accessToken;
    String refreshToken;
    String authToken;
    String appId;
    String appSecret;
    String unScheduledCalId;
    CloseableHttpClient httpClient = HttpClients.createDefault();

    private GoogleCalendar googleCal;

    public String getUnScheduledCalId() {
        return unScheduledCalId;
    }

    public void setUnScheduledCalId(String unScheduledCalId) {
        this.unScheduledCalId = unScheduledCalId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }


    @Override
    public void initCal() {
        setAppId("311852341920-0p94jk9fjl1vkbk1c8b1jocpcfjmufeb.apps.googleusercontent.com");
        setAppSecret("C7NO-PZ2DQp48rkljQcmhCC3");
        // This will be replcaed with db calls later on and checks
        setAccessToken("ya29.a0ARrdaM-iaamjM2I1s-BqzF7EqewfPtPUS_8PdR1NaD4kHLSkwWAlIcyYjXE-fxMkPDa03eKlyYgyMvYb1fjfUdOUuYE7Kf2P1_BQsgGDzq8SMHtda6Qqocx8yKBOHOak0XNX3cPOmXNUEBDRMOxhIS4coNtF");
        setUnScheduledCalId("e0vbqpurrsj0qds2h2a46ob6rc@group.calendar.google.com");
    }

    @Override
    public JSONObject authenticate(JSONObject auth) {

        return null;
    }

    @Override
    public org.json.JSONObject retrieveEvents() {
        String access_token = getAccessToken();
        addEvents();
        String url = "https://www.googleapis.com/calendar/v3/calendars/primary/events?maxResults=20&access_token=" + access_token;
        HttpGet request = new HttpGet(url);


        try (CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();
            String content;
            if (entity != null) {
                content = EntityUtils.toString(entity);
                org.json.JSONObject obj = new org.json.JSONObject(content);
                System.out.println(obj.toString());

                return obj;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Enums.calApiResponse updateEvents(JSONObject req) {

        return Success;
    }

    @Override
    public Enums.calApiResponse addEvents() {
        DateTime startDt = new DateTime(2021,9,24,03,00,0,0, DateTimeZone.forID("America/New_York"));
        DateTime endDt = new DateTime(2021,9,24,04,00,0,0, DateTimeZone.forID("America/New_York"));
        JSONObject json = new JSONObject();
        String access_token = getAccessToken();
        String url = "https://www.googleapis.com/calendar/v3/calendars/"+getUnScheduledCalId() + "/events";
        HttpPost request = new HttpPost(url);
        request.setHeader("Authorization", "Bearer "+getAccessToken());
        request.setHeader("Content-Type", "application/json");

        StringEntity body = null;
        json.put("dateTime", endDt.toString());
        JSONObject json2 = new JSONObject();
        json2.put("dateTime", startDt.toString());
        JSONObject json3 = new JSONObject();
        json3.put("end", json);
        json3.put("start", json2);
        json3.put("summary", "Testing event");
        System.out.println(json3);

        try {
            body = new StringEntity(json3.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        body.setContentType("application/json");
        request.setEntity(body);

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();
            String content;
            if (entity != null) {
                content = EntityUtils.toString(entity);
                org.json.JSONObject obj = new org.json.JSONObject(content);
                System.out.println(obj);
                return Success;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Success;
    }

    @Override
    public Enums.calApiResponse deleteEvents() {

        return Success;
    }

    @Override
    public Enums.calApiResponse createNewUnscheduledCalendar() {

        String access_token = getAccessToken();
        String url = "https://www.googleapis.com/calendar/v3/calendars";
        HttpPost request = new HttpPost(url);
        StringEntity params = null;
        try {
            params = new StringEntity("{\"summary\":\"aPAS\"} ");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(getAccessToken());
        request.setHeader("Authorization", "Bearer "+getAccessToken());
        request.setHeader("Content-Type", "application/json");

        //params.add(new BasicNameValuePair("access_token", getAccessToken()));
        request.setEntity(params);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            // Get HttpResponse Status

            HttpEntity entity = response.getEntity();
            String content;
            if (entity != null) {
                content = EntityUtils.toString(entity);
                org.json.JSONObject obj = new org.json.JSONObject(content);
                setUnScheduledCalId(obj.getString("id"));//Need to store this id in Db
                return Success;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Failure;
    }

    @Override
    public String getTokens(String authToken)
    {
        String url = "https://oauth2.googleapis.com/token";
        HttpPost request = new HttpPost(url);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("client_id", getAppId()));
        params.add(new BasicNameValuePair("client_secret", getAppSecret()));
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));
        params.add(new BasicNameValuePair("redirect_uri", "http://localhost:8081/redirect"));
        params.add(new BasicNameValuePair("code", authToken));

        try {
            request.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try (
             CloseableHttpResponse response = httpClient.execute(request)) {
            // Get HttpResponse Status

            HttpEntity entity = response.getEntity();
            String content;
            if (entity != null) {
                content = EntityUtils.toString(entity);
                org.json.JSONObject obj = new org.json.JSONObject(content);
                String access_token = obj.getString("access_token");
                //String refresh_token = obj.getString("refresh_token");
                System.out.println(access_token);
                setAccessToken(access_token);
                //Now create new unscheduled calendar for app
                if(createNewUnscheduledCalendar() == Success)
                {
                    //Good
                }
                else
                {
                    System.out.println("aPAS calendar creation failed");
                    //Need to see how to recover from this case
                }

                return access_token;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
