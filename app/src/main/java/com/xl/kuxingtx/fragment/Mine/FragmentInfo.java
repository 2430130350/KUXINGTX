package com.xl.kuxingtx.fragment.Mine;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;
import com.xl.kuxingtx.R;
import com.xuexiang.xui.widget.textview.MarqueeTextView;
import com.xuexiang.xui.widget.textview.marqueen.DisplayEntity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@ContentView(R.layout.fragment_fragment_info)//加载的xml文件
public class FragmentInfo extends Fragment implements View.OnClickListener {
    @ViewInject(R.id.tv_marquee)
    private MarqueeTextView tv_marquee;
    private List<String> tvDatas = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_fragment_info, null);
        //ViewUtils.inject(getActivity());
        View view = x.view().inject(this, inflater, container);

        tvDatas.add("内测用户、");
        tvDatas.add("VIP");
        tv_marquee.startSimpleRoll(tvDatas);

        return view;
    }


    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null) {
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }


    @Override
    public void onClick(View v) {
/*        switch (v.getId()){
            case R.id.home_city://地址
                startActivity(new Intent(getActivity(),CityActivity.class));
                break;
            case R.id.home_map://地图
                break;
            case R.id.home_search://搜索
                break;
        }*/
    }
}


