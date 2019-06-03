package com.xl.kuxingtx;

import java.util.ArrayList;

public class FriendInfo {
    private static FriendInfo friendInfo = new FriendInfo();

    private FriendInfo(){}

    public static FriendInfo getFriendInfo(){
        return friendInfo;
    }

    private ArrayList<friend> friends=new ArrayList<friend>();

    public ArrayList<friend> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<friend> friends) {
        this.friends=friends;
    }
}
