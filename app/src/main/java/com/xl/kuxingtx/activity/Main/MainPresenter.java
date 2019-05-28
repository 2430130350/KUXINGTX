package com.xl.kuxingtx.activity.Main;

import com.xl.kuxingtx.inter.MainMvp;

public class MainPresenter implements MainMvp.Presenter {
    private MainMvp.Model mainModel = new MainModel(this);
    private MainMvp.View mainView = null;
    public MainPresenter(MainMvp.View mainView){
        this.mainView = mainView;
    }
}
