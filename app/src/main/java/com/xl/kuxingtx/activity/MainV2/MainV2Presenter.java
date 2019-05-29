package com.xl.kuxingtx.activity.MainV2;

import com.xl.kuxingtx.inter.MainV2Mvp;

public class MainV2Presenter implements MainV2Mvp.Presenter{
    private MainV2Mvp.Model mainModel = new MainV2Model(this);
    private MainV2Mvp.View mainView = null;
    public MainV2Presenter(MainV2Mvp.View mainView){
        this.mainView = mainView;
    }
}
