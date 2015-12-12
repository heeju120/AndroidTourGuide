package com.example.rtse.japantourguide.schedule;

import java.io.Serializable;

/**
 * Created by RTSE on 2015-11-17.
 */

public class schedule_item implements Serializable{

    private long id;

    private int category;   //1:여행지 2:음식점 3:숙소 4:공항
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    private String location;
    private String content;

    schedule_item(){}

    schedule_item(int c,int y,int mon,int d, int h,int min, String l, String con){
        this.category = c;
        this.year = y;
        this.month = mon;
         this.day = d;
        this.hour = h;
        this.minute = min;
        this.location = l;
        this.content = con;

    }
    schedule_item(int i, int c,int y,int mon,int d, int h,int min, String l, String con){
        this.id = i;
        this.category = c;
        this.year = y;
        this.month = mon;
        this.day = d;
        this.hour = h;
        this.minute = min;
        this.location = l;
        this.content = con;

    }


    public void setId(long i){
        this.id = i;
    }

    public void setCategory(int c){
        this.category = c;
    }
    public void setDate(int y,int m, int d){
        this.year = y;
        this.month = m;
        this.day = d;
    }
    public void setTime(int h, int m){
        this.hour = h;
        this.minute = m;
    }

    public void setLocation(String l){
        this.location = l;
    }
    public void setContent(String c){
        this.content = c;
    }

    public int getCategory(){return this.category;}
    public int getYear(){   return this.year;    }
    public int getMonth(){  return this.month;  }
    public int getDay(){    return this.day;    }
    public int getHour(){   return this.hour;   }
    public int getMinute(){ return this.minute; }

    public String getLocation(){return this.location; }
    public String getContent(){ return this.content;   }

    public long getId(){ return this.id; }



}
