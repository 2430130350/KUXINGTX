package com.xl.kuxingtx.fragment.Mine.mine;

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

import com.xl.kuxingtx.R;
import com.xl.kuxingtx.inter.FMineMvp;
import com.xuexiang.xui.widget.button.ButtonView;
import com.xuexiang.xui.widget.edittext.ValidatorEditText;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.fragment_fragment_mine)//加载的xml文件
public class FragmentMine extends Fragment implements  View.OnClickListener, FMineMvp.View {
    private FMineMvp.Presenter minePresenter = new MinePresenter(this);
    //注:这是账号
    @ViewInject(R.id.input_username)
    private ValidatorEditText input_username;
    //注:这是密码
    @ViewInject(R.id.input_password)
    private ValidatorEditText input_password;
    //登录的按钮
    @ViewInject(R.id.sign_in_btn)
    private ButtonView sign_in_btn;
    @ViewInject(R.id.reinput_password)
    private ValidatorEditText reinput_password;
    @ViewInject(R.id.sign_up_btn)
    private ButtonView sign_up_btn;
    @ViewInject(R.id.to_sign_up)
    private TextView to_sign_up;
    @ViewInject(R.id.sign_in_text)
    private TextView sign_in_text;
    @ViewInject(R.id.sign_up_text)
    private TextView sign_up_text;
    @ViewInject(R.id.return_sign_in)
    private TextView return_sign_in;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_fragment_mine,null);

        View view = x.view().inject(this, inflater, container);

        initListener();
        return view;
    }

    private void initListener(){
        sign_in_btn.setOnClickListener(this);
        sign_up_btn.setOnClickListener(this);
        to_sign_up.setOnClickListener(this);
        return_sign_in.setOnClickListener(this);
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
        switch (v.getId()){
            case R.id.to_sign_up://地址
                //隐藏登录、
                sign_in_btn.setVisibility(View.GONE);
                to_sign_up.setVisibility(View.GONE);
                sign_in_text.setVisibility(View.GONE);

                //显示注册、
                reinput_password.setVisibility(View.VISIBLE);
                sign_up_btn.setVisibility(View.VISIBLE);
                sign_up_text.setVisibility(View.VISIBLE);
                return_sign_in.setVisibility(View.VISIBLE);
                break;
            case R.id.return_sign_in:
                //显示登录、
                sign_in_btn.setVisibility(View.VISIBLE);
                to_sign_up.setVisibility(View.VISIBLE);
                sign_in_text.setVisibility(View.VISIBLE);

                //隐藏注册、
                reinput_password.setVisibility(View.GONE);
                sign_up_btn.setVisibility(View.GONE);
                sign_up_text.setVisibility(View.GONE);
                return_sign_in.setVisibility(View.GONE);
                break;
        }
    }


    @Override
    public void loginSucess() {

    }

    @Override
    public void registerSuccess() {

    }

    @Override
    public void resetPasswordSuccess() {

    }

}