package com.se21.calbot.interfaces;

/**
 * This interface provides basic functionality for GUI
 * or any user interacting(like discord, telegram) class
 */
public interface ClientManager {

    /**
     * It will process any request for data update
     */
    void update();
    /**
     * It will set the value of a new clientId to session variables(currently they
     * are local, later we will move them to Spring session variables)
     */
    void setClientId(String clientId);

    /**
     * To get the current sessions clientId, for some operations.
     *
     * @return clientId
     */
    String getClientId();

    /**
     * It processes the message to add any new details from user
     */
    void add();
    /**
     * It processes the message to delete any new details from user
     */
    void delete();
    /**
     * It will process any request to display something, in future there can be multiple type of views
     */
    void show();

    /**
     * It will suggest the user, what he should do for now(out of his available pending tasks)
     */
    void suggestForNow();

    /**
     * It will process the request to show the unscheduled events in calendar
     */
    void suggestWeeklyUnscheduled();

    /**
     * This is first function which will be called by the eventListener.
     * Purpose is to do basic msg parsing and based on received input further call for next operation function.
     * @param msg raw_input from user
     * @return response
     */
    String processInput(String msg);

    /**
     * This will be used in future to make bot more interactive,
     * where user can select some option from previously shown options
     */
    void getLastMessageDataObject();
}
