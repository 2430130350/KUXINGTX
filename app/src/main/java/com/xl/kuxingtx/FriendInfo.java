package com.xl.kuxingtx;

import java.util.ArrayList;

public class FriendInfo {
    private static FriendInfo friendInfo = new FriendInfo();

    private FriendInfo(){}

    public static FriendInfo getFriendInfo(){
        return friendInfo;
    }

    private ArrayList<friend> friends=new ArrayList<friend>();

    private ArrayList<friend> alreadyFriends=new ArrayList<friend>();

    private ArrayList<friend> requestFriends=new ArrayList<friend>();

    public ArrayList<friend> getFriends() {
        return friends;
    }

    public ArrayList<friend> getAlreadyFriends(){
        return alreadyFriends;
    }

    public ArrayList<friend> getRequestFriends(){
        return requestFriends;
    }

    public void setFriends(ArrayList<friend> friends) {
        this.friends=friends;
    }

    public void sortFriends(){
        for(int i=0;i<friends.size();i++){
            friend tmpfriend=friends.get(i);
            if(friends.get(i).getMconfirm()==1 && friends.get(i).getFconfirm()==1){
                alreadyFriends.add(tmpfriend);
            }else if(friends.get(i).getMconfirm()==0 &&friends.get(i).getFconfirm()==1){
                requestFriends.add(tmpfriend);
            }
        }
    }
}
