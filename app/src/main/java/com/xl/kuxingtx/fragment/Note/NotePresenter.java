package com.xl.kuxingtx.fragment.Note;

import com.xl.kuxingtx.inter.FNoteMvp;

public class NotePresenter implements FNoteMvp.Presenter {
    private FNoteMvp.Model noteModel = new NoteModel(this);
    private FNoteMvp.View noteView;
    public NotePresenter(FNoteMvp.View noteView){
        this.noteView = noteView;
    }

    @Override
    public void delNoteBean(int position) {
        noteModel.delNoteBeanToFile(position);
    }

    @Override
    public void delNoteBeanSuccess() {
        noteView.delNoteBeanSuccess();
    }
}
