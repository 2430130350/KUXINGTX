package com.xl.kuxingtx.fragment.Note;

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
import com.xl.kuxingtx.fragment.Around.TrendsAdapter;
import com.xl.kuxingtx.fragment.Around.TrendsBean;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.fragment_fragment_note)//加载的xml文件
public class FragmentNote extends Fragment implements View.OnClickListener{
    @ViewInject(R.id.note_recycler)
    private RecyclerView note_recycler;

    private NoteAdapter noteAdapter;
    private List<NoteBean> noteBeans = new ArrayList<NoteBean>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = x.view().inject(this, inflater, container);



        NoteBean noteBean;
        for (int i = 0; i < 15; i++) {
            noteBean = new NoteBean();
            noteBean.setContent(
                    "<h3>测试富文本、</h3><img src=\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559473454823&di=aeb63602b58f630754528c005b78f133&imgtype=0&src=http%3A%2F%2Fmmbiz.qpic.cn%2Fmmbiz_png%2F7CrlBDLFl4YLrIVhtEZZu2mVefODWXMibq4bUrZV22aGZy6aU3OxQWX6UvicMX2CShG6WicdIK9BEeMj4gibtfyc2w%2F640%3Fwx_fmt%3Dpng\" />" +
                            "<img src=\"http://k.zol-img.com.cn/sjbbs/7692/a7691515_s.jpg\"/>中国官方星期六宣布，国家有关部门决定对美国联邦快递涉嫌损害我国用户合法权益的问题立案调查。据华为日前向媒体披露，联邦快递最近将该公司从日本发往中国的两个邮件送到了美国，另外两个从越南发往香港和新加坡的邮件被在中途滞留，终极地也被联邦快递改为了美国。\n" +
                            "\n" +
                            "　　尽管联邦快递随后声称这些都是偶然的错误，但是舆论的大量分析质疑联邦快递这样做是受到了美国政府的指使。因为上述四件包裹在几天时间里先后被错误地改为发往美国，实在太蹊跷了。这样的出错率与联邦快递通常的服务水平大相径庭。\n" +
                            "\n" +
                            "　　更重要的是，美国政府正动用国家力量对华为开展致力于让其关门的全面打压，而四件包裹错投的终极地都是美国，这加重了人们对联邦快递这样做是受到美国政府操纵的怀疑。");
            noteBeans.add(noteBean);
        }
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        note_recycler.setLayoutManager(layoutManager);

        //创建适配器
        noteAdapter = new NoteAdapter(R.layout.note_item, noteBeans);

        //给RecyclerView设置适配器
        note_recycler.setAdapter(noteAdapter);
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