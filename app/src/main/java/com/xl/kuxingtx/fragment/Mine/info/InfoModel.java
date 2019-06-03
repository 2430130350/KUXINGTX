package com.xl.kuxingtx.fragment.Mine.info;

import android.util.Log;

import com.xl.kuxingtx.FriendInfo;
import com.xl.kuxingtx.MyApplication;
import com.xl.kuxingtx.UserInfo;
import com.xl.kuxingtx.friend;
import com.xl.kuxingtx.inter.FInfoMvp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        RequestParams params = new RequestParams(MyApplication.webUri_relation_confirm);
        params.addBodyParameter("uid",uid+"");
        params.addBodyParameter("fid",fid+"");
        params.addBodyParameter("nick_name",nick_name);
        params.addBodyParameter("description",description);
        params.addHeader("head","android"); //为当前请求添加一个头
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
                Log.e("用户确认好友发送成功", "成功");
                Log.e("用户确认好友发送成功", result);
                try {
                    JSONObject jsonResult = new JSONObject(result);
                    if(jsonResult.optBoolean("isRelation_confirmSuccess")){
                        /**
                         * 用户信息修改成功、
                         * */

                        Log.e("用户确认好友成功", result);

                        infoPresenter.relation_confirmSuccess();
                    }else{
                        Log.e("用户确认好友失败", result);
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
    public void relation_delPost(long uid, long fid) {
        RequestParams params = new RequestParams(MyApplication.webUri_relation_del);
        params.addBodyParameter("uid",uid+"");
        params.addBodyParameter("fid",fid+"");
        params.addHeader("head","android"); //为当前请求添加一个头
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
                Log.e("用户删除好友发送成功", "成功");
                Log.e("用户删除好友发送成功", result);
                try {
                    JSONObject jsonResult = new JSONObject(result);
                    if(jsonResult.optBoolean("isRelation_delSuccess")){
                        /**
                         * 用户信息删除成功、
                         * */

                        Log.e("用户删除好友成功", result);

                        infoPresenter.relation_delSuccess();
                    }else{
                        Log.e("用户删除好友失败", result);
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
    public void relation_my_all_qurPost(long uid) {
        RequestParams params = new RequestParams(MyApplication.webUri_relation_my_all_qur);
        params.addBodyParameter("uid",uid+"");
        params.addHeader("head","android"); //为当前请求添加一个头
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
                Log.e("用户查询所有好友发送成功", "成功");
                Log.e("用户查询所有好友发送成功", result);
                try {
                    JSONObject jsonResult = new JSONObject(result);
                    if(jsonResult.optBoolean("isRelation_my_all_qurSuccess")){
                        ArrayList<friend> friends=new ArrayList<friend>();
                        FriendInfo friendInfo = FriendInfo.getFriendInfo();

                        JSONObject object=new JSONObject(result);
                        JSONArray array=object.optJSONArray("resultContent");
                        for(int i=0;i<array.length();i++){
                            JSONObject objects=array.optJSONObject(i);
                            long uid=objects.optLong("uid");
                            long fid=objects.optLong("fid");
                            long mconfirm=objects.optLong("mconfirm");
                            long fconfirm=objects.optLong("fconfirm");
                            String nick_name=objects.optString("nick_name");
                            String description=objects.optString("description");
                            friend tmpfriend=new friend(uid,fid,mconfirm,fconfirm,nick_name,description);
                            friends.add(tmpfriend);
                        }

                        friendInfo.setFriends(friends);
                        /**
                         * 用户查询所有好友成功、
                         * */

                        Log.e("用户查询所有好友成功", result);

                        infoPresenter.relation_my_all_qurSuccess();
                    }else{
                        Log.e("用户查询好友失败", result);
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
    public void relation_my_one_qurPost(long uid, long fid) {
        RequestParams params = new RequestParams(MyApplication.webUri_relation_my_one_qur);
        params.addBodyParameter("uid",uid+"");
        params.addBodyParameter("fid",fid+"");
        params.addHeader("head","android"); //为当前请求添加一个头
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
                Log.e("用户查询单个好友发送成功", "成功");
                Log.e("用户查询单个好友发送成功", result);
                try {
                    JSONObject jsonResult = new JSONObject(result);
                    if(jsonResult.optBoolean("isRelation_my_one_qurSuccess")){
                        ArrayList<friend> friends=new ArrayList<friend>();
                        FriendInfo friendInfo = FriendInfo.getFriendInfo();

                        JSONObject object=new JSONObject(result);
                        JSONArray array=object.optJSONArray("resultContent");
                        for(int i=0;i<array.length();i++){
                            JSONObject objects=array.optJSONObject(i);
                            long uid=objects.optLong("uid");
                            long fid=objects.optLong("fid");
                            long mconfirm=objects.optLong("mconfirm");
                            long fconfirm=objects.optLong("fconfirm");
                            String nick_name=objects.optString("nick_name");
                            String description=objects.optString("description");
                            friend tmpfriend=new friend(uid,fid,mconfirm,fconfirm,nick_name,description);
                            friends.add(tmpfriend);
                        }

                        /**
                         * 用户查询所有好友成功、
                         * */

                        Log.e("用户查询所有好友成功", result);

                        infoPresenter.relation_my_one_qurSuccess(friends);
                    }else{
                        Log.e("用户查询好友失败", result);
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

}
