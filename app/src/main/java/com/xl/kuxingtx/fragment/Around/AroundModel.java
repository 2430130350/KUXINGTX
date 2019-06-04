package com.xl.kuxingtx.fragment.Around;

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

    @Override
    public void uploadTrendsPost(TrendsBean trendsBean) {

/*        POST{
            "uid": String uid;
            "date": String date;
            "content": String html;
            "imgNumber": int numebr;
            "img1": 图片1;
            "img2": 图片2;
        }*/
/*        JSONObject json = new JSONObject();
        try {
            json.put("uid", trendsBean.getUid());
            json.put("date", trendsBean.getmTime());
            json.put("imgNumber", 5);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        //构建RequestParams对象，传入请求的服务器地址URL
        RequestParams params = new RequestParams(MyApplication.webUri_upload_trends_post);

        params.setAsJsonContent(true);
        List<KeyValue> list = new ArrayList<>();
//        list.add(new KeyValue("uid", trendsBean.getUid());
        list.add(new KeyValue("date", trendsBean.getmTime().toString()));
        list.add(new KeyValue("content", trendsBean.getContent()));
//        list.add(new KeyValue("imgNumber", trendsBean.()));
        //list.add(new KeyValue("img1", new File(filePah)));

        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setRequestBody(body);
        x.http().post(params, new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
            }

            @Override
            public void onFinished() {
                //上传完成
            }

            @Override
            public void onCancelled(CancelledException cex) {
                //取消上传
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //上传失败

            }
        });
    }
}
