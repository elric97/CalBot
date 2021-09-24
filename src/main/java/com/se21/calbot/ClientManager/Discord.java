package com.se21.calbot.ClientManager;

import com.se21.calbot.Controller.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Discord implements ClientManager{

    String userId;
    String clientId;

    final Controller controllerObj;

    public Discord(Controller controllerObj) {
        this.controllerObj = controllerObj;
    }

    @Override
    public void update() {

    }

    @Override
    public void add() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void show() {

    }

    @Override
    public void suggestForNow() {

    }

    @Override
    public void suggestWeeklyUnscheduled() {

    }

    @Override
    public void processInput() {

    }

    @Override
    public void getLastMessageDataObject() {

    }
}
