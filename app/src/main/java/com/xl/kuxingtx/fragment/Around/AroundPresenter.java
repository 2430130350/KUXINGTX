package com.xl.kuxingtx.fragment.Around;

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
}
