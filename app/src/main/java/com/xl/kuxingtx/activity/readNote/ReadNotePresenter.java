package com.xl.kuxingtx.activity.readNote;

import com.xl.kuxingtx.UserInfo;
import com.xl.kuxingtx.fragment.Around.TrendsBean;
import com.xl.kuxingtx.fragment.Note.NoteBean;
import com.xl.kuxingtx.inter.ReadNoteMvp;
import com.xl.kuxingtx.utils.CodeUtils;

import java.util.Date;

public class ReadNotePresenter implements ReadNoteMvp.Presenter {
    private ReadNoteMvp.Model readNoteModel = new ReadNoteModel(this);
    private ReadNoteMvp.View readNoteView;
    public ReadNotePresenter(ReadNoteMvp.View readNoteView){
        this.readNoteView = readNoteView;
    }

    @Override
    public void uploadTrends(String content) {
        UserInfo userInfo = UserInfo.getUserInfo();
        String username = userInfo.getUserName();
        long uid = userInfo.getId();
        Date mTime = new Date();
        TrendsBean trendsBean = new TrendsBean(username, uid, mTime, content);
        readNoteModel.uploadTrendsPost(trendsBean);
    }

    @Override
    public void saveNote(String content) {
        //没有position、表示是新增Note、
        UserInfo userInfo = UserInfo.getUserInfo();
        String username = userInfo.getUserName();
        long uid = userInfo.getId();
        Date mTime = new Date();
        NoteBean noteBean = new NoteBean(username, uid, mTime, content);
        userInfo.addNoteBean(noteBean);

        readNoteModel.saveNote();
    }

    @Override
    public void saveNote(String content, int position) {
        //有position、表示是更新Note、
        UserInfo userInfo = UserInfo.getUserInfo();
        userInfo.updateNoteBean(content, position);

        readNoteModel.saveNote();
    }

    @Override
    public void uploadTrendsSuccess() {
        readNoteView.uploadTrendsSuccess();
    }

    @Override
    public void saveNoteSuccess() {
        readNoteView.saveNoteSuccess();
    }
}
