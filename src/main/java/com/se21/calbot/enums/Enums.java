package com.se21.calbot.enums;

public class Enums {

    public enum calApiResponse{
        Success,
        Failure,
        AuthFailure,
        TokenExpired,
        Null,
        //Keep adding more to enrich debugging arsenal
    }

    public enum calendarType{
        googleCalendar,
        appleCalendar,
        samsungCalendar,
    }

    public enum operationType{
        Add,
        Update,
        Delete,
        Create,
        Retrieve,

    }
}
