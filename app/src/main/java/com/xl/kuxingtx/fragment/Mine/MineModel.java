package com.xl.kuxingtx.fragment.Mine;

import android.util.Log;

import com.xl.kuxingtx.MyApplication;
import com.xl.kuxingtx.UserInfo;
import com.xl.kuxingtx.inter.FMineMvp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class MineModel implements FMineMvp.Model {
    private FMineMvp.Presenter minePresenter;
    public MineModel(FMineMvp.Presenter minePresenter){
        this.minePresenter = minePresenter;
    }


    //登录功能
    public void loginPost(UserInfo userInfo){
        RequestParams params = new RequestParams(MyApplication.webUri_login);
        params.addBodyParameter("userName",userInfo.getUserName());
        params.addBodyParameter("password",userInfo.getPassword());
        params.addHeader("head","android"); //为当前请求添加一个头
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
                Log.e("登录发送成功", "成功、");
                Log.e("登录发送成功", result);
                try {
                    JSONObject jsonResult = new JSONObject(result);
                    if(jsonResult.optBoolean("isLoginSuccess")){
                        /**
                         * 登录成功、
                         * */
                        UserInfo userInfo1 = UserInfo.getUserInfo();
                        userInfo1.setUserName(jsonResult.optString("userName"));
                        userInfo1.setPassword(jsonResult.optString("password"));
                        userInfo1.setLogined(true);
                        Log.e("登录成功", result);

                        minePresenter.loginSucess();
                    }
                    /*JSONArray array=object.optJSONArray("result");
                    for(int i=0;i<array.length();i++){
                        JSONObject objects=array.optJSONObject(i);
                        String hostid=objects.optString("id");
                        String name=objects.optString("name");
                    }*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });

    }

    //注册功能
    public void registerPost(String userName, String password){
        RequestParams params = new RequestParams(MyApplication.webUri_register);
        params.addBodyParameter("userName",userName);
        params.addBodyParameter("password",password);
        params.addHeader("head","android"); //为当前请求添加一个头
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
                Log.e("注册发送成功", "成功、");
                Log.e("注册发送成功", result);
                try {
                    JSONObject jsonResult = new JSONObject(result);
                    if(jsonResult.optBoolean("isRegisterSuccess")){
                        /**
                         * 注册成功、
                         * */
                        Log.e("注册成功", result);

                        minePresenter.registerSuccess();
                        //UserInfo userInfo1 = UserInfo.getUserInfo();
                        //userInfo1.setUserName(jsonResult.optString("userName"));
                        //userInfo1.setPassword(jsonResult.optString("password"));
                        //userInfo1.setLogined(true);
                    }
                    /*JSONArray array=object.optJSONArray("result");
                    for(int i=0;i<array.length();i++){
                        JSONObject objects=array.optJSONObject(i);
                        String hostid=objects.optString("id");
                        String name=objects.optString("name");
                    }*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });

    }

    //重置密码功能
    public void resetPasswordPost(String ouserName, String opassword, String nuserName, String npassword){
        RequestParams params = new RequestParams(MyApplication.webUri_resetPassword);
        params.addBodyParameter("ouserName",ouserName);
        params.addBodyParameter("opassword",opassword);
        params.addBodyParameter("nuserName",nuserName);
        params.addBodyParameter("npassword",npassword);
        params.addHeader("head","android"); //为当前请求添加一个头
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
                Log.e("重置密码发送成功", "成功、");
                Log.e("重置密码发送成功", result);
                try {
                    JSONObject jsonResult = new JSONObject(result);
                    if(jsonResult.optBoolean("isResetSuccess")){
                        /**
                         * 重置密码成功、
                         * */
                        Log.e("重置密码成功", result);
                        minePresenter.resetPasswordSuccess();
                        //UserInfo userInfo1 = UserInfo.getUserInfo();
                        //userInfo1.setUserName(jsonResult.optString("userName"));
                        //userInfo1.setPassword(jsonResult.optString("password"));
                        //userInfo1.setLogined(true);
                    }
                    /*JSONArray array=object.optJSONArray("result");
                    for(int i=0;i<array.length();i++){
                        JSONObject objects=array.optJSONObject(i);
                        String hostid=objects.optString("id");
                        String name=objects.optString("name");
                    }*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });

    }

}
