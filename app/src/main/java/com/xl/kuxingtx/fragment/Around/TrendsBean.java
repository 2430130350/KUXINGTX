package com.xl.kuxingtx.fragment.Around;

import android.util.Log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrendsBean implements Serializable {
    private String username;
    private long uid;

    private Date mTime;
    private String content;
    private List<String> imgUrl = new ArrayList<String>();

    public TrendsBean(){}

    public TrendsBean(String username, long uid, Date mTime, String content){
        this.username = username;
        this.uid = uid;
        this.mTime = mTime;
        this.content = content;
    }

    public List<String> getImgUrl() {
        if(imgUrl.size() == 0)
            getImgNumber();
        return imgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        this.imgUrl = imgUrl;
    }

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
        String pattern1 = "(.*?)(<img.*?>)";
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

    public int getImgNumber(){
        String pattern1 = "(.*?)<img.*?src=\"(.*?)\".*?>";
        // 创建 Pattern 对象
        Pattern r1 = Pattern.compile(pattern1);

        // 现在创建 matcher 对象
        Matcher m1 = r1.matcher(this.content);
        int number = 0;
        imgUrl.clear();
        while (m1.find( )) {
            number++;
            imgUrl.add(m1.group(2));
        }
        return number;
    }

}
