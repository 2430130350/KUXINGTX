package com.xl.kuxingtx.activity.readTrends;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.xl.kuxingtx.R;
import com.xl.kuxingtx.activity.myInfo.MyInfoActivity;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.zzhoujay.richtext.RichText;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;

@ContentView(R.layout.activity_read_trends)
public class ReadTrendsActivity extends AppCompatActivity {
    @ViewInject(R.id.titleBar)
    private TitleBar titleBar;
    @ViewInject(R.id.trends_content)
    private TextView trends_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setContentView(R.layout.activity_read_trends);
        x.view().inject(this);
        //在第一次调用RichText之前先调用RichText.initCacheDir()方法设置缓存目录，不设置会报错
        RichText.initCacheDir(this);

        Intent intent = getIntent();
        String trends_content_str = intent.getStringExtra("trends_content_str");
        RichText.from(trends_content_str).singleLoad(false).into(trends_content);
        initListener();
    }

    private void initListener(){
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadTrendsActivity.this.finish();
            }
        });
    }
}
