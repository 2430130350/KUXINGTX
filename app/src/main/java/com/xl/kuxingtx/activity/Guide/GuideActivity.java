package com.xl.kuxingtx.activity.Guide;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.xl.kuxingtx.R;
import com.xl.kuxingtx.activity.Main.MainActivity;
import com.xl.kuxingtx.activity.MainV2.MainV2Activity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {
    private ArrayList<View> mViewList;
    @ViewInject(R.id.in_viewpager)
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //取消状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        x.view().inject(this);
        /**
         * 引导页、
         * 1.计时器方式、计时完毕进入主界面、
         * 2.滑动四页后、通过点击按钮进入主界面、
         * */
        mViewList = new ArrayList<View>();
        LayoutInflater lf = getLayoutInflater().from(this);
        View view1 = lf.inflate(R.layout.view_guide1, null);
        View view2 = lf.inflate(R.layout.view_guide2, null);
        View view3 = lf.inflate(R.layout.view_guide3, null);
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);

        mViewPager.setAdapter(new ViewPagerAdatper(mViewList));

        view3.findViewById(R.id.goto_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GuideActivity.this, MainV2Activity.class);
                startActivity(intent);
                GuideActivity.this.finish();
            }
        });
    }

    @Override
    public void onBackPressed() {}
}