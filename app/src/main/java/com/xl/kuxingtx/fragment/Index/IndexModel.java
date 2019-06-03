package com.xl.kuxingtx.fragment.Index;

import com.xl.kuxingtx.inter.IndexMvp;

public class IndexModel implements IndexMvp.Model {
    private IndexMvp.Presenter indexPresenter;
    public IndexModel(IndexMvp.Presenter indexPresenter){
        this.indexPresenter = indexPresenter;
    }
}
