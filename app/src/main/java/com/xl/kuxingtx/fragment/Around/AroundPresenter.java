package com.xl.kuxingtx.fragment.Around;

import com.xl.kuxingtx.UserInfo;
import com.xl.kuxingtx.inter.FAroundMvp;

import java.util.List;

public class AroundPresenter implements FAroundMvp.Presenter {
    private FAroundMvp.Model arroundModel = new AroundModel(this);
    private FAroundMvp.View arroundView;

    public AroundPresenter(FAroundMvp.View arroundView){
        this.arroundView = arroundView;
    }

    @Override
    public void trends_my_add(String userName, String password) {

    }

    @Override
    public void trends_my_one_quer(String userName, String password) {

    }

    @Override
    public void trends_my_all_quer(String ouserName, String opassword, String nuserName, String npassword) {

    }

    @Override
    public void loadTrends() {
        arroundModel.loadTrendsPost();
    }

    @Override
    public void loadTrendsSuccess(List<TrendsBean> trendsBeans) {
        //时间排序、
        arroundView.loadTrendsSuccess(trendsBeans);
    }

    @Override
    public void delTrends(TrendsBean trendsBean) {
        long uid = trendsBean.getUid();
        String date = trendsBean.getDateString();

        //这里需要进行一次客户端判断、是否是自己的动态、
        if(uid == UserInfo.getUserInfo().getId())
            arroundModel.delTrendsPost(uid, date);
        else {
            //不是自己的日记、
            arroundView.delTrendsFailedNotMine();
        }
    }

    @Override
    public void delTrendsSuccess() {
        arroundView.delTrendsSuccess();
    }
}
