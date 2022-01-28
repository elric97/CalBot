package com.se21.calbot.controllers;

import com.se21.calbot.model.buckets;
import com.se21.calbot.model.events;
import io.opencensus.trace.Link;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class schedulerAlgo {

    public static List<events> jsonToEventObjects(JSONArray EventList, boolean isUnscheduled) throws JSONException {
        List<events> jsonEventList = new ArrayList<events>();
        for (int i = 0; i < EventList.length(); i++) {
            JSONObject jsonLineItem = EventList.getJSONObject(i);
            events eventObj = new events();
            eventObj.startTime = new DateTime(jsonLineItem.getJSONObject("start").getString("dateTime"));
            eventObj.deadline = new DateTime(jsonLineItem.getJSONObject("end").getString("dateTime"));
            eventObj.title = jsonLineItem.getString("summary");
            if(isUnscheduled)
            {
                String[] arrOfStr = eventObj.title.split("#", 2);
                eventObj.title = arrOfStr[0];
                eventObj.numOfHours =  Integer.parseInt(arrOfStr[1]);
            }
            else
            {
                Duration duration = new Duration(eventObj.startTime, eventObj.deadline);
                long minutes = duration.getStandardMinutes();
                eventObj.numOfHours = minutes/60.0;
            }
            jsonEventList.add(eventObj);
        }
        return jsonEventList;
    }

    public static double totalAvailableTime(List<buckets> dayBuckets, int deadlineDayOfWeek)
    {
        double availableTime = 0;
        int todayDayOfWeek = DateTime.now().getDayOfWeek();
        for(int i = todayDayOfWeek;i<=deadlineDayOfWeek;i++)
        {
            availableTime += dayBuckets.get(i).getFreeTime();
        }
        return availableTime;
    }

    public static String createBuckets(List<events> scheduled, List<events> unscheduled){
        List<buckets> dayBuckets = new ArrayList<buckets>();
        //Initialise all buckets
        for(int i = 0;i<8;i++)
        {
            dayBuckets.add(new buckets());
            //for now set it to hardcoded value
            dayBuckets.get(i).setFreeTime(10);
            dayBuckets.get(i).setScheduling(new LinkedList<events>());
        }

        //Sort unscheduled events in list
        Collections.sort(unscheduled, new sortAlgo());
        for (int i = 0; i < unscheduled.size(); i++) {
            System.out.println(unscheduled.get(i).title + unscheduled.get(i).deadline);
        }

        //fill scheduled events in bucket
        for(int i = 0;i<scheduled.size();i++)
        {
            int dayOfWeek = scheduled.get(i).startTime.getDayOfWeek();
            buckets dayBucket = dayBuckets.get(dayOfWeek);
            dayBucket.blockTime(scheduled.get(i).numOfHours, scheduled.get(i));
            System.out.println(scheduled.get(i).numOfHours);
        }
        System.out.println("unscheduled");
        //fill unscheduled events in bucket
        for(int i = 0;i<unscheduled.size();i++)
        {
            int dayOfWeek = unscheduled.get(i).deadline.getDayOfWeek();
            int todayDayOfWeek = DateTime.now().getDayOfWeek();
            buckets dayBucket;
            double totalAvailableTime = totalAvailableTime(dayBuckets, dayOfWeek);
            if(totalAvailableTime >= unscheduled.get(i).numOfHours)
            {
                //divide whole unscheduled event in ratio of each day free time until deadline
                for(int j = todayDayOfWeek;j<=dayOfWeek;j++)
                {
                    dayBucket = dayBuckets.get(j);
                    double ratioHours = (dayBucket.getFreeTime()/totalAvailableTime)* unscheduled.get(i).numOfHours;
                    dayBucket.blockTime(ratioHours, unscheduled.get(i));
                }
            }
            else
            {
                System.out.println("No way to fit this event: " + unscheduled.get(i).title + ", deadline:"+ unscheduled.get(i).deadline);
                System.out.println("Total available time before deadline: "+ totalAvailableTime+", but you need: "+unscheduled.get(i).numOfHours);
            }
        }

        return showBuckets(dayBuckets);
    }
    public static String showBuckets(List<buckets> dayBuckets){

        int todayDayOfWeek = DateTime.now().getDayOfWeek();
        buckets bucket;
        String Days[] = {"", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String sortedData = "";

        for(int i = todayDayOfWeek;i<=7;i++)
        {
            sortedData += "\n";
            bucket = dayBuckets.get(i);
            sortedData += "Events for "+ Days[i] + " are like this: \n";
            sortedData += "You have free time: "+ bucket.getFreeTime() + " hours\n";
            Iterator iterator = bucket.getScheduling().iterator();
            while (iterator.hasNext()) {
                events obj = (events) iterator.next();
                sortedData += obj.title + " do it for: "+ obj.numOfHours + " hours\n";
            }
        }
        System.out.println(sortedData);
        return sortedData;
    }
}
