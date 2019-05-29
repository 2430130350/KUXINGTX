package com.xl.kuxingtx;

public class UserInfo {
    private String userName = "";
    private String password = "";
    private boolean isLogined = false;


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

    public boolean isLogined() {
        return isLogined;
    }

    public void setLogined(boolean logined) {
        isLogined = logined;
    }

}
