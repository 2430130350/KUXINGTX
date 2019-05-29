package com.xl.kuxingtx.activity.Main;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xl.kuxingtx.R;
import com.xl.kuxingtx.inter.MainMvp;

public class MainActivity extends AppCompatActivity implements MainMvp.View {
    private MainMvp.Presenter mainPresenter = new MainPresenter(this);
    private WebView mainWebView;
    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainWebView=(WebView)findViewById(R.id.mainWebView);
        //绑定mainWebView的id
        mainWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });
        /* 启用javascript */
        mainWebView.getSettings().setJavaScriptEnabled(true);
        mainWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mainWebView.getSettings().setLoadWithOverviewMode(true);
        mainWebView.addJavascriptInterface(this, "kxtx");
        mainWebView.loadUrl("http://10.120.175.8:8000/index.html");
    }
}