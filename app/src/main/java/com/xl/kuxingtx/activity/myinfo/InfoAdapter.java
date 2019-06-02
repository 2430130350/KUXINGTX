package com.xl.kuxingtx.activity.myinfo;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xl.kuxingtx.R;


import java.util.List;


public class InfoAdapter extends BaseQuickAdapter<InfoBean, BaseViewHolder> {
    public InfoAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InfoBean item) {
//        helper.setText(R.id.friend_name, item.getUserName());
        helper.setText(R.id.info_key, item.getKey());
        helper.setText(R.id.info_value, item.getValue());
    }
}