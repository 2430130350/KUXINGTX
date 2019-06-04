package com.xl.kuxingtx.activity.allowFriend;

import android.util.Log;

import com.xl.kuxingtx.Friend;
import com.xl.kuxingtx.FriendInfo;
import com.xl.kuxingtx.MyApplication;
import com.xl.kuxingtx.inter.AllowFriendMvp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

public class AllowFriendModel implements AllowFriendMvp.Model {
    private AllowFriendMvp.Presenter allowFriendPresenter;
    public AllowFriendModel(AllowFriendMvp.Presenter allowFriendPresenter){
        this.allowFriendPresenter = allowFriendPresenter;
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

                        allowFriendPresenter.relation_confirmSuccess();
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

                        allowFriendPresenter.relation_delSuccess();
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
                        ArrayList<Friend> friends =new ArrayList<Friend>();
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
                            Friend tmpfriend=new Friend(uid,fid,mconfirm,fconfirm,nick_name,description);
                            friends.add(tmpfriend);
                        }

                        friendInfo.setFriends(friends);
                        /**
                         * 用户查询所有好友成功、
                         * */

                        Log.e("用户查询所有好友成功", result);

                        allowFriendPresenter.relation_my_all_qurSuccess();
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
                        ArrayList<Friend> Friends =new ArrayList<Friend>();
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
                            Friend tmpfriend=new Friend(uid,fid,mconfirm,fconfirm,nick_name,description);
                            Friends.add(tmpfriend);
                        }

                        /**
                         * 用户查询所有好友成功、
                         * */

                        Log.e("用户查询所有好友成功", result);

                        allowFriendPresenter.relation_my_one_qurSuccess(Friends);
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
