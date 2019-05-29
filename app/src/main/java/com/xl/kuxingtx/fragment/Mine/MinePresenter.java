package com.xl.kuxingtx.fragment.Mine;




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

}
