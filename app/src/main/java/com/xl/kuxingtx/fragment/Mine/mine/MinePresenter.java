package com.xl.kuxingtx.fragment.Mine.mine;

import com.xl.kuxingtx.UserInfo;
import com.xl.kuxingtx.inter.FMineMvp;

public class MinePresenter implements FMineMvp.Presenter {
    private FMineMvp.Model mineModel = new MineModel(this);
    private FMineMvp.View mineView;
    public MinePresenter(FMineMvp.View mineView){
        this.mineView = mineView;
    }

    public void login(String userName, String password){
        UserInfo userInfo = UserInfo.getUserInfo();
        userInfo.setUserName(userName);
        userInfo.setPassword(password);
        this.mineModel.loginPost(userInfo);
    }

    @Override
    public void loginSucess() {
        mineView.loginSucess();
    }

    @Override
    public void loginFailed() {
        mineView.loginFailed();
    }

    public void register(String userName, String password){
        this.mineModel.registerPost(userName,password);
    }

    @Override
    public void registerSuccess() {
        mineView.registerSuccess();
    }

    @Override
    public void registerFailed() {
        mineView.registerFailed();
    }

    public void resetPassword(String ouserName, String opassword, String nuserName, String npassword){
        this.mineModel.resetPasswordPost(ouserName,opassword,nuserName,npassword);
    }

    @Override
    public void resetPasswordSuccess() {
        mineView.resetPasswordSuccess();
    }
}
