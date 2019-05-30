package com.xl.kuxingtx.fragment.Mine.info;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xl.kuxingtx.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

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




/*
public class FriendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private String[] mDatas;


    public FriendAdapter(Context context, String[] datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = mInflater.inflate(R.layout.friend_item, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ItemViewHolder){

            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            String str = mDatas[position];
            itemViewHolder.friend_name.setText(str);
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.length;
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @ViewInject(R.id.friend_name)
        public TextView friend_name;
        public ItemViewHolder(View itemView) {
            super(itemView);
            x.view().inject(this, itemView);//注解绑定
            initListener(itemView);
        }

        private void initListener(View itemView) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "poistion "+ getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}*/
