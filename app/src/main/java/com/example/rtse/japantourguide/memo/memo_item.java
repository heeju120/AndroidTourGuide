package com.example.rtse.japantourguide.memo;

import java.io.Serializable;

/**
 * Created by RTSE on 2015-12-11.
 */
public class memo_item implements Serializable {
    private long id;
    private String title;
    private String context;

    public memo_item(){}

    public memo_item(String t,String c){
        this.title = t;
        this.context = c;
    }
    public memo_item(Long i,String t,String c){
        this.id = i;
        this.title = t;
        this.context = c;
    }


    public Long getId(){    return this.id; }
    public String getTitle(){   return this.title;  }
    public String getContext(){ return this.context;    }

    public void setId(Long i){  this.id = i;    }
    public void setTitle(String t){ this.title = t; }
    public void setContext(String c){   this.context = c;   }
}
