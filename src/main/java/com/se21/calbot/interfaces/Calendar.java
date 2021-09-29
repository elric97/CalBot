package com.se21.calbot.interfaces;

import com.se21.calbot.enums.Enums;
import org.joda.time.DateTime;
import org.json.simple.JSONObject;

/**
 * This interface provides basic functionality for Calendar classes
 */
public interface Calendar {

    /**
     * It serves any authenticate request
     * @param id User unique identifier
     * @return URL for authentication
     */
    String authenticate(String id);

    /**
     * Saves received access token to db and perform some basic functionalities like getting
     * access token and refresh token further.
     * @param code auth code
     * @param id user Id
     */
    void saveAccessToken(String code, String id);

    /**
     * Retrieves event from calendar
     * @param calId calendar id(One google calendar can have multiple calendars at same time)
     * @return JSON objects of calendar returned events
     * @throws Exception
     */
    org.json.JSONObject retrieveEvents(String calId) throws Exception;

    /**
     * Updates existing events to calendar
     * @param req type is JSON object, exact parameters not yet decided, but can be changed based on different type of calendars
     * @return response from calendar
     */
    Enums.calApiResponse updateEvents(JSONObject req);

    /**
     * Adds event to calendar, currently you can only add unscheduled
     * events i.e. when there is no start date but only deadline and number of hours need to be dedicated
     * @param title Event title
     * @param hours Number of hours to be dedicated
     * @param deadline deadline for this activity
     * @return Calendar response
     */
    Enums.calApiResponse addEvents( String title, String hours, String deadline);

    /**
     * Deletes one or some event
     * @return Calendar response
     */
    Enums.calApiResponse deleteEvents();

    /**
     * Create a new Unscheduled calendar in user's selected calendar
     * @return Calendar response
     */
    Enums.calApiResponse createNewUnscheduledCalendar();

    /**
     * This is temporary function, until spring session variables are not maintained
     */
    void setUserVariable();
}
