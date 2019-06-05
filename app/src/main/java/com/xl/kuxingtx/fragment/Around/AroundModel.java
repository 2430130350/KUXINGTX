package com.xl.kuxingtx.fragment.Around;

import android.util.Log;

import com.xl.kuxingtx.MyApplication;
import com.xl.kuxingtx.UserInfo;
import com.xl.kuxingtx.inter.FAroundMvp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.http.RequestParams;
import org.xutils.http.body.MultipartBody;
import org.xutils.x;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class AroundModel implements FAroundMvp.Model {
    private FAroundMvp.Presenter arroundPresenter;

    public AroundModel(FAroundMvp.Presenter arroundPresenter){
        this.arroundPresenter = arroundPresenter;
    }

    @Override
    public void loginPost(UserInfo userInfo) {

    }

    @Override
    public void registerPost(String userName, String password) {

    }

    @Override
    public void resetPasswordPost(String ouserName, String opassword, String nuserName, String npassword) {

    }


    public void loadTrendsPost(){
        RequestParams params = new RequestParams(MyApplication.webUri_load_trends_post);
        UserInfo userInfo = UserInfo.getUserInfo();
        params.addBodyParameter("uid", "" + userInfo.getId());
        params.addHeader("head","android"); //为当前请求添加一个头
        x.http().post(params, new Callback.CommonCallback<String>() {
            private List<TrendsBean> trendsBeans = new ArrayList<TrendsBean>();

            @Override
            public void onSuccess(String result) {
                //解析result
                try {
                    JSONObject jsonResult = new JSONObject(result);
                    if(jsonResult.optBoolean("isTrends_my_all_querSuccess")){
                        /**
                         * 获取数据成功、
                         * */
                        JSONArray array = jsonResult.optJSONArray("resultContent");
                        for(int i=0;i<array.length();i++){
                            JSONObject objects = array.optJSONObject(i);
                            long id = objects.optLong("uid");
                            String content = objects.optString("article");
                            String mTime = objects.optString("date");
                            mTime = mTime.replace("T", " ");
                            Date dTime = stringToDate1(mTime);

                            TrendsBean trendsBean = new TrendsBean();
                            trendsBean.setUid(id);
                            trendsBean.setmTime(dTime);
                            trendsBean.setContent(content);
                            trendsBeans.add(trendsBean);
                        }

                        sortTrends(trendsBeans);
                        arroundPresenter.loadTrendsSuccess(trendsBeans);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    arroundPresenter.loadTrendsSuccess(trendsBeans);
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
    public void delTrendsPost(long uid, String date) {
        RequestParams params = new RequestParams(MyApplication.webUri_del_trends_post);
        params.addBodyParameter("uid", "" + uid);
        params.addBodyParameter("date", date);
        params.addHeader("head","android"); //为当前请求添加一个头
        x.http().post(params, new Callback.CommonCallback<String>() {
            //private List<TrendsBean> trendsBeans = new ArrayList<TrendsBean>();

            @Override
            public void onSuccess(String result) {
                //解析result
                try {
                    JSONObject jsonResult = new JSONObject(result);
                    if(jsonResult.optBoolean("isTrends_my_one_delSuccess")){
                        /**
                         * 获取数据成功、
                         * */
/*                        JSONArray array = jsonResult.optJSONArray("resultContent");
                        for(int i=0;i<array.length();i++){
                            JSONObject objects = array.optJSONObject(i);
                            long id = objects.optLong("uid");
                            String content = objects.optString("article");
                            String mTime = objects.optString("date");
                            mTime = mTime.replace("T", " ");
                            Date dTime = stringToDate1(mTime);

                            TrendsBean trendsBean = new TrendsBean();
                            trendsBean.setUid(id);
                            trendsBean.setmTime(dTime);
                            trendsBean.setContent(content);
                            trendsBeans.add(trendsBean);
                        }

                        sortTrends(trendsBeans);
                        arroundPresenter.loadTrendsSuccess(trendsBeans);*/

                        arroundPresenter.delTrendsSuccess();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    //arroundPresenter.loadTrendsSuccess(trendsBeans);
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

    public static Date stringToDate1(String mTime){
        DateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date= null;
        try {
            date = format.parse(mTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.print(date);
        return date;
    }



    private void sortTrends(List<TrendsBean> trendsBeans){

        Collections.sort(trendsBeans, new Comparator<TrendsBean>(){
            @Override
            public int compare(TrendsBean arg0, TrendsBean arg1) {
                int mark = 1;
                Date date0 = arg0.getmTime();
                Date date1 = arg1.getmTime();
                if(date0.getTime() > date1.getTime()){
                    mark =  -1;
                }
                if(arg0.getmTime() == arg1.getmTime()){
                    mark =  0;
                }
                return mark;
            } //compare
        });

    }

}
