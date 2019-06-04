package com.xl.kuxingtx.fragment.Around;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrendsBean {
    private String username;
    private long uid;

    private Date mTime;
    private String content;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
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

    public String getPartialContent(){
        String pattern1 = "(.*?)(<img.*?/>)";
        String partialContent = "";
        // 创建 Pattern 对象
        Pattern r1 = Pattern.compile(pattern1);

        // 现在创建 matcher 对象
        Matcher m1 = r1.matcher(this.content);
        if (m1.find( )) {
            partialContent = m1.group(1) + m1.group(2);
        } else {
            //全是字、
            if(this.content.length() > 50){
                partialContent = this.content.substring(0, 50);
            }
            else {
                partialContent = this.content;
            }
        }
        return partialContent;
    }

    /*public int getImgNumber(){
        String pattern1 = "<img(.*?)>";
        // 创建 Pattern 对象
        Pattern r1 = Pattern.compile(pattern1);

        // 现在创建 matcher 对象
        Matcher m1 = r1.matcher(this.content);
        if (m1.find( )) {

        }
    }*/
}
