package com.xl.kuxingtx.activity.myInfo;

import com.xl.kuxingtx.inter.MyInfoMvp;

public class MyInfoModel implements MyInfoMvp.Model {
    private MyInfoMvp.Presenter myInfoPresenter;
    public MyInfoModel(MyInfoMvp.Presenter myInfoPresenter){
        this.myInfoPresenter = myInfoPresenter;
    }
}
