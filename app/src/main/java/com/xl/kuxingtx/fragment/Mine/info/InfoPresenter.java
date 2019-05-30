package com.xl.kuxingtx.fragment.Mine.info;

import com.xl.kuxingtx.inter.FInfoMvp;

public class InfoPresenter implements FInfoMvp.Presenter {
    private FInfoMvp.View infoView;
    private FInfoMvp.Model infoModel = new InfoModel(this);
    public InfoPresenter(FInfoMvp.View infoView){
        this.infoView = infoView;
    }
}
