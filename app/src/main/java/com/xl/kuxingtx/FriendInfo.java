package com.xl.kuxingtx;

import java.util.ArrayList;

public class FriendInfo {
    private static FriendInfo friendInfo = new FriendInfo();

    private FriendInfo(){}

    public static FriendInfo getFriendInfo(){
        return friendInfo;
    }

    private ArrayList<Friend> friends = new ArrayList<Friend>();

    private ArrayList<Friend> alreadyFriends=new ArrayList<Friend>();

    private ArrayList<Friend> requestFriends=new ArrayList<Friend>();

    public ArrayList<Friend> getFriends() {
        return friends;
    }

    public ArrayList<Friend> getAlreadyFriends(){
        return alreadyFriends;
    }

    public ArrayList<Friend> getRequestFriends(){
        return requestFriends;
    }

    public void setFriends(ArrayList<Friend> friends) {
        this.friends = friends;
    }

    public void sortFriends(){
        alreadyFriends.clear();
        requestFriends.clear();
        for(int i = 0; i< friends.size(); i++){
            Friend tmpfriend= friends.get(i);
            if(friends.get(i).getMconfirm()==1 && friends.get(i).getFconfirm()==1){
                alreadyFriends.add(tmpfriend);
            }else if(friends.get(i).getMconfirm()==0 && friends.get(i).getFconfirm()==1){
                requestFriends.add(tmpfriend);
            }
        }
    }
}
