package com.xl.kuxingtx;

public class UserInfo {
    private String userName = "";
    private String password = "";
    private int id = -1;
    private int records = -1;
    private int treature = -1;
    private boolean isLogined = true;           //为了测试此处进行了修改


    private static UserInfo userInfo = new UserInfo();

    private UserInfo(){}

    public static UserInfo getUserInfo(){
        return userInfo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id){ this.id = id; }

    public int getId(){ return id; }

    public void setRecords(int records){ this.records = records; }

    public int getRecords(){ return records; }

    public void setTreasure(int treature){ this.treature = treature; }

    public int getTreasure(){ return treature; }

    public boolean isLogined() {
        return isLogined;
    }

    public void setLogined(boolean logined) {
        isLogined = logined;
    }

}
