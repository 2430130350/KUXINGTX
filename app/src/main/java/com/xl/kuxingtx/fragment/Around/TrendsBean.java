package com.xl.kuxingtx.fragment.Around;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TrendsBean {
    private String username;
    private Date mTime;
    private String content;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getmTime() {
        return mTime;
    }

    public void setmTime(Date mTime) {
        this.mTime = mTime;
    }

    public long getTimeStamp(){
        return this.mTime.getTime();
    }

    public String getDateString(String format){
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(this.mTime);
    }

    public String getDateString(){
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(this.mTime);
    }

}
