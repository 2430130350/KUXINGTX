package com.xl.kuxingtx.fragment.Around;

import android.util.Log;

import com.xl.kuxingtx.MyApplication;
import com.xl.kuxingtx.UserInfo;
import com.xl.kuxingtx.inter.FAroundMvp;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.util.KeyValue;
import org.xutils.http.RequestParams;
import org.xutils.http.body.MultipartBody;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AroundModel implements FAroundMvp.Model {
    @Override
    public void loginPost(UserInfo userInfo) {

    }

    @Override
    public void registerPost(String userName, String password) {

    }

    @Override
    public void resetPasswordPost(String ouserName, String opassword, String nuserName, String npassword) {

    }

}
