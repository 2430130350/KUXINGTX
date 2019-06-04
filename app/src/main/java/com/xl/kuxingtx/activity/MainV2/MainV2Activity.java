package com.xl.kuxingtx.activity.MainV2;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.xl.kuxingtx.R;
import com.xl.kuxingtx.UserInfo;
import com.xl.kuxingtx.fragment.Around.FragmentAround;
import com.xl.kuxingtx.fragment.Index.FragmentIndex;
import com.xl.kuxingtx.fragment.Mine.info.FragmentInfo;
import com.xl.kuxingtx.fragment.Mine.mine.FragmentMine;
import com.xl.kuxingtx.fragment.Note.FragmentNote;
import com.xl.kuxingtx.inter.MainV2Mvp;
import com.xl.kuxingtx.utils.CodeUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

import per.goweii.anylayer.AnyLayer;

@ContentView(R.layout.activity_main_v2)
public class MainV2Activity extends AppCompatActivity implements MainV2Mvp.View{
    @ViewInject(R.id.bottom_bar)
    private RadioGroup bottom_bar;
    @ViewInject(R.id.layout_content)
    private FrameLayout layout_content;
    @ViewInject(R.id.radio0)
    private RadioButton indexBtn;
    @ViewInject(R.id.radio1)
    private RadioButton noteBtn;
    @ViewInject(R.id.radio2)
    private RadioButton arroundBtn;
    @ViewInject(R.id.radio3)
    private RadioButton mineBtn;

    private FragmentStatePagerAdapter fragmentStatePagerAdapter;
    private MainV2Mvp.Presenter mainPresenter = new MainV2Presenter(this);
    private boolean isExit = false;


    @SuppressLint("HandlerLeak")
    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 403:
                    MainV2Activity.this.isExit = false;
                    break;
                case CodeUtils.IS_LOGIN:
                    Fragment fragment= (Fragment) fragmentStatePagerAdapter.instantiateItem(layout_content,4);
                    fragmentStatePagerAdapter.setPrimaryItem(layout_content,0,fragment);
                    fragmentStatePagerAdapter.finishUpdate(layout_content);
                    fragmentStatePagerAdapter.notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //取消状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //setContentView(R.layout.activity_main_v2);
        x.view().inject(this);
        //bottom_bar = (RadioGroup)findViewById(R.id.bottom_bar);
        //layout_content = (FrameLayout)findViewById(R.id.layout_content);

        bottom_bar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if((checkedId == R.id.radio1 || checkedId == R.id.radio2) && !UserInfo.getUserInfo().isLogined()){
                    MainV2Activity.this.needLogin();
                    return;
                }

                int index=0;
                indexBtn.setTextColor(Color.rgb(162, 162, 162));
                noteBtn.setTextColor(Color.rgb(162, 162, 162));
                arroundBtn.setTextColor(Color.rgb(162, 162, 162));
                mineBtn.setTextColor(Color.rgb(162, 162, 162));
                switch (checkedId){
                    case R.id.radio0:
                        index=0;
                        indexBtn.setTextColor(Color.rgb(0,0,0));
                        break;
                    case R.id.radio1:
                        index=1;
                        noteBtn.setTextColor(Color.rgb(0,0,0));
                        break;
                    case R.id.radio2:
                        index=2;
                        arroundBtn.setTextColor(Color.rgb(0,0,0));
                        break;
                    case R.id.radio3:
                        if(UserInfo.getUserInfo().isLogined()){
                            index = 4;
                        }
                        else {
                            index = 3;
                        }
                        mineBtn.setTextColor(Color.rgb(0,0,0));
                        break;
                }
                Fragment fragment= (Fragment) fragmentStatePagerAdapter.instantiateItem(layout_content,index);
                fragmentStatePagerAdapter.setPrimaryItem(layout_content,0,fragment);
                fragmentStatePagerAdapter.finishUpdate(layout_content);
            }
        });
        fragmentStatePagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {


            @Override
            public int getItemPosition(Object object) {
                // TODO Auto-generated method stub
                return PagerAdapter.POSITION_NONE;
            }
                @Override
            public int getCount() {
                return 5;
            }

            @Override
            public Fragment getItem(int position) {
                Fragment fragment=null;
                switch (position){
                    case 0://首页
                        fragment = new FragmentIndex();
                        break;
                    case 1://随笔
                        fragment = new FragmentNote();
                        break;
                    case 2://周围
                        fragment = new FragmentAround();
                        break;
                    case 3://我的
                        if(UserInfo.getUserInfo().isLogined()){
                            fragment = new FragmentInfo();
                        }
                        else{
                            fragment = new FragmentMine();
                        }
                        break;
                    case 4:
                        fragment = new FragmentInfo();
                        break;
                    default:
                        fragment = new FragmentIndex();
                        break;
                }
                return fragment;
            }
        };
        Fragment fragment= (Fragment) fragmentStatePagerAdapter.instantiateItem(layout_content, 0);
        fragmentStatePagerAdapter.setPrimaryItem(layout_content,0,fragment);
        fragmentStatePagerAdapter.finishUpdate(layout_content);
        indexBtn.setTextColor(Color.rgb(0, 0, 0));
    }

    @Override
    public void onBackPressed() {

        if(this.isExit)
            super.onBackPressed();
        else{
            isExit = true;
            Toast.makeText(this, "再按一次返回键将退出程序、", Toast.LENGTH_SHORT).show();
            this.mHandler.sendEmptyMessageDelayed(403, 2000);
        }

    }

    @Override
    public void onResume() {

        super.onResume();
    }

    private void needLogin(){
        AnyLayer.with(this)
                .contentView(R.layout.needlogin_dialog)
                .backgroundBlurPercent(0.05f)
                .backgroundColorInt(getResources().getColor(R.color.dialog_blur_bg))
                .onClickToDismiss(R.id.fl_dialog_yes, R.id.fl_dialog_no)
                .show();
    }


}
