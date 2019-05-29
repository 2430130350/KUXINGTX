package com.xl.kuxingtx.activity.MainV2;

import com.xl.kuxingtx.inter.MainV2Mvp;

public class MainV2Model implements MainV2Mvp.Model{
    private MainV2Mvp.Presenter mainPresenter = null;
    public MainV2Model (MainV2Mvp.Presenter mainPresenter){
        this.mainPresenter = mainPresenter;
    }
}
