package com.xl.kuxingtx.activity.Guide;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.xl.kuxingtx.R;
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
        view1.setBackgroundColor(Color.rgb(0, 0, 0));
        view1.setBackgroundResource(R.drawable.pic1);
        View view2 = lf.inflate(R.layout.view_guide2, null);
        view2.setBackgroundColor(Color.rgb(0, 0, 0));
        view2.setBackgroundResource(R.drawable.pic2);
        View view3 = lf.inflate(R.layout.view_guide3, null);
        view3.setBackgroundColor(Color.rgb(0, 0, 0));
        view3.setBackgroundResource(R.drawable.pic3);
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);

        mViewPager.setAdapter(new ViewPagerAdatper(mViewList));
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            private boolean isPress = false;
            @Override
            public void onPageScrolled(int i, float v, int i1) {

                if(i == 2 && isPress){
                    Intent intent=new Intent(GuideActivity.this, MainV2Activity.class);
                    startActivity(intent);
                    GuideActivity.this.finish();
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    isPress = true;
                } else {//必须写else，不然的话，倒数第二页就开始自动跳转了
                    isPress = false;
                }
            }
        });

/*        view3.findViewById(R.id.goto_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GuideActivity.this, MainV2Activity.class);
                startActivity(intent);
                GuideActivity.this.finish();
            }
        });*/

//        skip();
    }

    @Override
    public void onBackPressed() {}

    public void skip(){
        Intent intent=new Intent(GuideActivity.this, MainV2Activity.class);
        startActivity(intent);
        GuideActivity.this.finish();
    }

}
