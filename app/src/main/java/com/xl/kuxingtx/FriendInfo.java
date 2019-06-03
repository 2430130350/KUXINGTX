package com.xl.kuxingtx;

import java.util.ArrayList;

public class FriendInfo {
    private static FriendInfo friendInfo = new FriendInfo();

    private FriendInfo(){}

    public static FriendInfo getFriendInfo(){
        return friendInfo;
    }

    private ArrayList<Friend> Friends = new ArrayList<Friend>();

    private ArrayList<Friend> alreadyFriends=new ArrayList<Friend>();

    private ArrayList<Friend> requestFriends=new ArrayList<Friend>();

    public ArrayList<Friend> getFriends() {
        return Friends;
    }

    public ArrayList<Friend> getAlreadyFriends(){
        return alreadyFriends;
    }

    public ArrayList<Friend> getRequestFriends(){
        return requestFriends;
    }

    public void setFriends(ArrayList<Friend> Friends) {
        this.Friends = Friends;
    }

    public void sortFriends(){
        for(int i = 0; i< Friends.size(); i++){
            Friend tmpfriend= Friends.get(i);
            if(Friends.get(i).getMconfirm()==1 && Friends.get(i).getFconfirm()==1){
                alreadyFriends.add(tmpfriend);
            }else if(Friends.get(i).getMconfirm()==0 && Friends.get(i).getFconfirm()==1){
                requestFriends.add(tmpfriend);
            }
        }
    }
}
