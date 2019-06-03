package com.xl.kuxingtx.activity.myinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.xl.kuxingtx.R;
import com.xl.kuxingtx.inter.MyInfoMvp;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_my_info)
public class MyInfoActivity extends AppCompatActivity implements View.OnClickListener, MyInfoMvp.View {
    private MyInfoMvp.Presenter myInfoPresent = new MyInfoPresenter(this);
    @ViewInject(R.id.trends_recycler)
    private RecyclerView info_recycler;
    @ViewInject(R.id.titleBar)
    private TitleBar titleBar;
    private InfoAdapter infoAdapter;
    private List<InfoBean> infoDatas = new ArrayList<InfoBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_my_info);
        x.view().inject(this);

        InfoBean infoBean;
        for (int i = 0; i < 15; i++) {
            infoBean = new InfoBean();
            infoBean.setKey("昵称");
            infoBean.setValue("汐离");
            infoDatas.add(infoBean);
        }


        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        info_recycler.setLayoutManager(layoutManager);

        //创建适配器
        infoAdapter = new InfoAdapter(R.layout.info_item, infoDatas);

        //给RecyclerView设置适配器
        info_recycler.setAdapter(infoAdapter);
        initListener();
    }

    private void initListener(){
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyInfoActivity.this.finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titleBar:
                break;
        }
    }
}
