package com.se21.calbot.enums;

/**
 * Enum manager class. It contains all enums needed in the project at one place
 */
public class Enums {

    /**
     * Response send by calendar classes
     */
    public enum calApiResponse{
        Success,
        Failure,
        AuthFailure,
        TokenExpired,
        Null,
        //Keep adding more to enrich debugging arsenal
    }

    /**
     * Type of calendar being used by any user can be represented using this enum
     */
    public enum calendarType{
        googleCalendar,
        appleCalendar,
        samsungCalendar,
    }

    /**
     * All available operations types.
     */
    public enum operationType{
        Add,
        Update,
        Delete,
        Create,
        Retrieve,
        Optimise,

    }
}
