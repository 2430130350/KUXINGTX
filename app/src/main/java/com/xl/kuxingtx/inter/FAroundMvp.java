package com.xl.kuxingtx.inter;

import com.xl.kuxingtx.UserInfo;
import com.xl.kuxingtx.fragment.Around.TrendsBean;

import java.util.Date;
import java.util.List;

public interface FAroundMvp {
    public interface View{
        public void loadTrendsSuccess(List<TrendsBean> trendsBeans);
        public void delTrendsSuccess();//需要重新调用刷新、
        public void delTrendsFailedNotMine();//不是我的动态、
    }
    public interface Presenter{
        public void trends_my_add(String userName, String password);                                            //添加动态信息
        public void trends_my_one_quer(String userName, String password);                                       //查询返回一个动态
        public void trends_my_all_quer(String ouserName,String opassword,String nuserName,String npassword);    //返回所有动态

        public void loadTrends();
        public void loadTrendsSuccess(List<TrendsBean> trendsBeans);
        public void delTrends(TrendsBean trendsBean);//只能删除自己的动态、这里由客户端进行判断是不对的、但服务器逻辑有问题、暂时由客户端判断、
        public void delTrendsSuccess();


    }
    public interface Model{
        public void loginPost(UserInfo userInfo);
        public void registerPost(String userName, String password);
        public void resetPasswordPost(String ouserName, String opassword, String nuserName, String npassword);

        public void loadTrendsPost();
        public void delTrendsPost(long uid, String date);

    }
}
