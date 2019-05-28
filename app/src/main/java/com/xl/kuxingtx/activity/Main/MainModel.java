package com.xl.kuxingtx.activity.Main;

import com.xl.kuxingtx.inter.MainMvp;

public class MainModel implements MainMvp.Model {
    private MainMvp.Presenter mainPresenter = null;
    public MainModel (MainMvp.Presenter mainPresenter){
        this.mainPresenter = mainPresenter;
    }
}
