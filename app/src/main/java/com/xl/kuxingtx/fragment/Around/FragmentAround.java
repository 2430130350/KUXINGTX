package com.xl.kuxingtx.fragment.Around;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.xl.kuxingtx.R;
import com.xl.kuxingtx.inter.FAroundMvp;
import com.zzhoujay.richtext.RichText;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.fragment_fragment_around)//加载的xml文件
public class FragmentAround extends Fragment implements View.OnClickListener, FAroundMvp.View{
    @ViewInject(R.id.trends_recycler)
    private RecyclerView trends_recycler;

    private TrendsAdapter trendsAdapter;
    private List<TrendsBean> trendsDatas = new ArrayList<TrendsBean>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = x.view().inject(this, inflater, container);
        //必须先调用RichText.initCacheDir()方法、不然报错、
        RichText.initCacheDir(this.getActivity());

        TrendsBean trendsBean;
        for (int i = 0; i < 15; i++) {
            trendsBean = new TrendsBean();
            trendsBean.setContent("<h3>测试富文本、</h3><img src=\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559473454823&di=aeb63602b58f630754528c005b78f133&imgtype=0&src=http%3A%2F%2Fmmbiz.qpic.cn%2Fmmbiz_png%2F7CrlBDLFl4YLrIVhtEZZu2mVefODWXMibq4bUrZV22aGZy6aU3OxQWX6UvicMX2CShG6WicdIK9BEeMj4gibtfyc2w%2F640%3Fwx_fmt%3Dpng\" />");
            trendsDatas.add(trendsBean);
        }
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        trends_recycler.setLayoutManager(layoutManager);

        //创建适配器
        trendsAdapter = new TrendsAdapter(R.layout.trends_item, trendsDatas);

        //给RecyclerView设置适配器
        trends_recycler.setAdapter(trendsAdapter);
        return view;
    }


    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if(this.getView()!=null){
            this.getView().setVisibility(menuVisible?View.VISIBLE:View.GONE);
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