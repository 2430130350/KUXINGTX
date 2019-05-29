package com.xl.kuxingtx.inter;

import com.xl.kuxingtx.UserInfo;

public interface FMineMvp {
    public interface View{}
    public interface Presenter{
        public void login(String userName, String password);
        public void register(String userName, String password);
        public void resetPassword(String ouserName,String opassword,String nuserName,String npassword);
    }
    public interface Model{
        public void loginPost(UserInfo userInfo);
        public void registerPost(String userName, String password);
        public void resetPasswordPost(String ouserName, String opassword, String nuserName, String npassword);
    }
}
