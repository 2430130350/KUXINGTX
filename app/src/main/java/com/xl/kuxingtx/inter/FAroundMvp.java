package com.xl.kuxingtx.inter;

import com.xl.kuxingtx.UserInfo;
import com.xl.kuxingtx.fragment.Around.TrendsBean;

public interface FAroundMvp {
    public interface View{}
    public interface Presenter{
        public void trends_my_add(String userName, String password);                                            //添加动态信息
        public void trends_my_one_quer(String userName, String password);                                       //查询返回一个动态
        public void trends_my_all_quer(String ouserName,String opassword,String nuserName,String npassword);    //返回所有动态
    }
    public interface Model{
        public void loginPost(UserInfo userInfo);
        public void registerPost(String userName, String password);
        public void resetPasswordPost(String ouserName, String opassword, String nuserName, String npassword);


    }
}
