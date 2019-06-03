package com.xl.kuxingtx.activity.readNote;

import com.xl.kuxingtx.inter.ReadNoteMvp;

public class ReadNotePresenter implements ReadNoteMvp.Presenter {
    private ReadNoteMvp.Model readNoteModel = new ReadNoteModel(this);
    private ReadNoteMvp.View readNoteView;
    public ReadNotePresenter(ReadNoteMvp.View readNoteView){
        this.readNoteView = readNoteView;
    }
}
