package com.xl.kuxingtx;

public class Friend {
    private long uid;           //本用户ID
    private long fid;           //好友的ID本体
    private long mconfirm;      //自己对好友的接受状态 1为接受，0位不接受
    private long fconfirm;      //好友对自己的接受状态
    private String nick_name;   //自己对好友的nick_name
    private String description; //自己对好友的描述

    public Friend(){}

    public Friend(long uid, long fid, long mconfirm, long fconfirm, String nick_name, String description){
        this.uid=uid;
        this.fid=fid;
        this.mconfirm=mconfirm;
        this.nick_name=nick_name;
        this.description=description;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getFid() {
        return fid;
    }

    public void setFid(long fid) {
        this.fid = fid;
    }

    public long getMconfirm() {
        return mconfirm;
    }

    public void setMconfirm(long mconfirm) {
        this.mconfirm = mconfirm;
    }

    public long getFconfirm() {
        return fconfirm;
    }

    public void setFconfirm(long fconfirm) {
        this.fconfirm = fconfirm;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
