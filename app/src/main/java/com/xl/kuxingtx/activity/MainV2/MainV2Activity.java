package com.xl.kuxingtx.activity.MainV2;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.xl.kuxingtx.R;
import com.xl.kuxingtx.UserInfo;
import com.xl.kuxingtx.fragment.Around.FragmentAround;
import com.xl.kuxingtx.fragment.Index.FragmentIndex;
import com.xl.kuxingtx.fragment.Mine.info.FragmentInfo;
import com.xl.kuxingtx.fragment.Mine.mine.FragmentMine;
import com.xl.kuxingtx.fragment.Note.FragmentNote;
import com.xl.kuxingtx.inter.MainV2Mvp;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

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
            }
            super.handleMessage(msg);
        }
    };

    PoiSearch.Query query;
    PoiSearch poiSearch;

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
                        index=3;
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
            public int getCount() {
                return 4;
            }

            @Override
            public Fragment getItem(int position) {
                Fragment fragment=null;
                switch (position){
                    case 0://首页
                        fragment=new FragmentIndex();
                        break;
                    case 1://随笔
                        fragment=new FragmentNote();
                        break;
                    case 2://周围
                        fragment=new FragmentAround();
                        break;
                    case 3://我的
                        if(UserInfo.getUserInfo().isLogined()){
                            fragment = new FragmentInfo();
                        }
                        else{
                            fragment=new FragmentMine();
                        }
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
        indexBtn.setTextColor(Color.rgb(0, 0, 0));
        query = new PoiSearch.Query("武汉", "110000", "武汉");
        //keyWord表示搜索字符串，
        //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
        //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(1);//设置查询页码
        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
            @Override
            public void onPoiSearched(PoiResult poiResult, int i) {
                ArrayList<PoiItem> poiItemArrayList=poiResult.getPois();
                String s=poiItemArrayList.get(0).getTitle();
            }

            @Override
            public void onPoiItemSearched(PoiItem poiItem, int i) {

            }
        });
        poiSearch.searchPOIAsyn();
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
}
