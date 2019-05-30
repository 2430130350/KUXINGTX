package com.xl.kuxingtx.inter;

import com.xl.kuxingtx.UserInfo;

public interface FMineMvp {
    public interface View{
        //登录功能
        public void loginSucess();
        //注册功能
        public void registerSuccess();
        //重置密码功能
        public void resetPasswordSuccess();
    }
    public interface Presenter{
        //登录功能
        public void login(String userName, String password);
        public void loginSucess();
        //注册功能
        public void register(String userName, String password);
        public void registerSuccess();
        //重置密码功能
        public void resetPassword(String ouserName,String opassword,String nuserName,String npassword);
        public void resetPasswordSuccess();
    }
    public interface Model{
        //登录功能
        public void loginPost(UserInfo userInfo);
        //注册功能
        public void registerPost(String userName, String password);
        //重置密码功能
        public void resetPasswordPost(String ouserName, String opassword, String nuserName, String npassword);
    }
}
