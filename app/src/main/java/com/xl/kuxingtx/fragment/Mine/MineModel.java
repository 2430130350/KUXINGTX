package com.xl.kuxingtx.fragment.Mine;

import com.xl.kuxingtx.inter.FMineMvp;

public class MineModel implements FMineMvp.Model {
    private FMineMvp.Presenter minePresenter;
    public MineModel(FMineMvp.Presenter minePresenter){
        this.minePresenter = minePresenter;
    }
}
