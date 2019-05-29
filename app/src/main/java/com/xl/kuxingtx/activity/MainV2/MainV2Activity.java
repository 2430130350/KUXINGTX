package com.xl.kuxingtx.activity.MainV2;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.xl.kuxingtx.R;
import com.lidroid.xutils.ViewUtils;
import com.xl.kuxingtx.inter.MainV2Mvp;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class MainV2Activity extends AppCompatActivity implements MainV2Mvp.View{
    @ViewInject(R.id.bottom_bar)
    private RadioGroup bottom_bar;
    @ViewInject(R.id.layout_content)
    private FrameLayout layout_content;
    private FragmentStatePagerAdapter fragmentStatePagerAdapter;
    private MainV2Mvp.Presenter mainPresenter = new MainV2Presenter(this);
    private boolean isExit = false;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 403:
                    MainV2Activity.this.isExit = false;
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

        setContentView(R.layout.activity_main_v2);
        x.view().inject(this);
        //bottom_bar = (RadioGroup)findViewById(R.id.bottom_bar);
        //layout_content = (FrameLayout)findViewById(R.id.layout_content);

        bottom_bar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index=0;
                switch (checkedId){
                    case R.id.radio0:
                        index=0;
                        break;
                    case R.id.radio1:
                        index=1;
                        break;
                    case R.id.radio2:
                        index=2;
                        break;
                    case R.id.radio3:
                        index=3;
                        break;
                }
                Fragment fragment= (Fragment) fragmentStatePagerAdapter.instantiateItem(layout_content,index);
                fragmentStatePagerAdapter.setPrimaryItem(layout_content,0,fragment);
                fragmentStatePagerAdapter.finishUpdate(layout_content);
            }
        });
        fragmentStatePagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public Fragment getItem(int position) {
                Fragment fragment=null;
                switch (position){
                    case 0://团购
                        fragment=new FragmentIndex();
                        break;
                    case 1://附近
                        fragment=new FragmentNote();
                        break;
                    case 2://我的
                        fragment=new FragmentAround();
                        break;
                    case 3://更多
                        fragment=new FragmentMine();
                        break;
                    default:
                        fragment=new FragmentIndex();
                        break;
                }
                return fragment;
            }
        };
        Fragment fragment= (Fragment) fragmentStatePagerAdapter.instantiateItem(layout_content, 0);
        fragmentStatePagerAdapter.setPrimaryItem(layout_content,0,fragment);
        fragmentStatePagerAdapter.finishUpdate(layout_content);
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
}