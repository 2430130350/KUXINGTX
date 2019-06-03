package com.xl.kuxingtx.activity.readNote;

import com.xl.kuxingtx.inter.ReadNoteMvp;

public class ReadNoteModel implements ReadNoteMvp.Model {
    private ReadNoteMvp.Presenter readNotePresenter;
    public ReadNoteModel(ReadNoteMvp.Presenter readNotePresenter){
        this.readNotePresenter = readNotePresenter;
    }
}
