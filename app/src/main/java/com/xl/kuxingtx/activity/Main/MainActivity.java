package com.xl.kuxingtx.activity.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xl.kuxingtx.R;
import com.xl.kuxingtx.inter.MainMvp;

public class MainActivity extends AppCompatActivity implements MainMvp.View {
    private MainMvp.Presenter mainPresenter = new MainPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}