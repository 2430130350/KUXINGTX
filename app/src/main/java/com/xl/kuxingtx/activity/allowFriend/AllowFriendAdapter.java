package com.xl.kuxingtx.activity.allowFriend;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xl.kuxingtx.Friend;
import com.xl.kuxingtx.R;
import com.xl.kuxingtx.fragment.Mine.info.FriendBean;

import java.util.List;

public class AllowFriendAdapter extends BaseQuickAdapter<Friend, BaseViewHolder> {
    public AllowFriendAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Friend item) {
        //helper.setText(R.id.friend_name, item.getUserName());
    }
}
