package com.xl.kuxingtx.fragment.Mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.xl.kuxingtx.R;
import com.xl.kuxingtx.inter.FMineMvp;

import org.xutils.view.annotation.ViewInject;


public class FragmentMine extends Fragment implements  View.OnClickListener, FMineMvp.View {
    private FMineMvp.Presenter minePresenter = new MinePresenter(this);
    //注:这是账号
    @ViewInject(R.id.editText2)
    private EditText ed1;

    //注:这是密码
    @ViewInject(R.id.editText)
    private EditText ed2;

    //登录的按钮
    @ViewInject(R.id.button6)
    private Button btn1;

    //忘记密码的按钮
    @ViewInject(R.id.button4)
    private Button btn2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_fragment_mine,null);
        ViewUtils.inject(getActivity());


        //登录的监听事件
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account=ed1.getText().toString();
                String pwd=ed2.getText().toString();
                Log.e("","我他妈要上天！！！我他妈要上天！！！我他妈要上天！！！我他妈要上天！！！我他妈要上天！！！+account");
                Log.e("","我他妈不要上 天！！！我他妈要上天！！！我他妈要上天！！！我他妈要上天！！！我他妈要上天！！！+account");
                //对model层的接口进行调用
            }
        });
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