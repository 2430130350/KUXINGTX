package com.xl.kuxingtx.fragment.Around;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xl.kuxingtx.R;
import com.zzhoujay.richtext.RichText;

import java.util.List;

public class TrendsAdapter extends BaseQuickAdapter<TrendsBean, BaseViewHolder> {
    public TrendsAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TrendsBean item) {
        TextView trends_content = (TextView) helper.getView(R.id.trends_content);
        RichText.from(item.getPartialContent()).singleLoad(false).into(trends_content);

        helper.setText(R.id.username, "UID：" + item.getUid());
        helper.setText(R.id.time, item.getDateString());

        helper.addOnClickListener(R.id.trends_content);
    }
}