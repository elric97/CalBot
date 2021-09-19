package com.se21.Calendar;

public interface Calendar {

    void authenticate();
    void retrieveEvents();
    void updateEvents();
    void addEvents();
    void deleteEvents();
    void createNewUnscheduledCalendar();

}
