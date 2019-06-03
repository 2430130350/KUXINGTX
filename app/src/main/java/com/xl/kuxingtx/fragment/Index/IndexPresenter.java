package com.xl.kuxingtx.fragment.Index;

import com.xl.kuxingtx.inter.IndexMvp;

public class IndexPresenter implements IndexMvp.Presenter {
    private IndexMvp.Model indexModel = new IndexModel(this);
    private IndexMvp.View indexView;
    public IndexPresenter(IndexMvp.View indexView){
        this.indexView = indexView;
    }
}
