package com.xl.kuxingtx.activity.readNote;

import com.xl.kuxingtx.UserInfo;
import com.xl.kuxingtx.fragment.Around.TrendsBean;
import com.xl.kuxingtx.inter.ReadNoteMvp;

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
}
