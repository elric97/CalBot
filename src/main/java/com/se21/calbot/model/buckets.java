package com.se21.calbot.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Queue;

@Setter
@Getter
public class buckets {

    double freeTime;
    Queue<events> scheduling;


    public boolean canFit(double hours)
    {
        if(freeTime>=hours)
            return true;
        else
            return false;
    }
    public boolean blockTime(double hours, events event)
    {
        if(canFit(hours))
        {
            freeTime = freeTime - hours;
            events newEvent = new events();
            newEvent.numOfHours = hours;
            newEvent.title = event.title;
            newEvent.deadline = event.deadline;
            newEvent.startTime = event.startTime;
            scheduling.add(newEvent);
            return true;
        }
        return false;
    }

}
