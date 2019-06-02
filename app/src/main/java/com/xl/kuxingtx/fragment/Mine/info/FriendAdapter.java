package com.xl.kuxingtx.fragment.Mine.info;



import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xl.kuxingtx.R;


import java.util.List;


public class FriendAdapter extends BaseQuickAdapter<FriendBean, BaseViewHolder> {
    public FriendAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendBean item) {
        helper.setText(R.id.friend_name, item.getUserName());
    }
}