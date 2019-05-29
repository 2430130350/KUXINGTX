package com.xl.kuxingtx.inter;

import com.xl.kuxingtx.UserInfo;

public interface FMineMvp {
    public interface View{}
    public interface Presenter{
        public void login(String userName, String password);
    }
    public interface Model{
        public void loginPost(UserInfo userInfo);
    }
}
