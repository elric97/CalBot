package com.se21.calbot.ClientManager;


public interface ClientManager {

    void update();
    void add();
    void delete();
    void show();
    void suggestForNow();
    void suggestWeeklyUnscheduled();
    void processInput();
    void getLastMessageDataObject();
}
