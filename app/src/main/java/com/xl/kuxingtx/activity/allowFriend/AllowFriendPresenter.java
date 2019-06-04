package com.xl.kuxingtx.activity.allowFriend;

import com.xl.kuxingtx.inter.AllowFriendMvp;

public class AllowFriendPresenter implements AllowFriendMvp.Presenter {
    private AllowFriendMvp.Model allowFriendModel = new AllowFriendModel(this);
    private AllowFriendMvp.View allowFriendView;
    public AllowFriendPresenter(AllowFriendMvp.View allowFriendView){
        this.allowFriendView =allowFriendView;
    }
}
