package com.xl.kuxingtx.activity.myInfo;

import com.xl.kuxingtx.inter.MyInfoMvp;

public class MyInfoPresenter implements MyInfoMvp.Presenter {
    private MyInfoMvp.Model myInfoModel = new MyInfoModel(this);
    private MyInfoMvp.View myInfoView;
    public MyInfoPresenter(MyInfoMvp.View myInfoView){
        this.myInfoView = myInfoView;
    }

}
