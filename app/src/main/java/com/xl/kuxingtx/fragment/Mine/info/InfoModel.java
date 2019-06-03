package com.xl.kuxingtx.fragment.Mine.info;

import android.util.Log;

import com.xl.kuxingtx.MyApplication;
import com.xl.kuxingtx.UserInfo;
import com.xl.kuxingtx.inter.FInfoMvp;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class InfoModel implements FInfoMvp.Model {
    private FInfoMvp.Presenter infoPresenter;
    public InfoModel(FInfoMvp.Presenter infoPresenter){
        this.infoPresenter = infoPresenter;
    }

    @Override
    public void query_infoPost(String userName, String password) {
        RequestParams params = new RequestParams(MyApplication.webUri_query_info);
        params.addBodyParameter("userName",userName);
        params.addBodyParameter("password",password);
        params.addHeader("head","android"); //为当前请求添加一个头
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
                Log.e("请求用户信息发送成功", "成功");
                Log.e("请求用户信息发送成功", result);
                try {
                    JSONObject jsonResult = new JSONObject(result);
                    if(jsonResult.optBoolean("isQuery_infoSuccess")){
                        /**
                         * 请求用户信息成功、
                         * */
                        UserInfo userInfo1 = UserInfo.getUserInfo();
                        if(userInfo1.isLogined()) {
                            userInfo1.setId(jsonResult.optInt("id"));
                            userInfo1.setRecords(jsonResult.optInt("records"));
                            userInfo1.setTreasure(jsonResult.optInt("treasure"));
                        }
                        Log.e("请求用户信息成功", result);

                        infoPresenter.query_infoSuccess();
                    }else{
                        Log.e("请求用户信息失败", result);
                    }
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

    @Override
    public void modify_infoPost(String oname, String opassword, String nname, String npassword) {
        RequestParams params = new RequestParams(MyApplication.webUri_modify_info);
        params.addBodyParameter("ouserName",oname);
        params.addBodyParameter("opassword",opassword);
        params.addBodyParameter("nuserName",nname);
        params.addBodyParameter("npassword",npassword);
        params.addHeader("head","android"); //为当前请求添加一个头
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
                Log.e("用户修改信息发送成功", "成功");
                Log.e("用户修改信息发送成功", result);
                try {
                    JSONObject jsonResult = new JSONObject(result);
                    if(jsonResult.optBoolean("isModify_infoSuccess")){
                        /**
                         * 用户信息修改成功、
                         * */

                        Log.e("用户信息修改成功", result);

                        infoPresenter.modify_infoSuccess();
                    }else{
                        Log.e("用户信息修改失败", result);
                    }
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


    @Override
    public void relation_addPost(long uid, long fid, String nick_name, String description) {
        RequestParams params = new RequestParams(MyApplication.webUri_relation_add);
        params.addBodyParameter("uid",uid+"");
        params.addBodyParameter("fid",fid+"");
        params.addBodyParameter("nick_name",nick_name);
        params.addBodyParameter("description",description);
        params.addHeader("head","android"); //为当前请求添加一个头
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
                Log.e("用户添加好友发送成功", "成功");
                Log.e("用户添加好友发送成功", result);
                try {
                    JSONObject jsonResult = new JSONObject(result);
                    if(jsonResult.optBoolean("isRelation_addSuccess")){
                        /**
                         * 用户信息修改成功、
                         * */

                        Log.e("用户添加好友成功", result);

                        infoPresenter.relation_addSuccess();
                    }else{
                        Log.e("用户添加好友失败", result);
                    }
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

    @Override
    public void relation_confirmPost(long uid, long fid, String nick_name, String description) {

    }

    @Override
    public void relation_delPost(long uid, long fid) {

    }

    @Override
    public void relation_my_all_qurPost(long uid) {

    }

    @Override
    public void relation_my_one_qurPost(long uid, long fid) {

    }

}
