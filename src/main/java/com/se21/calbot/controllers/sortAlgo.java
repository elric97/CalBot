package com.se21.calbot.controllers;

import com.se21.calbot.model.events;

import java.util.Comparator;

public class sortAlgo implements Comparator<events> {

    @Override
    public int compare(events o1, events o2) {
        if(o1.deadline.getMillis() < o2.deadline.getMillis())
        {
            return -1;// o2 is greater than o1, so by default increasing order will keep o1 first
        }
        else if(o1.deadline.getMillis() == o2.deadline.getMillis())
        {
            if(o1.numOfHours > o2.numOfHours)
            {
                return -1;
            }
        }
        return 0;
    }
}
