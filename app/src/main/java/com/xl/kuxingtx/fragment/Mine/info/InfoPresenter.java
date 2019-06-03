package com.xl.kuxingtx.fragment.Mine.info;

import com.xl.kuxingtx.friend;
import com.xl.kuxingtx.inter.FInfoMvp;

import java.util.ArrayList;

public class InfoPresenter implements FInfoMvp.Presenter {
    private FInfoMvp.View infoView;
    private FInfoMvp.Model infoModel = new InfoModel(this);
    public InfoPresenter(FInfoMvp.View infoView){
        this.infoView = infoView;
    }

    @Override
    public void query_info(String userName, String password) {
        infoModel.query_infoPost(userName, password);
    }

    @Override
    public void query_infoSuccess() {
        infoView.query_infoSuccess();
    }

    @Override
    public void modify_info(String ouserName, String opassword, String nuserName, String npassword) {
        infoModel.modify_infoPost(ouserName,opassword,nuserName,npassword);
    }

    @Override
    public void modify_infoSuccess() {
        infoView.modify_infoSuccess();
    }

    @Override
    public void relation_add(long uid, long fid, String nick_name, String description) {
        infoModel.relation_addPost(uid, fid, nick_name, description);
    }

    @Override
    public void relation_addSuccess() {
        infoView.relation_addSuccess();
    }

    @Override
    public void relation_confirm(long uid, long fid, String nick_name, String description) {
        infoModel.relation_confirmPost(uid, fid, nick_name, description);
    }

    @Override
    public void relation_confirmSuccess() {
        infoView.relation_confirmSuccess();
    }

    @Override
    public void relation_del(long uid, long fid) {
        infoModel.relation_delPost(uid, fid);
    }

    @Override
    public void relation_delSuccess() {
        infoView.relation_delSuccess();
    }

    @Override
    public void relation_my_all_qur(long uid) {
        infoModel.relation_my_all_qurPost(uid);
    }

    @Override
    public void relation_my_all_qurSuccess() {
        infoView.relation_my_all_qurSuccess();
    }

    @Override
    public void relation_my_one_qur(long uid, long fid) {
        infoModel.relation_my_one_qurPost(uid, fid);
    }

    @Override
    public void relation_my_one_qurSuccess(ArrayList<friend> friends) {
        infoView.relation_my_one_qurSuccess(friends);
    }
}
