package com.xl.kuxingtx.inter;

import com.xl.kuxingtx.fragment.Around.TrendsBean;
import com.xl.kuxingtx.fragment.Note.NoteBean;

import java.util.List;

public interface ReadNoteMvp {
    public interface View{
        public void uploadTrendsSuccess();
        public void saveNoteSuccess();
    }
    public interface Presenter{
        public void uploadTrends(String content);
        public void saveNote(String content, int position);
        public void saveNote(String content);

        public void uploadTrendsSuccess();
        public void saveNoteSuccess();
    }
    public interface Model{
        public void uploadTrendsPost(TrendsBean trendsBean);
        public void saveNote();

    }
}
