package com.se21.calbot.ClientManager;

public interface ClientManager {

    void update();
    void setClientId(String clientId);
    String getClientId();
    void add();
    void delete();
    void show();
    void suggestForNow();
    void suggestWeeklyUnscheduled();
    String processInput(String msg);
    void getLastMessageDataObject();
}