package com.xl.kuxingtx.activity.allowFriend;

import com.xl.kuxingtx.inter.AllowFriendMvp;

public class AllowFriendModel implements AllowFriendMvp.Model {
    private AllowFriendMvp.Presenter allowFriendPresenter;
    public AllowFriendModel(AllowFriendMvp.Presenter allowFriendPresenter){
        this.allowFriendPresenter = allowFriendPresenter;
    }
}
