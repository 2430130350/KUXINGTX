package com.xl.kuxingtx.fragment.Mine.info;

import com.xl.kuxingtx.inter.FInfoMvp;

public class InfoModel implements FInfoMvp.Model {
    private FInfoMvp.Presenter infoPresenter;
    public InfoModel(FInfoMvp.Presenter infoPresenter){
        this.infoPresenter = infoPresenter;
    }
}
