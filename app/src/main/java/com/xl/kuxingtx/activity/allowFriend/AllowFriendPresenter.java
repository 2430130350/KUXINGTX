package com.xl.kuxingtx.activity.allowFriend;

import com.xl.kuxingtx.Friend;
import com.xl.kuxingtx.inter.AllowFriendMvp;

import java.util.ArrayList;

public class AllowFriendPresenter implements AllowFriendMvp.Presenter {
    private AllowFriendMvp.Model allowFriendModel = new AllowFriendModel(this);
    private AllowFriendMvp.View allowFriendView;
    public AllowFriendPresenter(AllowFriendMvp.View allowFriendView){
        this.allowFriendView =allowFriendView;
    }

    @Override
    public void relation_confirm(long uid, long fid, String nick_name, String description) {
        allowFriendModel.relation_confirmPost(uid, fid, nick_name, description);
    }

    @Override
    public void relation_confirmSuccess() {
        allowFriendView.relation_confirmSuccess();
    }

    @Override
    public void relation_del(long uid, long fid) {
        allowFriendModel.relation_delPost(uid, fid);
    }

    @Override
    public void relation_delSuccess() {
        allowFriendView.relation_delSuccess();
    }

    @Override
    public void relation_my_all_qur(long uid) {
        allowFriendModel.relation_my_all_qurPost(uid);
    }

    @Override
    public void relation_my_all_qurSuccess() {
        allowFriendView.relation_my_all_qurSuccess();
    }

    @Override
    public void relation_my_one_qur(long uid, long fid) {
        allowFriendModel.relation_my_one_qurPost(uid, fid);
    }

    @Override
    public void relation_my_one_qurSuccess(ArrayList<Friend> Friends) {
        allowFriendView.relation_my_one_qurSuccess(Friends);
    }
}
